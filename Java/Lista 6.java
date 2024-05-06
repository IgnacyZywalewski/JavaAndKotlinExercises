import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class Main {

    public static <T> List<T> getTail(List<T> list) {
        return list.subList(1, list.size());
    }

    public static <T> T getHead(List<T> list) {
        return list.getFirst();
    }

    public static <T> boolean isSorted(List<T> list, BiPredicate<T, T> ordered) {
        for (int i = 0; i < list.size() - 1; i++) {
            T current = list.get(i);
            T next = list.get(i + 1);
            if (!ordered.test(current, next)) {
                return false;
            }
        }
        return true;
    }

    public static List<Number> square(List<Number> numbers) {
        List<Number> result = new ArrayList<>();
        for (int i = 1; i < numbers.size(); i += 2) {
            Number num = numbers.get(i);
            if (num.doubleValue() > 0) {
                double squared = Math.pow(num.doubleValue(), 2);
                result.add(squared);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        List<Number> numbers = List.of(1, 2, 3.5, 5, -6, 1, 1);

        //Zadanie 1
        System.out.println("Head: " + getHead(intList));
        System.out.println("Tail: " + getTail(intList));

        //Zadanie 2
        BiPredicate<Integer, Integer> isAscending = (a, b) -> a <= b;
        System.out.println(isSorted(intList, isAscending));

        BiPredicate<String, String> isLexicographic = (a, b) -> a.compareTo(b) <= 0;
        System.out.println(isSorted(stringList, isLexicographic));

        //Zadanie 3
        System.out.println(square(numbers));
    }
}
