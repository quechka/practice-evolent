import java.util.stream.IntStream;

public class Task2 {
    public static long getArithmeticProgressionSum(int a, int b) {
        return IntStream.range(a, b)
                .asLongStream()
                .sum();
    }

    public static void main(String[] args) {
        int a = 10_000_000;
        int b = 1_000_000_000;
        long result = getArithmeticProgressionSum(a, b);
        System.out.println(result);
    }
}