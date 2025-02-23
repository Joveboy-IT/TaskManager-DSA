import java.util.*;

class Task {
    private String title;
    private int priority;
    
    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getPriority() {
        return priority;
    }
    
    @Override
    public String toString() {
        return "Task: " + title + " | Priority: " + priority;
    }
}

public class TaskManager {
    private List<Task> tasks;
    
    public TaskManager() {
        tasks = new ArrayList<>();
    }
    
    public void addTask(String title, int priority) {
        tasks.add(new Task(title, priority));
        System.out.println("Task added successfully.");
    }
    
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    public void deleteTask(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks.remove(index - 1);
        System.out.println("Task removed successfully.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        
        while (true) {
            System.out.println("\nTask Manager:");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    manager.addTask(title, priority);
                    break;
                case 2:
                    manager.listTasks();
                    break;
                case 3:
                    System.out.print("Enter task number to delete: ");
                    int taskNumber = scanner.nextInt();
                    manager.deleteTask(taskNumber);
                    break;
                case 4:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
