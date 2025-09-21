import java.util.*;

class Task {
    int id;
    String name;
    String prio;
    boolean done;

    Task(int i, String n, String p) {
        id = i;
        name = n;
        prio = p;
        done = false;
    }

    public String toString() {
        if (done) return id + ") " + name + " - " + prio + " - Done ";

        else return id + ") " + name + " - " + prio + " - Pending ";
    }
}

public class Main {
    static ArrayList<Task> arr = new ArrayList<>();
    static int c = 1;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int op = 0;
        while (op != 8) {
            System.out.println("\n==== SMART TIME ====");
            System.out.println("1 Add");
            System.out.println("2 Show");
            System.out.println("3 Complete");
            System.out.println("4 Delete");
            System.out.println("5 Summary");
            System.out.println("6 Filter");
            System.out.println("7 Search");
            System.out.println("8 Exit");
            System.out.print("Enter number: ");
            op = s.nextInt();
            s.nextLine();

            if (op == 1) add(s);
            else if (op == 2) show();
            else if (op == 3) completed(s);
            else if (op == 4) delete(s);
            else if (op == 5) report();
            else if (op == 6) filter(s);
            else if (op == 7) search(s);
            else if (op == 8) System.out.println("exiting");
            else System.out.println("invalid option");
        }
    }

    public static void add(Scanner s) {
        System.out.print("Task name: ");
        String n = s.nextLine();
        System.out.print("Priority: ");
        String p = s.nextLine();
        arr.add(new Task(c++, n, p.toUpperCase()));

        System.out.println("Task added ");
    }

    public static void show() {

        if (arr.size() == 0) {
            System.out.println("Nothing here ");
            return;
        }
        for (Task t : arr) {
            System.out.println(t);
        }
    }

    public static void completed(Scanner s) {
        
        System.out.print("Enter id: ");
        int id = s.nextInt();
        for (Task t : arr) {
            if (t.id == id) {
                t.done = true;
                System.out.println("Marked as done ");
                return;
            }
        }
        System.out.println("Id not found");
    }

    public static void delete(Scanner s) {
        System.out.print("Enter id: ");
        int id = s.nextInt();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).id == id) {
                arr.remove(i);
                System.out.println("Removed ");
                return;
            }
        }
        System.out.println("Not found ");
    }

    public static void report() {
        int d = 0;
        for (Task t : arr) {
            if (t.done) d++;
        }
        int p = arr.size() - d;
        System.out.println("Total: " + arr.size());
        System.out.println("Done: " + d);
        System.out.println("Pending: " + p);
    }

    public static void filter(Scanner s) {
        System.out.print("Enter priority: ");
        String pr = s.nextLine().toUpperCase();
        for (Task t : arr) {
            if (t.prio.equals(pr)) {
                System.out.println(t);
            }
        }
    }

    public static void search(Scanner s) {
        System.out.print("Enter keyword: ");
        String k = s.nextLine().toLowerCase();

        for (Task t : arr) {
            if (t.name.toLowerCase().contains(k)) {
                System.out.println(t);
            }
        }
    }
}
