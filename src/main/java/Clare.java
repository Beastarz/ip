import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Clare {
    static String divider = "----------------------------------------";
    static ArrayList<Task> tasks = new ArrayList<>();

    private static void welcome() {
        String welcomeText = "Hello dear, I am Clare!\nSo happy to see you today.\nWhat can I help?";
        System.out.println(divider);
        System.out.println(welcomeText);
        System.out.println(divider);
    }

    private static void farewell() {
        String farewellText = "Bye dear. I will miss you!";
        System.out.println(divider);
        System.out.println(farewellText);
        System.out.println(divider);
    }

    private static void addList(String task) {
        tasks.add(new Task(task));
        System.out.println(divider);
        System.out.println("added: " + task);
        System.out.println(divider);
    }

    private static void showList() {
        System.out.println(divider);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        System.out.println(divider);
    }

    private static void mark(int i) {
        i = i-1;
        if (i < 0 || i >= tasks.size()) {
            System.out.println("My dear. There is no such task.");
        } else {
            Task t = tasks.get(i);
            t.markDone();
            System.out.println(divider);
            System.out.println("I have marked this task done:");
            System.out.println("   " + (i + 1) + ". " + t);
            System.out.println(divider);
        }
    }

    private static void unmark(int i) {
        i = i-1;
        if (i < 0 || i >= tasks.size()) {
            System.out.println("My dear. There is no such task.");
        } else {
            Task t = tasks.get(i);
            t.markUndone();
            System.out.println(divider);
            System.out.println("I have unmarked this task done:");
            System.out.println("   " + (i + 1) + ". " + t);
            System.out.println(divider);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        welcome();
        String msg = scanner.nextLine();
        while (!Objects.equals(msg, "bye")) {
            String[] splits = msg.split(" ");

            switch (splits[0]) {
                case("list"):
                    showList();
                    break;
                case("mark"):
                    if (splits.length > 1) {
                        mark(Integer.parseInt(splits[1]));
                        break;
                    }
                case("unmark"):
                    if (splits.length > 1) {
                        unmark(Integer.parseInt(splits[1]));
                        break;
                    }
                default:
                    addList(msg);
                    break;
            }
            msg = scanner.nextLine();
        }
        farewell();

    }
}
