import java.util.ArrayList;
import java.util.List;
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

    public void setCompleted(boolean completed) { this.isCompleted = completed; }

    @Override
    public String toString() {
        return id + " | " + title + " | Priority: " + priority + " | Status: " + (isCompleted ? "✅ Completed" : "⌛ Pending");
    }
}

public class Main {
    private static List<Task> tasks = new ArrayList<>();
    private static int counter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Smart Time ---");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Report Summary");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTask(sc);
                case 2 -> viewTasks();
                case 3 -> markTaskCompleted(sc);
                case 4 -> deleteTask(sc);
                case 5 -> reportSummary();
                case 6 -> viewTasksByPriority(sc);
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 7);
        sc.close();
    }

    private static void addTask(Scanner sc) {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        System.out.print("Enter priority (HIGH/MEDIUM/LOW): ");
        String priority = sc.nextLine().toUpperCase();

        Task t = new Task(counter++, title, priority);
        tasks.add(t);
        System.out.println(" Task added successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks available!");
        } else {
            System.out.println("\n--- Task List ---");
            tasks.forEach(System.out::println);
        }
    }

    private static void markTaskCompleted(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks available to mark!");
            return;
        }
        System.out.print("Enter Task ID to mark as completed: ");
        int id = sc.nextInt();
        boolean found = false;

        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setCompleted(true);
                System.out.println(" Task marked as completed!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(" Task not found!");
        }
    }

    private static void deleteTask(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks available to delete!");
            return;
        }
        System.out.print("Enter Task ID to delete: ");
        int id = sc.nextInt();
        boolean removed = tasks.removeIf(t -> t.getId() == id);

        if (removed) {
            System.out.println(" Task deleted successfully!");
        } else {
            System.out.println(" Task not found!");
        }
    }

    private static void reportSummary() {
        int total = tasks.size();
        long completed = tasks.stream().filter(Task::isCompleted).count();
        long pending = total - completed;

        System.out.println("\n--- Report Summary ---");
        System.out.println(" Total Tasks: " + total);
        System.out.println(" Completed: " + completed);
        System.out.println(" Pending: " + pending);
    }

    private static void viewTasksByPriority(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }
        System.out.print("Enter priority to filter (HIGH/MEDIUM/LOW): ");
        String filter = sc.nextLine().toUpperCase();

        System.out.println("\n--- " + filter + " Priority Tasks ---");
        boolean found = false;
        for (Task t : tasks) {
            if (t.getPriority().equals(filter)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println(" No tasks found with " + filter + " priority.");
        }
    }
}
