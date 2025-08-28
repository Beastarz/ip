import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import clare.Task.TaskList;
import clare.command.ClareCommand;
import clare.storage.Storage;
import clare.exception.StringConvertExceptions;
import clare.ui.UI;

public class Clare {
    final static Scanner scanner = new Scanner(System.in);

    public static void run() {
        UI ui = new UI();
        ui.welcome();
        Storage storage = new Storage("data/duke.data.txt");
        TaskList taskList;
        try {
            taskList = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showMessage("File not Found: " + e);
            taskList = new TaskList();
        } catch (StringConvertExceptions e) {
            ui.showMessage("Error duke.data format " + e);
            taskList = new TaskList();
        }
        ClareCommand command = new ClareCommand(ui, storage, taskList);
        while (true) {
            String msg = scanner.nextLine();
            if (Objects.equals(msg, "bye")) {
                ui.farewell();
                break;
            }
            command.processCommand(msg);
        }

    }

    public static void main(String[] args) {
        run();
    }
}
