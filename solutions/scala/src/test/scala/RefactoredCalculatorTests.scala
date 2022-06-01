import org.scalatest.Assertion
import org.scalatest.TryValues.convertTryToSuccessOrFailure
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Try

class RefactoredCalculatorTests
  extends AnyFunSuite
    with Matchers {

  private val calculator = new Calculator()

  test("calculator should support add") {
    val result = calculator.calculate(9, 3, operator = Calculator.add)
    assertSuccess(result, 12)
  }

  test("calculator should support multiply") {
    val result = calculator.calculate(3, 76, operator = Calculator.multiply)
    assertSuccess(result, 228)
  }

  test("calculator should support divide") {
    val result = calculator.calculate(9, 3, operator = Calculator.divide)
    assertSuccess(result, 3)
  }

  test("calculator should support subtract") {
    val result = calculator.calculate(9, 3, operator = Calculator.subtract)
    assertSuccess(result, 6)
  }

  test("calculator should fail when operator not supported") {
    assert(
      calculator
        .calculate(9, 3, operator = "UnsupportedOperator")
        .failure
        .exception
        .getMessage == "Not supported operator"
    )
  }

  private def assertSuccess(result: Try[Int], expectedValue: Int): Assertion = {
    assert(result.success.value == expectedValue)
  }
}