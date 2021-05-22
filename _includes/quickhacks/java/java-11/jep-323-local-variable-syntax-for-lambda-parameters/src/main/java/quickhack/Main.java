package quickhack;

public class Main {

    @FunctionalInterface
    interface BinaryOperator<T, R> {
        R apply(T a, T b);
    }

    public static void main(final String[] args) {
        compute((a, b) -> a + b);
        compute((Integer a, Integer b) -> a - b);
        compute((var a, var b) -> a - b); /* Supported from Java 11 onwards */
    }

    private static void compute(BinaryOperator<Integer, Integer> operator){
        System.out.printf("The result is: %d%n", operator.apply(20, 22));
    }
}
