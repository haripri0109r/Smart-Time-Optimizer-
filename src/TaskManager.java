package src;

import java.util.*;

public class TaskManager {
    private List<Task>array = new ArrayList<>();
    private int c = 1;

    public void add(Scanner s) {
        System.out.print("Task name: ");
        String n = s.nextLine();
        System.out.print("Priority: ");
        String p = s.nextLine();

        array.add(new Task(c++, n, p.toUpperCase()));
        System.out.println("Task added successfully!");
    }

   
    public void show() {
        if (array.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task t : array) System.out.println(t);
    }

    
    public void completed(Scanner s) {
        System.out.print("Enter task ID: ");
        int id = s.nextInt();
        for (Task t : array) {
            if (t.id == id) {
                if (!t.done) {
                    t.done = true;
                    System.out.println("Marked as done!");
                    motivationalReply();
                } else {
                    System.out.println("Already marked as done!");
                }
                return;
            }
        }
        System.out.println("Invalid ID.");
    }

   
    public void delete(Scanner s) {
        System.out.print("Enter task ID: ");
        int id = s.nextInt();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).id == id) {
                array.remove(i);
                System.out.println("Task deleted.");
                return;
            }
        }
        System.out.println("Invalid ID.");
    }

   
    public void report() {
        int done = 0;
        for (Task t : array) if (t.done) done++;
        int pending = array.size() - done;

        System.out.println("Total: " + array.size());
        System.out.println("Done: " + done);
        System.out.println("Pending: " + pending);
    }

    
    public void filter(Scanner s) {
        System.out.print("Enter priority: ");
        String pr = s.nextLine().toUpperCase();
        for (Task t : array)
            if (t.rank.equals(pr)) System.out.println(t);
    }


    public void search(Scanner s) {
        System.out.print("Enter keyword: ");
        String k = s.nextLine().toLowerCase();
        for (Task t : array)
            if (t.name.toLowerCase().contains(k)) System.out.println(t);
    }


    private void motivationalReply() {
        String[] quotes = {
            "Great job! Keep the momentum going!",
            "Another task conquered! You're unstoppable!",
            "Success is built one task at a time!",
            "Well done! Your hard work is paying off!",
            "Keep pushing forward, one step at a time!"
        };
        int index = new Random().nextInt(quotes.length);
        System.out.println("Motivational Auto-Reply: " + quotes[index]);
    }
}
