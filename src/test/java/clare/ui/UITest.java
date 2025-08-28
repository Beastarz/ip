package clare.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final static String DIVIDER = "----------------------------------------";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testWelcome() {
        UI ui = new UI();
        ui.welcome();
        String expectedOutput =
                "Hello dear, I am Clare!\n";
        String output = outContent.toString();
        assertTrue(output.contains(expectedOutput));
    }

    @Test
    void testFarewell() {
        UI ui = new UI();
        ui.farewell();
        String expectedOutput = "Bye dear. I will miss you!";
        String output = outContent.toString();
        assertTrue(output.contains(expectedOutput));
    }

    @Test
    void testShowMessage() {
        UI ui = new UI();
        String message = "This is a test message.";
        ui.showMessage(message);
        String output = outContent.toString();
        assertTrue(output.contains(message));
    }
}