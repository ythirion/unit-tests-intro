# Step-by-step - Test it or Die Trying (35')
## Calculator
- We start by a first simple test case : `9 + 3 = 12`
  - We create a `CalculatorTests` class
  - We use the `3A` pattern to describe it
  - We assert the expected result

```java
@Test
void supportAdd()
{
    // Arrange
    var calculator = new Calculator();

    // Act
    var result = calculator.calculate(9, 3, Calculator.ADD);

    // Assert
    assertEquals(12, result);
}
```
- Now that we have 1 test case we can repeat for others
```text
✅ 9 + 3 = 12
3 x 76 = 228
9 / 3 = 3
9 - 3 = 9
Unsupported operator should fail
```

- Other test cases
```java
@Test
void supportMultiply()
{
    // Arrange
    var calculator = new Calculator();

    // Act
    var result = calculator.calculate(3, 76, Calculator.MULTIPLY);

    // Assert
    assertEquals(228, result);
}

@Test
void supportDivide()
{
    // Arrange
    var calculator = new Calculator();

    // Act
    var result = calculator.calculate(9, 3, Calculator.DIVIDE);

    // Assert
    assertEquals(3, result);
}

@Test
void supportSubtract()
{
    // Arrange
    var calculator = new Calculator();

    // Act
    var result = calculator.calculate(9, 3, Calculator.SUBTRACT);

    // Assert
    assertEquals(6, result);
}
```
- Update our test cases
```text
✅ 9 + 3 = 12
✅ 3 x 76 = 228
✅ 9 / 3 = 3
✅ 9 - 3 = 9
Unsupported operator should fail
```

- Let's implement the failure test
```java
@Test
void failWhenOperatorNotSupported()
{
    var calculator = new Calculator();
    var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(9, 3, "UnsupportedOperator"));
    assertEquals("Not supported operator", exception.getMessage());
}
```

### Refactor the tests
> You should pay the same attention to your tests as to your production code.

- Let's remove duplication
  - assertion code is duplicated
  - we instantiate 1 `Calculator` per test, but we can use the same (no state inside)
- In `junit` for this kind of test we can use `Parameterized tests`
  - We define a `[Theory]` method that takes some data as input `[InlineData]`
  - We adapt the test method as well `public void SupportOperations(int a, int b, string @operator, int expectedResult)`
- We can use a library that simplify assertion readability as well called [assertJ](https://assertj.github.io/doc/)

```java
public class RefactoredCalculatorShould
{
    private readonly Calculator _calculator;

    public RefactoredCalculatorShould()
    {
        _calculator = new Calculator();
    }

    [Theory]
    [InlineData(9, 3, Add, 12)]
    [InlineData(3, 76, Multiply, 228)]
    [InlineData(9, 3, Divide, 3)]
    [InlineData(9, 3, Subtract, 6)]
    public void SupportOperations(int a, int b, string @operator, int expectedResult) =>
        _calculator
            .Calculate(a, b, @operator)
            .Should()
            .Be(expectedResult);

    [Fact]
    public void FailWhenOperatorNotSupported()
    {
        var calculate = () => _calculator.Calculate(9, 3, "UnsupportedOperator");
    
        calculate.Should()
            .Throw<ArgumentException>()
            .WithMessage("Not supported operator");
    }
}
```

> There is one Edge case not yet supported, what happens if we divide by 0? 

```text
✅ 9 + 3 = 12
✅ 3 x 76 = 228
✅ 9 / 3 = 3
✅ 9 - 3 = 9
✅ Unsupported operator should fail
Divide by 0 should fail
```

## Time
- Write at least one test for it

```java
public class TimeUtility
{
    public string GetTimeOfDay()
    {
        var time = DateTime.Now;

        return time.Hour switch
        {
            >= 0 and < 6 => "Night",
            >= 6 and < 12 => "Morning",
            >= 12 and < 18 => "Afternoon",
            _ => "Evening"
        }
    }
}
```

- Here is the simplest test we can write
  - Which problem will you encounter?
```java
public class TimeUtilityShould
{
    [Fact]
    public void BeAfternoon() =>
        new TimeUtility()
            .GetTimeOfDay()
            .Should()
            .Be("Afternoon");
}
```

- This test is not repeatable because the design is coupled to `LocalTime.now()`
    - We need to isolate it to be able to test this unitary
    - A few solutions here :
        - Pass a `LocalTime` as method arg
        - Pass a `Clock` which will provide a `time()`method that we will be able to substitute
        - Pass a function `clock: Unit => LocalTime`

- Identify some examples
```text
6:05AM -> Morning
1:00AM -> Night
1PM -> Afternoon
11PM -> Evening
```        

### Use an IClock interface
- Adapt the `TimeUtility` to inject an `IClock` collaborator
  - Generate your code from usage

```java
public class TimeUtility
{
    private readonly IClock _clock;

    public TimeUtility(IClock clock) => _clock = clock;

    public string GetTimeOfDay()
    {
        return _clock.Now().Hour switch
        {
            >= 0 and < 6 => "Night",
            >= 6 and < 12 => "Morning",
            >= 12 and < 18 => "Afternoon",
            _ => "Evening"
        };
    }
}

public interface IClock
{
    DateTime Now();
}
```

- Now our code has no `hardcoded` dependency anymore
- We need to adapt our tests
  - How to provide a `IClock` in the given state for our test cases?
  - `Test Doubles` is our solution

- To use TestDoubles we need to use `moq`
  - Instantiate a `IClock` mock
  - We implement our first test case

```java
[Fact]
public void ReturnMorningFor6AM()
{
    var clockMock = new Mock<IClock>();
    clockMock.Setup(c => c.Now())
        .Returns(new DateTime(2022, 12, 1, 6, 5, 0, 0));
    
    new TimeUtility(clockMock.Object)
        .GetTimeOfDay()
        .Should()
        .Be("Morning");
}
```

```text
✅ 6:05AM -> Morning
1:00AM -> Night
1PM -> Afternoon
11PM -> Evening
```

- Implement others by using a `Theory` once again
```java
public class TimeUtilityShould
{
    [Theory]
    [InlineData(0, "Night")]
    [InlineData(4, "Night")]
    [InlineData(6, "Morning")]
    [InlineData(9, "Morning")]
    [InlineData(12, "Afternoon")]
    [InlineData(17, "Afternoon")]
    [InlineData(18, "Evening")]
    [InlineData(23, "Evening")]
    public void GetADescriptionAtAnyTime(int hour, string expectedDescription)
    {
        var clockMock = new Mock<IClock>();
        clockMock.Setup(c => c.Now())
            .Returns(hour.ToDateTime());
        
        new TimeUtility(clockMock.Object)
            .GetTimeOfDay()
            .Should()
            .Be(expectedDescription);
    }
    
    [Fact]
    public void ReturnMorningFor6AM()
    {
        var clockMock = new Mock<IClock>();
        clockMock.Setup(c => c.Now())
            .Returns(new DateTime(2022, 12, 1, 6, 5, 0, 0));
        
        new TimeUtility(clockMock.Object)
            .GetTimeOfDay()
            .Should()
            .Be("Morning");
    }
}

internal static class TestExtensions
{
    public static DateTime ToDateTime(this int hour) 
        => new(2022, 12, 1, hour, 0, 0, 0);
}
```