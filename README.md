# unit-tests-intro
Introduction to Unit Tests in scala, java, php, angular and c#



```scala
object TimeUtility {
  def timeOfDay: String = {
    LocalTime.now() match {
      case night if night.getHour >= 0 && night.getHour < 6 => "Night"
      case morning if morning.getHour >= 6 && morning.getHour < 12 => "Morning"
      case afternoon if afternoon.getHour >= 12 && afternoon.getHour < 18 => "Afternoon"
      case _ => "Evening"
    }
  }
}
```

- Write at least one test for it
  - Which problem will you encounter?

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
  - A few solutions here :
    - Pass a `LocalTime` as method arg
    - Pass a `Clock` which will provide a `time()`method that we will be able to substitute
    - Pas a function `clock: Unit => LocalTime`
