import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task implements Serializable {
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
        return id + " | " + title + " | Priority: " + priority + " | Status: " + (isCompleted ? "Completed" : "Pending");
    }
}

public class Main {
    private static List<Task> tasks = new ArrayList<>();
    private static int counter = 1;
    private static final String FILE_NAME = "tasks.dat";

    public static void main(String[] args) {
        loadTasks();

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
            System.out.println("7. Save Tasks");
            System.out.println("8. Search Task by Title");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addTask(sc);
                case 2 -> viewTasks();
                case 3 -> markTaskCompleted(sc);
                case 4 -> deleteTask(sc);
                case 5 -> reportSummary();
                case 6 -> viewTasksByPriority(sc);
                case 7 -> saveTasks();
                case 8 -> searchTaskByTitle(sc);
                case 9 -> {
                    saveTasks();
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 9);
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
            System.out.println("\n--- Task List ---");
            tasks.forEach(System.out::println);
        }
    }

    private static void markTaskCompleted(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.printl      int id = sc.nextInt();

        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) {
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    private static void reportSummary() {
        int total = tasks.size();
        long completed = tasks.stream().filter(Task::isCompleted).count();
        long pending = total - completed;

        System.out.println("\n--- Report Summary ---");
        System.out.println("Total Tasks: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + pending);
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
            System.out.println("No tasks found with " + filter + " priority.");
        }
    }

    private static void searchTaskByTitle(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to search!");
            return;
        }
        System.out.print("Enter title keyword to search: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Task t : tasks) {
            if (t.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with keyword: " + keyword);
        }
    }

    private static void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) ois.readObject();
            if (!tasks.isEmpty()) {
                counter = tasks.get(tasks.size() - 1).getId() + 1;
            }
            System.out.println("Tasks loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
