package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;
import Task.TaskList;
import exception.StringConvertExceptions;

public class Storage {
    final private String dataPath;

    public Storage(String path) {
        this.dataPath = path;
    }

    /**
     * Read data from data path and load task into tasks array
     * @throws FileNotFoundException exception when the file is not found
     * @throws StringConvertExceptions exception if there is string conversion error on the command
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
     * Overwrites the file from data path from tasks array
     * @param tasks the array of tasks
     * @throws IOException exception when the file has problem
     */
    public void rewriteData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        fw.write(tasks.getAllTaskSaveString());
        fw.close();
    }

    /**
     * Add new line of data to the file
     * @param newTask the new data to be added
     * @throws IOException exception when the file has problem
     */
    public void addData(Task newTask) throws IOException{
        FileWriter fw = new FileWriter(dataPath, true);
        fw.write(newTask.toSaveString() + System.lineSeparator());
        fw.close();
    }
}
