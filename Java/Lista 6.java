import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Main {

    //Zadanie 1
    public static <T> List<T> getTail(List<T> list) {
        return list.subList(1, list.size());
    }
    public static <T> T getHead(List<T> list) {
        return list.getFirst();
    }

    //Zadanie 2
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

    //Zadanie 3
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

    //Zadanie 4
    public static <T> List<T> tailToHead(List<T> list){
        if (list == null || list.isEmpty()){
            throw new IllegalStateException("Empty list");
        }

        T temp = list.getFirst();
        list.set(0, list.getLast());
        list.set(list.size() - 1, temp);

        return list;
    }

    //Zadanie 5
    public static <T> List<T> setHead(List<T> list, T newHead){
        list.set(0, newHead);
        return list;
    }

    //Zadanie 6
    public static <T> List<T> dropWhile(List<T> list, Predicate<T> predicate){
        List<T> result = new ArrayList<>(list);
        int n = result.size();
        for (int i = 0; i < n; i++) {
            if (!predicate.test(result.get(i))){
                break;
            }
            result.remove(i);
            i--;
            n--;
        }
        return result;
    }


    public static void main(String[] args) {
        List<Integer> numList = List.of(1, 2, 3, 4, 5);

        //Zadanie 1
        System.out.println("Head: " + getHead(numList));
        System.out.println("Tail: " + getTail(numList));

        //Zadanie 2
        BiPredicate<Integer, Integer> isAscending = (a, b) -> a <= b;
        System.out.println(isSorted(numList, isAscending));

        BiPredicate<String, String> isLexicographic = (a, b) -> a.compareTo(b) <= 0;
        System.out.println(isSorted(Arrays.asList("apple", "banana", "cherry"), isLexicographic));

        //Zadanie 3
        System.out.println(square(List.of(1, 2, 3.5, 5, -6, 1, 1)));

        //Zadanie 4
        System.out.println(tailToHead(Arrays.asList(1, 2, 3)));

        //Zadanie 5
        System.out.println(setHead(Arrays.asList(1, 2, 3), 5));

        //Zadanie 6
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 3, 2, 1, 9);
        Predicate<Integer> lessThanThree = (i) -> i < 3;
        System.out.println(dropWhile(list, lessThanThree));
    }
}
