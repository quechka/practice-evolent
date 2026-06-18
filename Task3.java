import java.util.Scanner;
class Multiplier {
    public double multiply(double a, double b) {
        return a * b;
    }
}
class Divider {
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Нельзя делить на ноль!");
        }
        return a / b;
    }
}
public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число a: ");
        double a = scanner.nextDouble();
        System.out.print("Введите число b: ");
        double b = scanner.nextDouble();
        Multiplier multiplier = new Multiplier();
        Divider divider = new Divider();


        System.out.println("Результат сложения a и b: " + (a + b));
        System.out.println("Результат вычитания a и b: " + (a - b));
        System.out.println("Результат умножения a и b: " + multiplier.multiply(a, b));
        try {
            System.out.println("Результат деления a и b: " + divider.divide(a, b));
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }
}