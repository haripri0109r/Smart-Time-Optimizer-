package src;

public class Task {
    int id;
    String name;
    String rank;
    boolean done;

    public Task(int i, String n, String p) {
        id = i;
        name = n;
        rank = p;
        done = false;
    }

    @Override
    public String toString() {
        if (done)
            return id + ") " + name + " - " + rank + " - Done";
        else
            return id + ") " + name + " - " + rank + " - Pending";
    }
}
