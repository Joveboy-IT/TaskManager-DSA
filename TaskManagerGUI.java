import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Task Class
class Task {
    private String title;
    private int priority;
    private String deadline;
    private String status; // **Added status attribute**

    // Constructor to initialize task attributes
    public Task(String title, int priority, String deadline, String status) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status; // **Newly added field for task status**
    }
    // Getter method for task title
    public String getTitle() {
        return title;
    }
    // Getter method for task priority
    public int getPriority() {
        return priority;
    }
    // Getter method For task priority
    public String getDeadline() {
        return deadline;
    }
    // Getter method for task deadline
    public String getStatus() {
        return status;
    }
    // Getter method For task status
    public void setTitle(String title) {
        this.title = title;
    }
    // Setter method for updating status
    public void setPriority(int priority) {
        this.priority = priority;
    }
    // Getter method for task deadline
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    // Getter method for task status
    public void setStatus(String status) {
        this.status = status;
    }
}

// GUI-based Task Manager
public class TaskManagerGUI {
    private List<Task> tasks;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public TaskManagerGUI() {
        tasks = new ArrayList<>();

        // Create main frame
        frame = new JFrame("Task Manager");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table to display tasks
        String[] columns = {"Title", "Priority", "Deadline", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton updateButton = new JButton("Update Task");
        JButton deleteButton = new JButton("Delete Task");

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        frame.add(panel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(this::addTask);
        updateButton.addActionListener(this::updateTask);
        deleteButton.addActionListener(this::deleteTask);

        // Show frame
        frame.setVisible(true);
    }

    private void addTask(ActionEvent e) {
        JTextField titleField = new JTextField();
        JTextField priorityField = new JTextField();
        JTextField deadlineField = new JTextField();
        String[] statuses = {"Pending", "In Progress", "Completed"};
        JComboBox<String> statusBox = new JComboBox<>(statuses);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Priority (1-5):"));
        panel.add(priorityField);
        panel.add(new JLabel("Deadline (YYYY-MM-DD):"));
        panel.add(deadlineField);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Add Task", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                int priority = Integer.parseInt(priorityField.getText());
                String deadline = deadlineField.getText();
                String status = (String) statusBox.getSelectedItem();
                
                if (title.isEmpty() || deadline.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Task task = new Task(title, priority, deadline, status);
                tasks.add(task);
                tableModel.addRow(new Object[]{title, priority, deadline, status});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid priority value!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTask(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Task task = tasks.get(selectedRow);

        JTextField titleField = new JTextField(task.getTitle());
        JTextField priorityField = new JTextField(String.valueOf(task.getPriority()));
        JTextField deadlineField = new JTextField(task.getDeadline());
        String[] statuses = {"Pending", "In Progress", "Completed"};
        JComboBox<String> statusBox = new JComboBox<>(statuses);
        statusBox.setSelectedItem(task.getStatus());

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Priority (1-5):"));
        panel.add(priorityField);
        panel.add(new JLabel("Deadline (YYYY-MM-DD):"));
        panel.add(deadlineField);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Update Task", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                int priority = Integer.parseInt(priorityField.getText());
                String deadline = deadlineField.getText();
                String status = (String) statusBox.getSelectedItem();

                if (title.isEmpty() || deadline.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                task.setTitle(title);
                task.setPriority(priority);
                task.setDeadline(deadline);
                task.setStatus(status);

                tableModel.setValueAt(title, selectedRow, 0);
                tableModel.setValueAt(priority, selectedRow, 1);
                tableModel.setValueAt(deadline, selectedRow, 2);
                tableModel.setValueAt(status, selectedRow, 3);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid priority value!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteTask(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this task?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            tasks.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerGUI::new);
    }
}
