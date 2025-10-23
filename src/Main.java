package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        int op = 0;

        System.out.println("Welcome to SMART TIME! Let's be productive!");

        while (op != 8) {
            System.out.println("\n==== SMART TIME ====");
            System.out.println("1. Add");
            System.out.println("2. Show");
            System.out.println("3. Complete");
            System.out.println("4. Delete");
            System.out.println("5. Report");
            System.out.println("6. Filter");
            System.out.println("7. Search");
            System.out.println("8. Exit");
            System.out.print("Enter number: ");

            op = s.nextInt();
            s.nextLine();

            switch (op) {
                case 1 -> manager.add(s);
                case 2 -> manager.show();
                case 3 -> manager.completed(s);
                case 4 -> manager.delete(s);
                case 5 -> manager.report();
                case 6 -> manager.filter(s);
                case 7 -> manager.search(s);
                case 8 -> System.out.println("Exiting... Stay productive!");
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
