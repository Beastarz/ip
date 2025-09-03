package clare.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import clare.exception.StringConvertExceptions;

public class TaskTest {
    @Test
    public void testConvert_todo() throws StringConvertExceptions {
        String data = "T|0|read book";
        Task task = Task.convert(data);
        assertTrue(task instanceof Todo);
        // Using public method to verify the status
        assertEquals("[ ]", task.getIsDoneStatus());
        assertEquals("read book", task.getTitle());
    }

    @Test
    public void testConvert_deadline() throws StringConvertExceptions {
        String data = "D|1|return book|2023-10-26";
        Task task = Task.convert(data);
        assertTrue(task instanceof Deadline);
        // Using public method to verify the status
        assertEquals("[X]", task.getIsDoneStatus());
        assertEquals("return book", task.getTitle());
    }

    @Test
    public void testConvert_event() throws StringConvertExceptions {
        String data = "E|0|project meeting|2023-11-01|2023-11-02";
        Task task = Task.convert(data);
        assertTrue(task instanceof Event);
        // Using public method to verify the status
        assertEquals("[ ]", task.getIsDoneStatus());
        assertEquals("project meeting", task.getTitle());
    }

    @Test
    public void testMarkDone() throws StringConvertExceptions {
        String data = "T|0|read book";
        Task task = Task.convert(data);
        task.markDone();
        // Using public methods to verify the new status
        assertEquals("[X]", task.getIsDoneStatus());
        assertEquals("1", task.getIsDoneInt());
    }

    @Test
    public void testMarkUndone() throws StringConvertExceptions {
        String data = "T|1|read book";
        Task task = Task.convert(data);
        task.markUndone();
        // Using public methods to verify the new status
        assertEquals("[ ]", task.getIsDoneStatus());
        assertEquals("0", task.getIsDoneInt());
    }
}
