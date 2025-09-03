package clare.task;

import clare.exception.StringConvertExceptions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a task type with a deadline and a start time
 */
public class Event extends Deadline{
    LocalDate startDate;
    LocalTime startTime;

    public Event(String title, String startTime, String deadline, boolean isDone) throws StringConvertExceptions {
        super(title, deadline, isDone);
        try {
            String[] d = startTime.split(" ");
            if (Objects.equals(d[0], "now")) {
                startDate = LocalDate.now();
                this.startTime = LocalTime.now();
                return;
            }
            startDate = LocalDate.parse(d[0]);
            if (d.length > 1) {
                this.startTime = LocalTime.parse(d[1]);
            }
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new StringConvertExceptions("Error start date format: " + deadline + " Please follow this format YYYY-MM-DD HH:MM");
        }
    }

    @Override
    String getTypeString() {
        return "E";
    }

    @Override
    public TaskType getType() {
        return TaskType.E;
    }

    private String getStartTime() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ (startTime == null ? "" : (" " + startTime));
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" + getIsDoneStatus() + " " + getTitle() + " (from: " + getStartTime() + " to: " + getDeadlineString() + ")";
    }

    @Override
    public String toSaveString() {
        return getTypeString() +  super.toSaveString().substring(1) + "|" + startDate + (startTime == null ? "" : (" " +startTime));
    }
}
