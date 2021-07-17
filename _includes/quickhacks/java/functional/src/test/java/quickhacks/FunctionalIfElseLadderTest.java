package quickhacks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FunctionalIfElseLadderTest {

    private static final String EXPECTED_RESULT = "It should be me";

    @Test
    @DisplayName("should evaluate the first if option and ignoring the others")
    void shouldEvaluateTheFirstIfOptionAndIgnoringTheOthers() {
        /* Given/When */
        final String result = FunctionalIfElseLadder
                .ifTrue(alwaysTrue(), () -> EXPECTED_RESULT)
                .elseIf(alwaysTrue(), failIfInvoked())
                .orElse(failIfInvoked());

        /* Then */
        assertThat(result)
                .describedAs("The first if option should be executed")
                .isEqualTo(EXPECTED_RESULT);
    }

    @Test
    @DisplayName("should evaluate the first if option that evaluates to true and ignoring the others")
    void shouldEvaluateTheFirstIfOptionThatEvaluatesToTrueAndIgnoringTheOthers() {
        /* Given/When */
        final String result = FunctionalIfElseLadder
                .ifTrue(alwaysFalse(), failIfInvoked())
                .elseIf(alwaysTrue(), () -> EXPECTED_RESULT)
                .elseIf(alwaysTrue(), failIfInvoked())
                .orElse(failIfInvoked());

        /* Then */
        assertThat(result)
                .describedAs("The second if option should be executed")
                .isEqualTo(EXPECTED_RESULT);
    }

    @Test
    @DisplayName("should evaluate the else option as non of the if options are true")
    void shouldEvaluateTheElseOptionAsNonOfTheIfOptionsAreTrue() {
        /* Given/When */
        final String result = FunctionalIfElseLadder
                .ifTrue(alwaysFalse(), failIfInvoked())
                .elseIf(alwaysFalse(), failIfInvoked())
                .orElse(() -> EXPECTED_RESULT);

        /* Then */
        assertThat(result)
                .describedAs("The else option is executed as non of the if options evaluated to true")
                .isEqualTo(EXPECTED_RESULT);
    }

    private static BooleanSupplier alwaysTrue() {
        return () -> true;
    }

    private static BooleanSupplier alwaysFalse() {
        return () -> false;
    }

    private static Supplier<String> failIfInvoked() {
        return () -> {
            fail("This should not be invoked");
            return "This should not be invoked";
        };
    }
}
