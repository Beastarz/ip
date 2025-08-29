import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import clare.task.TaskList;
import clare.parser.Parser;
import clare.storage.Storage;
import clare.exception.StringConvertExceptions;
import clare.ui.UI;

public class Clare {
    final static Scanner scanner = new Scanner(System.in);

    public static void run() {
        UI ui = new UI();
        ui.welcome();
        Storage storage = new Storage("data/data.txt");
        TaskList taskList;
        try {
            taskList = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showMessage("File not Found: " + e);
            taskList = new TaskList();
        } catch (StringConvertExceptions e) {
            ui.showMessage("Error data format " + e);
            taskList = new TaskList();
        }
        Parser parser = new Parser(ui, storage, taskList);
        while (true) {
            String msg = scanner.nextLine();
            if (Objects.equals(msg, "bye")) {
                ui.farewell();
                break;
            }
            parser.processCommand(msg);
        }
    }

    public static void main(String[] args) {
        run();
    }
}
