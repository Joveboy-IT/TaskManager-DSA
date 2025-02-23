import java.util.*;

// Task class to store task details
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
    
    // Getter method for task deadline
    public String getDeadline() {
        return deadline;
    }
    
    // Getter method for task status
    public String getStatus() {
        return status;
    }
    
    // Setter method for updating status
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Override toString method to display task details
    @Override
    public String toString() {
        return "Task: " + title + " | Priority: " + priority + " | Deadline: " + deadline + " | Status: " + status;
    }
}

// TaskManager class to handle task operations
public class TaskManager {
    private List<Task> tasks;
    
    // Constructor to initialize task list
    public TaskManager() {
        tasks = new ArrayList<>(); // Using ArrayList as a dynamic array (DSA: List)
    }
    
    // **Fixed saving task functionality**
    public void addTask(String title, int priority, String deadline, String status) {
        Task newTask = new Task(title, priority, deadline, status);
        tasks.add(newTask); // **Ensuring the task is correctly added**
        System.out.println("Task added successfully.");
    }
    
    // Method to list all tasks
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        // Sorting tasks by priority using Comparator (O(n log n) complexity due to sorting algorithm)
        tasks.sort(Comparator.comparingInt(Task::getPriority));
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    // Method to update an existing task
    public void updateTask(int index, String newTitle, int newPriority, String newDeadline, String newStatus) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks.set(index - 1, new Task(newTitle, newPriority, newDeadline, newStatus)); // **Updating an element in ArrayList (O(1) complexity)**
        System.out.println("Task updated successfully.");
    }
    
    // Method to delete a task
    public void deleteTask(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks.remove(index - 1); // Removing an element from ArrayList (O(n) complexity as shifting elements occurs)
        System.out.println("Task removed successfully.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        
        while (true) {
            // Display menu options
            System.out.println("\nTask Manager:");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    // Add new task
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter deadline (YYYY-MM-DD): ");
                    String deadline = scanner.nextLine();
                    System.out.print("Enter status (Pending/In Progress/Completed): ");
                    String status = scanner.nextLine();
                    manager.addTask(title, priority, deadline, status);
                    break;
                case 2:
                    // List tasks
                    manager.listTasks();
                    break;
                case 3:
                    // Update an existing task
                    System.out.print("Enter task number to update: ");
                    int taskNum = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new task title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new priority (1-5): ");
                    int newPriority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new deadline (YYYY-MM-DD): ");
                    String newDeadline = scanner.nextLine();
                    System.out.print("Enter new status (Pending/In Progress/Completed): ");
                    String newStatus = scanner.nextLine();
                    manager.updateTask(taskNum, newTitle, newPriority, newDeadline, newStatus);
                    break;
                case 4:
                    // Delete a task
                    System.out.print("Enter task number to delete: ");
                    int deleteNum = scanner.nextInt();
                    manager.deleteTask(deleteNum);
                    break;
                case 5:
                    // Exit program
                    System.out.println("Exiting Task Manager. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
