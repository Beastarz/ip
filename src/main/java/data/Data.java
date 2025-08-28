package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;
import exception.StringConvertExceptions;

public class Data {
    /**
     * Read data from data path and load task into tasks array
     * @param tasks the array of task to be filled
     * @param dataPath the source of data
     * @throws FileNotFoundException exception when the file is not found
     * @throws StringConvertExceptions exception if there is string conversion error on the command
     */
    public static void readFile(ArrayList<Task> tasks, String dataPath) throws FileNotFoundException, StringConvertExceptions {
        File f = new File(dataPath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            tasks.add(Task.convert(sc.nextLine()));
        }
    }

    /**
     * Overwrites the file from data path from tasks array
     * @param tasks the array of tasks
     * @param dataPath the source of data file
     * @throws IOException exception when the file has problem
     */
    public static void rewriteData(ArrayList<Task> tasks, String dataPath) throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        StringBuilder data = new StringBuilder();
        for (Task t : tasks) {
            data.append(t.toSaveString());
            data.append(System.lineSeparator());
        }
        fw.write(data.toString());
        fw.close();
    }

    /**
     * Add new line of data to the file
     * @param newTask the new data to be added
     * @param dataPath the source of data file
     * @throws IOException exception when the file has problem
     */
    public static void addData(Task newTask, String dataPath) throws IOException{
        FileWriter fw = new FileWriter(dataPath, true);
        fw.write(newTask.toSaveString() + System.lineSeparator());
        fw.close();
    }
}
