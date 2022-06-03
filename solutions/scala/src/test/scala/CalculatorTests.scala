import org.scalatest.TryValues.convertTryToSuccessOrFailure
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CalculatorTests
  extends AnyFunSuite {

  test("calculator should support add") {
    // Arrange
    val calculator = new Calculator()

    // Act
    val result = calculator.calculate(9, 3, operator = Calculator.add)

    // Assert
    assert(result.success.value == 12)
  }

  test("calculator should support multiply") {
    // Arrange
    val calculator = new Calculator()

    // Act
    val result = calculator.calculate(3, 76, operator = Calculator.multiply)

    // Assert
    assert(result.success.value == 228)
  }

  test("calculator should support divide") {
    // Arrange
    val calculator = new Calculator()

    // Act
    val result = calculator.calculate(9, 3, operator = Calculator.divide)

    // Assert
    assert(result.success.value == 3)
  }

  test("calculator should support subtract") {
    // Arrange
    val calculator = new Calculator()

    // Act
    val result = calculator.calculate(9, 3, operator = Calculator.subtract)

    // Assert
    assert(result.success.value == 6)
  }

  test("calculator should fail when operator not supported") {
    val calculator = new Calculator()
    assert(
      calculator
        .calculate(9, 3, operator = "UnsupportedOperator")
        .failure
        .exception
        .getMessage == "Not supported operator"
    )
  }
}
