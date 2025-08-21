public class Deadline extends Todo{
    String deadline;

    Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    String getTypeString() {
        return "[D]";
    }

    public String getDeadlineString() {
        return "(by: " + deadline + ")";
    }

    @Override
    public String toString() {
        return getTypeString() +  super.toString().substring(3) + " (" + deadline + ")";
    }
}
