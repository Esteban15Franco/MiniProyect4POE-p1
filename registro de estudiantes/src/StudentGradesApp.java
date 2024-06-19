import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class StudentGradesApp extends JFrame {
    private JTextField nameField;
    private JTextField gradeField;
    private JTextArea resultArea;
    private ArrayList<Student> students;
    private static final String FILE_PATH = "students.csv";

    public StudentGradesApp() {
        students = new ArrayList<>();
        loadStudents();

        setTitle("Calificaciones de los estudiantes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Nombre:");
        nameField = new JTextField(20);
        JLabel gradeLabel = new JLabel("calificacion:");
        gradeField = new JTextField(5);

        JButton addButton = new JButton("Añadir estudiante");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton calculateButton = new JButton("Calcular promedio");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverage();
            }
        });

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        add(nameLabel);
        add(nameField);
        add(gradeLabel);
        add(gradeField);
        add(addButton);
        add(calculateButton);
        add(new JScrollPane(resultArea));
    }


    private void addStudent() {
        String name = nameField.getText();
        double grade;
        try {
            grade = Double.parseDouble(gradeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una calificación válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        students.add(new Student(name, grade));
        saveStudents();
        nameField.setText("");
        gradeField.setText("");
    }

    private void calculateAverage() {
        if (students.isEmpty()) {
            resultArea.setText("No hay estudiantes para calcular.");
            return;
        }

        double sum = 0;
        for (Student student : students) {
            sum += student.getGrade();
        }
        double average = sum / students.size();

        StringBuilder result = new StringBuilder();
        result.append("Notas promedio: ").append(average).append("\n");
        result.append("Estudiantes con calificaciones superiores al promedio:\n");

        for (Student student : students) {
            if (student.getGrade() > average) {
                result.append(student.getName()).append("\n");
            }
        }

        resultArea.setText(result.toString());
    }

    private void saveStudents() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.println(student.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de guardado de estudiantes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (FileNotFoundException e) {
            // File not found, no students to load
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de estudiantes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentGradesApp().setVisible(true);
            }
        });
    }
}

