package clare.task;

import clare.exception.StringConvertExceptions;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int i) throws StringConvertExceptions {
        Task t;
        try {
            t = tasks.get(i);
            tasks.remove(i);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new StringConvertExceptions("There is no such task");
        }
    }

    public Task markDone(int i) throws StringConvertExceptions {
        Task t;
        try {
            t = tasks.get(i);
            t.markDone();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new StringConvertExceptions("There is no such task");
        }
    }

    public Task markUndone(int i) throws StringConvertExceptions {
        Task t;
        try {
            t = tasks.get(i);
            t.markUndone();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new StringConvertExceptions("There is no such task");
        }
    }

    public int size() {
        return tasks.size();
    }

    public String getAllTaskString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(tasks.get(i));
            if (i == tasks.size()-1) {
                break;
            }
            s.append("\n");
        }
        return s.toString();
    }

    public String getAllTaskSaveString() {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(task.toSaveString());
            s.append("\n");
        }
        return s.toString();
    }

    public String findTask(LocalDate date) {
        StringBuilder s = new StringBuilder();
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                if (((Deadline) t).checkDeadline(date)) {
                    s.append(t);
                    s.append("\n");
                }
            }
        }
        return s.toString();
    }
}
