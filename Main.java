import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private int id;
    private String title;
    private String priority;
    private boolean completed;

    public Task(int id, String title, String priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public int getId() {
         return id; 
        }
    public String getTitle() 
    { 
        return title; 
    }
    public String getPriority() 
    { 
        return priority; 
    }
    public boolean completed() 
    {
         return completed; 
    }

    public void setCompleted(boolean completed) 
    { 
        this.completed = completed; 
    }

    @Override
    public String toString() {
        return id + " | " + title + " | Priority: " + priority + " | Status: " + (completed ? "Completed" : "Pending");
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
            System.out.println("6. View Tasks by ");
            System.out.println("7. Search Task by Title");
            System.out.println("8. Exit");
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
                case 7 -> searchTaskByTitle(sc);
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
        sc.close();
    }

    private static void addTask(Scanner sc) {
        System.out.print("Enter task name : ");
        String title = sc.nextLine();
        System.out.print("Enter priority (HIGH/MEDIUM/LOW): ");
        String priority = sc.nextLine().toUpperCase();

        Task t = new Task(counter++, title, priority);
        tasks.add(t);
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks are available!");
        } else {
            System.out.println("\n--- Task List ---");
            tasks.forEach(System.out::println);
        }
    }

    private static void markTaskCompleted(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks is to mark!");
            return;
        }
        System.out.print("Enter task's ID to mark as completed: ");
        int id = sc.nextInt();

        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setCompleted(true);
                System.out.println("Task is completed!");
                return;
            }
        }
        System.out.println("Task not found!");
    }

    private static void deleteTask(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks is here!");
            return;
        }
        System.out.print("Enter task ID to delete: ");
        int id = sc.nextInt();

        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.remove(t);
                System.out.println("Task is deleted!");
                return;
            }
        }
        System.out.println("Task is not found!");
    }

    private static void reportSummary() {
        long lcompleted = tasks.stream().filter(Task::completed).count();
        long pending = tasks.size() - lcompleted;
        System.out.println("Total Tasks: " + tasks.size());
        System.out.println("Completed: " + lcompleted);
        System.out.println("Pending: " + pending);
    }

    private static void viewTasksByPriority(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks is available!");
            return;
        }
        System.out.print("Enter priority to filter (HIGH/MEDIUM/LOW): ");
        String priority = sc.nextLine().toUpperCase();

        tasks.stream()
             .filter(t -> t.getPriority().equals(priority))
             .forEach(System.out::println);
    }

    private static void searchTaskByTitle(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks is available!");
            return;
        }
        System.out.print("Enter keyword to search the task: ");
        String keyword = sc.nextLine().toLowerCase();

        tasks.stream()
             .filter(t -> t.getTitle().toLowerCase().contains(keyword))
             .forEach(System.out::println);
    }
}
