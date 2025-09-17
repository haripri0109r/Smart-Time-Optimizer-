import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private int id;
    private String title;
    private String priority;
    private boolean isCompleted;

    public Task(int id, String title, String priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.isCompleted = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return isCompleted; }

    public void setTitle(String title) { this.title = title; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setCompleted(boolean completed) { this.isCompleted = completed; }

    @Override
    public String toString() {
        return id + " | " + title + " | Priority: " + priority + " | Status: " + (isCompleted ? "Completed" : "Pending");
    }
}

public class Main {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int counter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== Smart Time Optimizer ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Update Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addTask(sc);
                case 2 -> viewTasks();
                case 3 -> markTaskCompleted(sc);
                case 4 -> updateTask(sc);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }

    private static void addTask(Scanner sc) {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        System.out.print("Enter priority (HIGH/MEDIUM/LOW): ");
        String priority = sc.nextLine().toUpperCase();

        Task t = new Task(counter++, title, priority);
        tasks.add(t);
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available!");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    private static void markTaskCompleted(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to mark!");
            return;
        }
        System.out.print("Enter task ID to mark as completed: ");
        int id = sc.nextInt();
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setCompleted(true);
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found!");
    }

    private static void updateTask(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to update!");
            return;
        }
        System.out.print("Enter task ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Task t : tasks) {
            if (t.getId() == id) {
                System.out.print("Enter new title: ");
                String newTitle = sc.nextLine();
                System.out.print("Enter new priority (HIGH/MEDIUM/LOW): ");
                String newPriority = sc.nextLine().toUpperCase();

                t.setTitle(newTitle);
                t.setPriority(newPriority);
                System.out.println("Task updated successfully!");
                return;
            }
        }
        System.out.println("Task not found!");
    }
}
