import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import data.Data;
import exception.StringConvertExceptions;

enum Commands {
    LIST,
    BYE,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    FIND,
}

public class Clare {
    static String divider = "----------------------------------------";
    static ArrayList<Task> tasks = new ArrayList<>();
    static String dataPath = "data/data.txt";

    private static void clareSays(String msg) {
        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
    }

    private static void welcome() {
        String welcomeText = "Hello dear, I am Clare!\nSo happy to see you today.\nWhat can I help?";
        try {
            Data.readFile(tasks, dataPath);
        } catch (FileNotFoundException e) {
            clareSays("File not found. "+ e);
        } catch (StringConvertExceptions e) {
            clareSays("Failed to load file: " + e);
        }
        clareSays(welcomeText);
    }

    private static void farewell() {
        String farewellText = "Bye dear. I will miss you!";
        clareSays(farewellText);
    }

    private static void createTodo(String msg) {
        if (msg.length() < 5) {
            clareSays("Please add a description.");
            return;
        }
        msg = msg.substring(5); // remove prefix command

        Task newTask = new Todo(msg, false);
        try {
            Data.addData(newTask, dataPath);
        } catch (IOException e) {
            clareSays("Something went wrong." + e);
            return;
        }
        tasks.add(newTask);
        clareSays("added todo: " + newTask + "\nNow you have " + tasks.size() + " tasks.");
    }

    private static void createDeadline(String msg) {
        msg = msg.substring(8); // remove prefix command
        String[] s = msg.split(" /");
        if (s.length < 2) {
            clareSays("Please add a deadline according to this format: deadline ... /by ....");
            return;
        }
        if (!s[1].startsWith("by ")) {
            clareSays("Wrong format!!\nPlease input according to this format: deadline ... /by ...");
            return;
        }
        Task newTask;

        try {
            newTask = new Deadline(s[0].substring(1), s[1].substring(3), false);
        } catch (StringConvertExceptions e) {
            clareSays(e.toString());
            return;
        }

        try {
            Data.addData(newTask, dataPath);
        } catch (IOException e) {
            clareSays("Something went wrong." + e);
            return;
        }
        tasks.add(newTask);
        clareSays("added deadline: " + newTask + "\nNow you have " + tasks.size() + " tasks.");
    }

    private static void createEvent(String msg) {
        msg = msg.substring(5); // remove prefix command
        String[] s = msg.split(" /");
        if (s.length < 3) {
            clareSays("Please add a deadline and start time according to this format: event ... /from ... /to ....");
            return;
        }
        if (!s[1].startsWith("from ") || !s[2].startsWith("to ")) {
            clareSays("Wrong format!!\nPlease input according to this format: event ... /from ... /to ...");
            return;
        }

        Task newTask;
        try {
            newTask = new Event(s[0].substring(1), s[1].substring(5), s[2].substring(3), false);
        } catch (StringConvertExceptions e) {
            clareSays(e.toString());
            return;
        }

        try {
            Data.addData(newTask, dataPath);
        } catch (IOException e) {
            clareSays("Something went wrong." + e);
            return;
        }
        tasks.add(newTask);
        clareSays("added event: " + newTask + "\nNow you have " + tasks.size() + " tasks.");
    }

    private static void showList() {
        System.out.println(divider);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        System.out.println(divider);
    }

    private static void mark(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            clareSays("This is not a number!!");
            return;
        }
        if (i < 0 || i >= tasks.size()) {
            clareSays("My dear. There is no such task.");
        } else {
            Task t = tasks.get(i);
            t.markDone();
            try {
                Data.rewriteData(tasks, dataPath);
            } catch (IOException e) {
                clareSays("Something went wrong. " + e);
            }
            System.out.println(divider);
            System.out.println("I have marked this task done:");
            System.out.println("   " + (i + 1) + ". " + t);
            System.out.println(divider);
        }
    }

    private static void unmark(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            clareSays("This is not a number!!");
            return;
        }
        if (i < 0 || i >= tasks.size()) {
            clareSays("My dear. There is no such task.");
        } else {
            Task t = tasks.get(i);
            t.markUndone();
            try {
                Data.rewriteData(tasks, dataPath);
            } catch (IOException e) {
                clareSays("Something went wrong. " + e);
            }
            System.out.println(divider);
            System.out.println("I have unmarked this task done:");
            System.out.println("   " + (i + 1) + ". " + t);
            System.out.println(divider);
        }
    }

    private static void delete(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            clareSays("This is not a number!!");
            return;
        }
        if (i < 0 || i >= tasks.size()) {
            clareSays("My dear. There is no such task.");
        } else {
            Task t = tasks.get(i);
            tasks.remove(i);
            try {
                Data.rewriteData(tasks, dataPath);
            } catch (IOException e) {
                clareSays("Something went wrong. " + e);
            }
            clareSays("deleted event: " + t + "\nNow you have " + tasks.size() + " tasks.");
        }
    }

    private static void find(String msg) {
        String[] s = msg.split(" ");
        LocalDate date;
        try {
            date = LocalDate.parse(s[1]);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            clareSays("Invalid format: please follow this format, YYYY-MM-DD");
            return;
        }
        System.out.println(divider);
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                if (((Deadline) t).getDeadlineDate().equals(date)) {
                    System.out.println(t);
                }
            }
        }
        System.out.println(divider);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        welcome();
        String msg = scanner.nextLine();
        while (true) {
            String[] splits = msg.split(" ");
            if (splits.length > 0) {
                try {
                    Commands command = Commands.valueOf(splits[0].toUpperCase());
                    switch (command) {
                        case BYE:
                            farewell();
                            return;
                        case LIST:
                            showList();
                            break;
                        case MARK:
                            if (splits.length > 1) {
                                mark(splits[1]);
                            } else {
                                clareSays("Please provide a number!");
                            }
                            break;
                        case UNMARK:
                            if (splits.length > 1) {
                                unmark(splits[1]);
                            } else {
                                clareSays("Please provide a number!");
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
                                clareSays("Please provide a number!");
                            }
                            break;
                        case FIND:
                            find(msg);
                            break;
                        default:
                            clareSays("I don't understand this command :(");
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    clareSays("I don't understand this command :(");
                }
            }
            msg = scanner.nextLine();
        }
    }
}
