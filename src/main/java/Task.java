public abstract class Task {
    private boolean isDone;
    final String title;

    Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public String getIsDoneStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    abstract String getTypeString();


}
