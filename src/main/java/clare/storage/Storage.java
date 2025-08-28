package clare.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import clare.Task.Task;
import clare.Task.TaskList;
import clare.exception.StringConvertExceptions;

public class Storage {
    final private String dataPath;

    public Storage(String path) {
        this.dataPath = path;
    }

    /**
     * Read duke.data from duke.data path and load task into tasks array
     * @throws FileNotFoundException duke.exception when the file is not found
     * @throws StringConvertExceptions duke.exception if there is string conversion error on the duke.command
     */
    public ArrayList<Task> loadData() throws FileNotFoundException, StringConvertExceptions {
        File f = new File(dataPath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(Task.convert(sc.nextLine()));
        }
        return tasks;
    }

    /**
     * Overwrites the file from duke.data path from tasks array
     * @param tasks the array of tasks
     * @throws IOException duke.exception when the file has problem
     */
    public void rewriteData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        fw.write(tasks.getAllTaskSaveString());
        fw.close();
    }

    /**
     * Add new line of duke.data to the file
     * @param newTask the new duke.data to be added
     * @throws IOException duke.exception when the file has problem
     */
    public void addData(Task newTask) throws IOException{
        FileWriter fw = new FileWriter(dataPath, true);
        fw.write(newTask.toSaveString() + System.lineSeparator());
        fw.close();
    }
}
