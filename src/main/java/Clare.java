import java.util.Objects;
import java.util.Scanner;

public class Clare {
    static String divider = "----------------------------------------";

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

    private static void echo(String msg) {
        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        welcome();
        String msg = scanner.next();
        while (!Objects.equals(msg, "bye")) {
            echo(msg);
            msg = scanner.next();
        }
        farewell();

    }
}
