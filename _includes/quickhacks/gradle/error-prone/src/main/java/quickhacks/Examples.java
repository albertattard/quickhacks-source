package quickhacks;

import java.time.LocalDateTime;

public class Examples {

    public static void main(final String[] args) {
        int a = 7;
        int b = 42;
        logf("%s is the answer to the ultimate question of life, the universe, and everything", b);

        String text = getString();
        printLength(text);
    }

    private static String getString() {
        return null;
    }

    private static void printLength(String string) {
        System.out.printf("The string is %d long", string.length());
    }

    private static void logf(final String pattern, final Object... params) {
        log(String.format(pattern, params));
    }

    private static void log(final String message) {
        System.out.printf("[%tT] %s%n", System.currentTimeMillis(), message);
    }
}
