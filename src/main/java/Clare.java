import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Clare {
    static String divider = "----------------------------------------";
    static ArrayList<String> tasks = new ArrayList<>();

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
        tasks.add(task);
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        welcome();
        String msg = scanner.nextLine();
        while (!Objects.equals(msg, "bye")) {
            switch (msg) {
                case ("list"):
                    showList();
                    break;
                default:
                    addList(msg);
                    break;
            }
            msg = scanner.nextLine();
        }
        farewell();

    }
}
