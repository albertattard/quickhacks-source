package quickhacks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static quickhacks.Calculator.sqrt;

@DisplayName("Calculator")
class CalculatorTest {

    @Test
    @DisplayName("should return the square root of the given positive number")
    void shouldReturnTheSquareRootOfTheGivenPositiveNumber(){
        assertEquals(4, sqrt(16), 0);
    }
}