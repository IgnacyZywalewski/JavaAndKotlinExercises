import java.math.BigInteger;
import java.util.concurrent.*;

public class Main {
    public static BigInteger factorial(int n) throws InterruptedException, ExecutionException {
        if (n < 0) {
            throw new IllegalArgumentException("Liczba musi być nieujemna");
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        int numTasks = Math.min(n, Runtime.getRuntime().availableProcessors());
        int taskSize = n / numTasks;

        CompletionService<BigInteger> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < numTasks; i++) {
            int start = i * taskSize + 1;
            int end = (i == numTasks - 1) ? n : (i + 1) * taskSize;
            completionService.submit(() -> calculateFactorial(start, end));
        }

        BigInteger result = BigInteger.ONE;

        for (int i = 0; i < numTasks; i++) {
            result = result.multiply(completionService.take().get());
        }

        executor.shutdown();

        return result;
    }

    private static BigInteger calculateFactorial(int start, int end) {
        BigInteger result = BigInteger.ONE;
        for (int i = start; i <= end; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Zadanie 1
        int n = 5;
        BigInteger factorialResult = factorial(n);
        System.out.println("Silnia z " + n + " wynosi: " + factorialResult);

    }
}
