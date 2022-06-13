
public class Calculator {

    private static Map<String, (Integer, Integer) -> Integer> supportedOperators = new HashMap(
        ("add", (a, b) -> a + b),
        ("multiply", (a, b) -> a * b),
        ("divide", (a, b) -> a / b),
        ("substract", (a, b) -> a - b)
    )

    public Try<Integer> calculate(Integer a, Integer b, String operator) {

        switch(this.supportedOperators.get(operator)) {
            
        }


    }



}