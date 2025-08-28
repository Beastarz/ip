package Task;

import exception.StringConvertExceptions;

import java.util.Objects;

public abstract class Task {
    private boolean isDone;
    private final String title;

    Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Method to convert data strings to Task object
     * @param data data string
     * @return Task object from data string
     * @throws StringConvertExceptions exceptions when the format of data string is wrong
     */
    public static Task convert(String data) throws StringConvertExceptions {
        String[] l = data.split("\\|");
        TaskType t;
        boolean isDone;
        Task task;
        try {
            t = TaskType.valueOf(l[0]);
        } catch (IllegalArgumentException e) {
            throw new StringConvertExceptions("Unknown type");
        }
        if (Objects.equals(l[1], "0")) {
            isDone = false;
        } else if (Objects.equals(l[1], "1")) {
            isDone = true;
        } else {
            throw new StringConvertExceptions("Unknown mark status");
        }

        switch (t) {
            case T -> {
                if (l.length < 3) {
                    throw new StringConvertExceptions("Missing information on data" + data);
                }
                task = new Todo(l[2], isDone);
            }
            case D -> {
                if (l.length < 4) {
                    throw new StringConvertExceptions("Missing information on data" + data);
                }
                task = new Deadline(l[2], l[3], isDone);
            }
            case E -> {
                if (l.length < 5) {
                    throw new StringConvertExceptions("Missing information on data" + data);
                }
                task = new Event(l[2], l[3], l[4], isDone);
            }
            default -> throw new StringConvertExceptions("Unknown type");
        }
        return task;
    }

    /**
     * gets the title string
     * @return the string of title
     */
    protected String getTitle() {
        return title;
    }

    /**
     * get string representation of status
     * @return string of status
     */
    protected String getIsDoneStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * get string representation of status to save
     * @return string of status
     */
    protected String getIsDoneInt() {
        return  isDone ? "1" : "0";
    }

    /**
     * mark isDone status as true
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * mark isDone status as false
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * get the type of task
     * @return TaskType
     */
    protected abstract TaskType getType();

    /**
     * get the string of type of thr task
     * @return string of type
     */
    abstract String getTypeString();

    /**
     * get the string of task to save
     * @return string to save
     */
    public abstract String toSaveString();


}
