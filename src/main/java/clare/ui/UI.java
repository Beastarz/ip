package clare.ui;

public class UI {
    private final static String DIVIDER = "----------------------------------------";

    private static void clareSays(String msg) {
        System.out.println(DIVIDER);
        System.out.println(msg);
        System.out.println(DIVIDER);
    }

    /**
     * prints out the welcome message
     */
    public void welcome() {
        String welcomeText = "Hello dear, I am Clare!\nSo happy to see you today.\nWhat can I help?";
        clareSays(welcomeText);
    }

    /**
     * print put the farewell message
     */
    public void farewell() {
        String farewellText = "Bye dear. I will miss you!";
        clareSays(farewellText);
    }

    /**
     * prints out the msg given in the ui format
     * @param msg the message to be printed out
     */
    public void showMessage(String msg) {
        clareSays(msg);
    }
}
