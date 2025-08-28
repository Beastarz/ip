package clare.command;

import clare.Task.Task;
import clare.Task.Deadline;
import clare.Task.Event;
import clare.Task.Todo;
import clare.Task.TaskList;
import clare.storage.Storage;
import clare.exception.StringConvertExceptions;
import clare.ui.UI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ClareCommand {
    private final UI ui;
    private final Storage storage;
    private final TaskList taskList;

    public ClareCommand(UI ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * process duke.command from input
     * @param msg the duke.command string
     */
    public void processCommand(String msg) {
        String[] splits = msg.split(" ");
        try {
            Commands command = Commands.valueOf(splits[0].toUpperCase());
            switch (command) {
                case BYE:
                    ui.farewell();
                    return;
                case LIST:
                    showList();
                    break;
                case MARK:
                    if (splits.length > 1) {
                        mark(splits[1]);
                    } else {
                        ui.showMessage("Please provide a number!");
                    }
                    break;
                case UNMARK:
                    if (splits.length > 1) {
                        unmark(splits[1]);
                    } else {
                        ui.showMessage("Please provide a number!");
                    }
                    break;
                case TODO:
                    createTodo(msg);
                    break;

                case DEADLINE:
                    createDeadline(msg);
                    break;

                case EVENT:
                    createEvent(msg);
                    break;

                case DELETE:
                    if (splits.length > 1) {
                        delete(splits[1]);
                    } else {
                        ui.showMessage("Please provide a number!");
                    }
                    break;
                case FIND:
                    find(msg);
                    break;
                default:
                    ui.showMessage("I don't understand this duke.command :(");
                    break;
            }
            } catch (IllegalArgumentException e) {
                ui.showMessage("I don't understand this duke.command :(");
            }

    }

    private void createTodo(String msg) {
        if (msg.length() < 5) {
            ui.showMessage("Please add a description.");
            return;
        }
        msg = msg.substring(5); // remove prefix duke.command

        Task newTask = new Todo(msg, false);
        try {
            storage.addData(newTask);
        } catch (IOException e) {
            ui.showMessage("Something went wrong." + e);
            return;
        }
        taskList.add(newTask);
        ui.showMessage("added todo: " + newTask + "\nNow you have " + taskList.size() + " tasks.");
    }

    private void createDeadline(String msg) {
        msg = msg.substring(8); // remove prefix duke.command
        String[] s = msg.split(" /");
        if (s.length < 2) {
            ui.showMessage("Please add a deadline according to this format: deadline ... /by ....");
            return;
        }
        if (!s[1].startsWith("by ")) {
            ui.showMessage("Wrong format!!\nPlease input according to this format: deadline ... /by ...");
            return;
        }
        Task newTask;

        try {
            newTask = new Deadline(s[0].substring(1), s[1].substring(3), false);
        } catch (StringConvertExceptions e) {
            ui.showMessage(e.toString());
            return;
        }

        try {
            storage.addData(newTask);
        } catch (IOException e) {
            ui.showMessage("Something went wrong." + e);
            return;
        }
        taskList.add(newTask);
        ui.showMessage("added deadline: " + newTask + "\nNow you have " + taskList.size() + " tasks.");
    }

    private void createEvent(String msg) {
        msg = msg.substring(5); // remove prefix duke.command
        String[] s = msg.split(" /");
        if (s.length < 3) {
            ui.showMessage("Please add a deadline and start time according to this format: event ... /from ... /to ....");
            return;
        }
        if (!s[1].startsWith("from ") || !s[2].startsWith("to ")) {
            ui.showMessage("Wrong format!!\nPlease input according to this format: event ... /from ... /to ...");
            return;
        }

        Task newTask;
        try {
            newTask = new Event(s[0].substring(1), s[1].substring(5), s[2].substring(3), false);
        } catch (StringConvertExceptions e) {
            ui.showMessage(e.toString());
            return;
        }

        try {
            storage.addData(newTask);
        } catch (IOException e) {
            ui.showMessage("Something went wrong." + e);
            return;
        }
        taskList.add(newTask);
        ui.showMessage("added event: " + newTask + "\nNow you have " + taskList.size() + " tasks.");
    }

    private void showList() {
        ui.showMessage(taskList.getAllTaskString());
    }

    private void mark(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            ui.showMessage("This is not a number!!");
            return;
        }
        Task t;
        try {
            t = taskList.markDone(i);
            storage.rewriteData(taskList);
        } catch (IOException e) {
            ui.showMessage("Something went wrong. " + e);
            return;
        } catch (StringConvertExceptions e) {
            ui.showMessage("Invalid format: " + e);
            return;
        }
        ui.showMessage("I have marked this task done:" + " \n  " + (i + 1) + ". " + t);
    }

    private void unmark(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            ui.showMessage("This is not a number!!");
            return;
        }
        Task t;
        try {
            t = taskList.markUndone(i);
            storage.rewriteData(taskList);
        } catch (IOException e) {
            ui.showMessage("Something went wrong. " + e);
            return;
        } catch (StringConvertExceptions e) {
            ui.showMessage("Invalid format: " + e);
            return;
        }
        ui.showMessage("I have marked this task undone:" + " \n  " + (i + 1) + ". " + t);
    }

    private void delete(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            ui.showMessage("This is not a number!!");
            return;
        }
        Task t;
        try {
            t = taskList.delete(i);
            storage.rewriteData(taskList);
        } catch (IOException e) {
            ui.showMessage("Something went wrong. " + e);
            return;
        } catch (StringConvertExceptions e) {
            ui.showMessage("Invalid format: " + e);
            return;
        }
        ui.showMessage("deleted event: " + t + "\nNow you have " + taskList.size() + " tasks.");
    }

    private void find(String msg) {
        String[] s = msg.split(" ");
        LocalDate date;
        try {
            date = LocalDate.parse(s[1]);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            ui.showMessage("Invalid format: please follow this format, YYYY-MM-DD");
            return;
        }
        ui.showMessage(taskList.findTask(date));
    }
}
