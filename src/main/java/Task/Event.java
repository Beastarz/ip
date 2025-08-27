package Task;

public class Event extends Deadline{
    String startTime;

    public Event(String title, String startTime, String deadline, boolean isDone) {
        super(title, deadline, isDone);
        this.startTime = startTime;
    }

    @Override
    String getTypeString() {
        return "E";
    }

    @Override
    public TaskType getType() {
        return TaskType.E;
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" + getIsDoneStatus() + " " + getTitle() + " (from: " + startTime + " to: " + getDeadline() + ")";
    }

    @Override
    public String toSaveString() {
        return getTypeString() +  super.toSaveString().substring(1) + "|" + startTime;
    }
}
