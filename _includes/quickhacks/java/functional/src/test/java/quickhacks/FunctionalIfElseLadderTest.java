package quickhacks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Locale;
import java.util.Optional;
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

    @Test
    @DisplayName("should return optional empty if none of the if options evaluates to true")
    void shouldReturnOptionalEmptyIfNoneOfTheIfOptionsEvaluatesToTrue() {
        /* Given/When */
        final Optional<String> result = FunctionalIfElseLadder
                .ifTrue(alwaysFalse(), failIfInvoked())
                .elseIf(alwaysFalse(), failIfInvoked())
                .asOptional();

        /* Then */
        assertThat(result)
                .describedAs("An empty optional is returned")
                .isNotPresent();
    }

    @Test
    @DisplayName("should return map the first if option that evaluates to true")
    void shouldReturnMapTheFirstIfOptionThatEvaluatesToTrue() {
        /* Given/When */
        final Optional<String> result = FunctionalIfElseLadder
                .ifTrue(alwaysFalse(), failIfInvoked())
                .elseIf(alwaysTrue(), () -> EXPECTED_RESULT)
                .map(s -> s.toUpperCase(Locale.ROOT));

        /* Then */
        assertThat(result)
                .describedAs("An empty optional is returned")
                .isEqualTo(Optional.of(EXPECTED_RESULT.toUpperCase(Locale.ROOT)));
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
