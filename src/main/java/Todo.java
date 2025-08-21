public class Todo extends Task{
    Todo(String title) {
        super(title);
    }

    @Override
    String getTypeString() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getTypeString() + getIsDoneStatus() + " " + title;
    }
}
