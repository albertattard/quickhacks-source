package quickhacks;

public class Calculator {

    public static double sqrt(final double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot compute the square root of a negative number");
        }

        return Math.sqrt(number);
    }
}
