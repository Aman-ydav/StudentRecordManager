import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Main extends JFrame {
    public  JTextField nameField, rollField, marksField;
    public JTextArea studentDisplay;
    public JLabel statusLabel;

    public Main() {
        setTitle("Student Record Manager");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Roll No:"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);

        statusLabel = new JLabel("");
        inputPanel.add(statusLabel);

        add(inputPanel, BorderLayout.NORTH);

        studentDisplay = new JTextArea();
        studentDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentDisplay);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student List"));
        add(scrollPane, BorderLayout.CENTER);

        addBottomButtons();

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int roll = Integer.parseInt(rollField.getText().trim());
                int marks = Integer.parseInt(marksField.getText().trim());

                Student s = new Student(name, roll, marks);
                DSA.addStudent(s);

                nameField.setText("");
                rollField.setText("");
                marksField.setText("");
                statusLabel.setText("Student added!");

                refreshStudentList();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Enter valid numbers for Roll and Marks.");
            }
        });

        setVisible(true);
    }

    public void refreshStudentList() {
        List<Student> allStudents = DSA.getAllStudents();
        studentDisplay.setText("");
        for (Student s : allStudents) {
            studentDisplay.append(s.toString() + "\n");
        }
    }

    private void addBottomButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));  
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton searchButton = new JButton("Search by Roll No");
        JButton deleteButton = new JButton("Delete by Roll No");
        JButton sortNameButton = new JButton("Sort by Name");
        JButton sortMarksButton = new JButton("Sort by Marks");

        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sortNameButton);
        buttonPanel.add(sortMarksButton);

        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Roll No to search:");
            if (input != null) {
                try {
                    int roll = Integer.parseInt(input.trim());
                    Student s = DSA.searchByRoll(roll);
                    if (s != null) {
                        JOptionPane.showMessageDialog(this, "Found:\n" + s.toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Roll Number.");
                }
            }
        });

        deleteButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Roll No to delete:");
            if (input != null) {
                try {
                    int roll = Integer.parseInt(input.trim());
                    if (DSA.deleteByRoll(roll)) {
                        JOptionPane.showMessageDialog(this, "Student deleted.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found.");
                    }
                    refreshStudentList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Roll Number.");
                }
            }
        });

        sortNameButton.addActionListener(e -> {
            studentDisplay.setText("");
            for (Student s : DSA.getStudentsSortedByName()) {
                studentDisplay.append(s.toString() + "\n");
            }
        });

        sortMarksButton.addActionListener(e -> {
            studentDisplay.setText("");
            for (Student s : DSA.getStudentsSortedByMarks()) {
                studentDisplay.append(s.toString() + "\n");
            }
        });
    }
    public static void main(String[] args) {
        new Main();
    }
}
