public class Task {
    String name;
    String priority;
    String status = "Pending";

    // Constructor
    public Task(String name, String priority) {
        this.name = name;
        this.priority = priority;
    }

    // Method to mark completed
    public void markCompleted() {
        status = "Completed";
    }

    // Method to display task
    public void displayTask() {
        System.out.println("Task: " + name);
        System.out.println("Priority: " + priority);
        System.out.println("Status: " + status);
    }


    public static void main(String[] args) {
        Task task1 = new Task("Finish report", "High");
        task1.displayTask();
        task1.markCompleted();
        task1.displayTask();
    }
}
