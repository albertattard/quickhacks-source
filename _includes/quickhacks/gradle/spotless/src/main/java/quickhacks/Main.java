package quickhacks;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(final String[] args) {
        final PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        out.println("Hello spotless!!");
    }
}
