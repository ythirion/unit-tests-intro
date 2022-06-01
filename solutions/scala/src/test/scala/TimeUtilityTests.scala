import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.LocalTime

class TimeUtilityTests
  extends AnyFlatSpec
    with Matchers
    with MockFactory {

  private val clockStub: Clock = stub[Clock]

  "at 6 AM" should "be Morning" in {
    (clockStub.time _).when().returns(LocalTime.of(6, 5))
    TimeUtility.timeOfDay(clockStub) should be("Morning")
  }

  "at 1 PM" should "be Afternoon" in {
    (clockStub.time _).when().returns(LocalTime.of(13, 0))
    TimeUtility.timeOfDay(clockStub) should be("Afternoon")
  }

  "at 2 AM" should "be Night" in {
    (clockStub.time _).when().returns(LocalTime.of(1, 0))
    TimeUtility.timeOfDay(clockStub) should be("Night")
  }

  "at 11 PM" should "be Evening" in {
    (clockStub.time _).when().returns(LocalTime.of(23, 0))
    TimeUtility.timeOfDay(clockStub) should be("Evening")
  }
}