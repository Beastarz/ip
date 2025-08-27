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
    public static void readFile(ArrayList<Task> tasks, String dataPath) throws FileNotFoundException, StringConvertExceptions {
        File f = new File(dataPath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            tasks.add(Task.convert(sc.nextLine()));
        }
    }

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

    public static void addData(Task newTask, String dataPath) throws IOException{
        FileWriter fw = new FileWriter(dataPath, true);
        fw.write(newTask.toSaveString() + System.lineSeparator());
        fw.close();
    }
}
