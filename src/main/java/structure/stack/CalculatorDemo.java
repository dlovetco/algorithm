package structure.stack;

public class CalculatorDemo {

    public static void main(String[] args) {
        Calculator calculator = new Calculator("(10+2)*(30/6)");
        int result = calculator.calculate();
        System.out.println(result);
    }
}
