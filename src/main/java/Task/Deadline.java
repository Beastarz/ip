package Task;

public class Deadline extends Todo{
    String deadline;

    public Deadline(String title, String deadline, boolean isDone) {
        super(title, isDone);
        this.deadline = deadline;
    }

    @Override
    String getTypeString() {
        return "D";
    }

    @Override
    public TaskType getType() {
        return TaskType.D;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" +  super.toString().substring(3) + " (by: " + deadline + ")";
    }

    @Override
    public String toSaveString() {
        return getTypeString() + super.toSaveString().substring(1) + "|" + getDeadline();
    }
}
