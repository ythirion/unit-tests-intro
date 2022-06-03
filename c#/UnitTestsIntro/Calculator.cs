namespace UnitTestsIntro;

public static class Calculator
{
    public const string Add = "add";
    public const string Multiply = "multiply";
    public const string Divide = "divide";
    public const string Subtract = "subtract";

    private static readonly Dictionary<string, Func<int, int, int>> _supportedOperators =
        new()
        {
            {Add, (a, b) => a + b},
            {Multiply, (a, b) => a * b},
            {Divide, (a, b) => a / b},
            {Subtract, (a, b) => a - b}
        };
    public static int Calculate(int a, int b, string @operator) =>
        _supportedOperators.ContainsKey(@operator)
            ? _supportedOperators[@operator](a, b)
            : throw new ArgumentException("Not supported operator");
}