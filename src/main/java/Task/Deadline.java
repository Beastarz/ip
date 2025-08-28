package Task;

import exception.StringConvertExceptions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Todo{
    LocalDate deadlineDate;
    LocalTime deadlineTime;

    public Deadline(String title, String deadline, boolean isDone) throws StringConvertExceptions{
        super(title, isDone);
        try {
            String[] d = deadline.split(" ");
            if (Objects.equals(d[0], "now")) {
                deadlineDate = LocalDate.now();
                this.deadlineTime = LocalTime.now();
                return;
            }
            deadlineDate = LocalDate.parse(d[0]);
            if (d.length > 1) {
                deadlineTime = LocalTime.parse(d[1]);
            }
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new StringConvertExceptions("Error deadline format: " + deadline + " Please follow this format YYYY-MM-DD HH:MM");
        }
    }

    @Override
    String getTypeString() {
        return "D";
    }

    @Override
    public TaskType getType() {
        return TaskType.D;
    }

    /**
     * check if the date give is same as the task deadline
     * @param date the given date to compare
     * @return true if equals, false otherwise
     */
    public boolean checkDeadline(LocalDate date) {
        return deadlineDate.equals(date);
    }

    /**
     * get the string of deadline
     * @return the string of deadline
     */
    protected String getDeadlineString() {
        return deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + (deadlineTime == null ? "" : (" " + deadlineTime));
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" +  super.toString().substring(3) + " (by: " + getDeadlineString() + ")";
    }

    @Override
    public String toSaveString() {
        return getTypeString() + super.toSaveString().substring(1) + "|" + deadlineDate + " " + deadlineTime;
    }
}
