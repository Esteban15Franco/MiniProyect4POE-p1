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