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

        setTitle("Student Grades");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(5);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton calculateButton = new JButton("Calculate Average");
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
            JOptionPane.showMessageDialog(this, "Please enter a valid grade.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        students.add(new Student(name, grade));
        saveStudents();
        nameField.setText("");
        gradeField.setText("");
    }

    private void calculateAverage() {
        if (students.isEmpty()) {
            resultArea.setText("No students to calculate.");
            return;
        }

        double sum = 0;
        for (Student student : students) {
            sum += student.getGrade();
        }
        double average = sum / students.size();

        StringBuilder result = new StringBuilder();
        result.append("Average grade: ").append(average).append("\n");
        result.append("Students with grades above average:\n");

        for (Student student : students) {
            if (student.getGrade() > average) {
                result.append(student.getName()).append("\n");
            }
        }

        resultArea.setText(result.toString());
    }
