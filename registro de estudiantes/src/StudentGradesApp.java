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
