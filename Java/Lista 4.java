import java.util.*;

public class Main {

    public static String duplicate(String s, int i){
        return String.valueOf(s).repeat(i);
    }

    public static int suma(List<Integer> list){
        return list.stream()
                .filter(num -> num > 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static Map<String, Integer> countElements (List<List<String>> list){
        return list.stream()
                .flatMap(Collection::stream)
                .collect(
                        HashMap::new,
                        (map, element) -> map.merge(element, 1, Integer::sum),
                        HashMap::putAll
                );
    }

    public static void main(String[] args) {
        //Zadanie 1
        String s = "a";
        int i = 3;
        Runnable runnable = () -> System.out.println(duplicate(s, i));
        runnable.run();

        //Zadanie 2
        List<Integer> list = List.of( 1, -4, 12, 0, -3, 29, -150);
        System.out.println(suma(list));

        //Zadanie 3
        List<List<String>> list1 = List.of(List.of("a", "b", "c"), List.of("c", "d", "f"), List.of("d", "f", "g"));
        System.out.println(countElements(list1));

    }
}
