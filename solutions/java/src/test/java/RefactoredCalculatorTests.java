import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import unit.tests.intro.Calculator;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static unit.tests.intro.Calculator.*;

class RefactoredCalculatorTests {
    private final Calculator calculator = new Calculator();

    @ParameterizedTest
    @MethodSource("supportedOperatorsTestCases")
    void supportOperations(int a, int b, String operator, int expectedResult) {
        assertThat(calculator.calculate(a, b, operator))
                .isEqualTo(expectedResult);
    }

    @Test
    void failWhenOperatorNotSupported() {
        assertThatThrownBy(() -> calculator.calculate(9, 3, "UnsupportedOperator"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not supported operator");
    }

    private static Stream<Arguments> supportedOperatorsTestCases() {
        return Stream.of(
                Arguments.of(9, 3, ADD, 12),
                Arguments.of(3, 76, MULTIPLY, 228),
                Arguments.of(9, 3, DIVIDE, 3),
                Arguments.of(9, 3, SUBTRACT, 6)
        );
    }
}