package Task;

import exception.StringConvertExceptions;

import java.util.Objects;

public abstract class Task {
    static String hi = "KK";
    private boolean isDone;
    private final String title;

    Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

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

    public String getTitle() {
        return title;
    }

    public String getIsDoneStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getIsDone() {
        return  isDone ? "1" : "0";
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public abstract TaskType getType();
    abstract String getTypeString();
    public abstract String toSaveString();


}
