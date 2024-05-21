import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class Main {

    //Zadanie 1
    public static BigInteger factorial(int n, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<BigInteger>> futures = new ArrayList<>();

        int range = n / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * range + 1;
            int end = (i == numThreads - 1) ? n : (i + 1) * range;
            futures.add(executor.submit(new factorialTask(start, end)));
        }

        BigInteger result = BigInteger.ONE;
        for (Future<BigInteger> future : futures) {
            result = result.multiply(future.get());
        }

        executor.shutdown();
        return result;
    }
    private record factorialTask(int start, int end) implements Callable<BigInteger> {
        @Override
            public BigInteger call() {
                BigInteger result = BigInteger.ONE;
                for (int i = start; i <= end; i++) {
                    result = result.multiply(BigInteger.valueOf(i));
                }
                return result;
            }
        }


    //Zadanie 2
    public static BigDecimal calculateEulerNumber(int precision) {
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal e = BigDecimal.ONE;
        BigDecimal factorial = BigDecimal.ONE;

        for (int i = 1; i < precision; i++) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
            BigDecimal term = BigDecimal.ONE.divide(factorial, mc);
            if (term.compareTo(BigDecimal.ZERO) == 0) break;
            e = e.add(term);
        }

        return e;
    }


    //Zadanie 3
    public static int[] findNumberWithMostDivisors(int range, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<int[]>> futures = new ArrayList<>();

        int subRange = range / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * subRange + 1;
            int end = (i == numThreads - 1) ? range : (i + 1) * subRange;
            futures.add(executor.submit(new DivisorsTask(start, end)));
        }

        int maxDivisors = 0;
        int numberWithMaxDivisors = 0;

        for (Future<int[]> future : futures) {
            int[] result = future.get();
            if (result[1] > maxDivisors) {
                maxDivisors = result[1];
                numberWithMaxDivisors = result[0];
            }
        }

        executor.shutdown();
        return new int[]{numberWithMaxDivisors, maxDivisors};
    }
    private record DivisorsTask(int start, int end) implements Callable<int[]> {

        @Override
            public int[] call() {
                int maxDivisors = 0;
                int numberWithMaxDivisors = 0;

                for (int i = start; i <= end; i++) {
                    int divisorsCount = countDivisors(i);
                    if (divisorsCount > maxDivisors) {
                        maxDivisors = divisorsCount;
                        numberWithMaxDivisors = i;
                    }
                }

                return new int[]{numberWithMaxDivisors, maxDivisors};
            }

            private int countDivisors(int n) {
                int count = 0;
                for (int i = 1; i <= Math.sqrt(n); i++) {
                    if (n % i == 0) {
                        count++;
                        if (i != n / i) {
                            count++;
                        }
                    }
                }
                return count;
            }
        }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Zadanie 1
        System.out.println("Factorial of 5: " + factorial(5, 5));

        //Zadanie 2
        System.out.println("\nEuler's Number: " + calculateEulerNumber(17));

        //Zadanie 3
        int[] result = findNumberWithMostDivisors(100000, 5);
        System.out.println("\nNumber: " + result[0]);
        System.out.println("Number of divisors: " + result[1]);
    }
}
