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

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return getTypeString() +  super.toString().substring(3) + " (by: " + deadline + ")";
    }
}
