package quickhack;

import java.lang.reflect.Field;

public class Main {

    private static class NestedClass {
        private String privateField;
    }

    public static void main(final String[] args) throws NoSuchFieldException, IllegalAccessException {
        printJavaVersion();

        final NestedClass object = new NestedClass();
        object.privateField = "Accessing the field directly";
        printFieldValue(object);

        final Field field = NestedClass.class.getDeclaredField("privateField");
        field.set(object, "Accessing the field using reflection"); /* Fails with versions prior to Java 11 */
        printFieldValue(object);
    }

    private static void printFieldValue(NestedClass object) {
        System.out.printf("The field value is: %s%n", object.privateField);
    }

    private static void printJavaVersion() {
        final String version = System.getProperty("java.version");
        System.out.printf("Java version is: %s%n", version);
    }
}
