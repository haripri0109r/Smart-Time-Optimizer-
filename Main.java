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
        return id + " | " + title + " | Priority: " + priority + " | Status: " + (isCompleted ? "Completed" : "Pending");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Smart Time ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 3);
        sc.close();
    }

    private static void addTask() {
       
    }

    private static void viewTasks() {
        // to be implemented
    }
}
