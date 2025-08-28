import java.util.Objects;
import java.util.Scanner;

import commands.ClareCommands;

public class Clare {
    final static Scanner scanner = new Scanner(System.in);

    public static void run() {
        ClareCommands.welcome();
        while (true) {
            String msg = scanner.nextLine();
            if (Objects.equals(msg, "bye")) {
                ClareCommands.farewell();
                break;
            }
            ClareCommands.processCommand(msg);
        }

    }

    public static void main(String[] args) {
        run();
    }
}
