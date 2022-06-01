# unit-tests-intro
Introduction to Unit Tests in scala, java, php, angular and c#



```scala
class TimeUtilityTests
  extends AnyFlatSpec
    with Matchers {

  "time description" should "be Afternoon" in {
    TimeUtility.timeOfDay should be("Afternoon")
  }
}
```

- This test is not repeatable because the design is coupled to `LocalTime.now()`
  - We need to isolate it to be able to test this unitary
