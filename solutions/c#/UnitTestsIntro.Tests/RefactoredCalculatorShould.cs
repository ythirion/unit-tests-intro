using System;
using FluentAssertions;
using Xunit;
using static UnitTestsIntro.Calculator;

namespace UnitTestsIntro.Tests
{
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
}