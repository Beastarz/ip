package Task;

public class Todo extends Task{
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    String getTypeString() {
        return "T";
    }

    @Override
    public TaskType getType() {
        return TaskType.T;
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" + getIsDoneStatus() + " " + getTitle();
    }

    @Override
    public String toSaveString() {
        return  getTypeString() + "|" + getIsDoneInt() + "|" + getTitle();
    }
}
