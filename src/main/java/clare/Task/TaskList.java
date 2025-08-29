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

    /**
     * delete a task using the index number
     * @param i the index
     * @return the task to be deleted
     * @throws StringConvertExceptions exceptions when the given index is invalid
     */
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

    /**
     * mark a task done using an index
     * @param i the index
     * @return the task marked done
     * @throws StringConvertExceptions exception when the index given is invalid
     */
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

    /**
     * mark a task undone using an index
     * @param i the index
     * @return the task marked undone
     * @throws StringConvertExceptions exception when the index given is invalid
     */
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

    /**
     * return the size of the task list
     * @return the size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * get the string representation of all task
     * @return the string of all task in list
     */
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

    /**
     * get the string of all task for storage
     * @return the string for all task in save format
     */
    public String getAllTaskSaveString() {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(task.toSaveString());
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * get the string of tasks searched by deadline
     * @param deadline the deadline of task
     * @return the string pf tasks within the deadline in list
     */
    public String findTaskByDeadline(LocalDate deadline) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t instanceof Deadline) {
                if (((Deadline) t).checkDeadline(deadline)) {
                    s.append(t);
                    if (i != tasks.size() - 1) {
                        s.append("\n");
                    }
                }
            }
        }
        return s.isEmpty() ? "No task found." : s.toString();
    }
}
