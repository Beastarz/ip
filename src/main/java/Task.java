public class Task {
    private boolean isDone;
    private String title;

    Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    void markDone() {
        this.isDone = true;
    }

    void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String d = isDone ? "X" : " ";
        return "[" + d + "]" + " " + title;
    }
}
