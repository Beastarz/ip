public class Event extends Deadline{
    String startTime;

    Event(String title, String startTime, String deadline) {
        super(title, deadline);
        this.startTime = startTime;
    }

    @Override
    String getTypeString() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeString() + super.getIsDoneStatus() + " " + super.title + " (from: " + startTime + " to: " + getDeadline() + ")";
    }
}
