package clare.exception;

public class StringConvertExceptions extends Exception{
    private final String str;

    public StringConvertExceptions(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "String conversion duke.exception: error processing string " + str;
    }
}
