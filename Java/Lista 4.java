import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static BiFunction<String, Integer, String> duplicate = (s, i) -> String.valueOf(s).repeat(i);
    public static int suma(List<Integer> list){
        return list.stream()
                .filter(num -> num > 0)
                .mapToInt(Integer::intValue)
                .sum();
    }
    public static Map<String, Long> countElements(List<List<String>> inputlist){
        return inputlist.stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ));
    }
    public static List<Integer> evenPositiveSquare(List<Integer> inputList){
        return IntStream.range(0, inputList.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(inputList::get)
                .filter(x -> x > 0)
                .map(x -> x * x)
                .collect(Collectors.toList());
    }
    public static List<List<Object>> srt(List<String> inputList){
        Set<Character> firstLetters = inputList.stream()
                .map(s -> s.charAt(0))
                .collect(Collectors.toSet());

        Map<Character, List<String>> groupedMap = inputList.stream()
                .filter(s -> s.length() % 2 == 0)
                .sorted()
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0),
                        TreeMap::new,
                        Collectors.toList()
                ));

        List<List<Object>> result = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (firstLetters.contains(c)) {
                List<String> words = groupedMap.getOrDefault(c, Collections.emptyList());
                result.add(Arrays.asList(c, words));
            }
        }
        return result;
    }
    public static List<List<Integer>> perm(List<Integer> inputList){
        return inputList.isEmpty() ? Collections.singletonList(Collections.emptyList()) :
                IntStream.range(0, inputList.size())
                        .mapToObj(i -> {
                            int current = inputList.get(i);
                            List<Integer> remaining = new ArrayList<>(inputList);
                            remaining.remove(i);
                            return perm(remaining).stream().map(permutation -> {
                                List<Integer> newList = new ArrayList<>();
                                newList.add(current);
                                newList.addAll(permutation);
                                return newList;
                            });
                        })
                        .flatMap(Function.identity())
                        .collect(Collectors.toList());

    }
    public static int check(Integer N, List<Integer> inputList){
        Set<Integer> preamble = new HashSet<>(inputList.subList(0, N));
        int preambleStart = 0;
        int num;
        boolean found;

        for (int i = N; i < inputList.size(); i++) {
            found = false;
            for (int j = preambleStart; j < N - 1 + preambleStart; j++) {
                for(int k = j + 1; k < N + preambleStart; k++){
                    num = inputList.get(j) + inputList.get(k);
                    if (inputList.get(i).equals(num)) {
                        found = true;
                        break;
                    }
                }
                if(found) break;
            }

            if (!found) return inputList.get(i);

            preamble.remove(inputList.get(i - N));
            preamble.add(inputList.get(i));
            preambleStart++;
        }

        return -1;
    }

    public static void main(String[] args) {
        //Zadanie 1
        String s = "a";
        int i = 3;
        System.out.println(duplicate.apply(s, i));

        //Zadanie 2
        List<Integer> list2 = List.of( 1, -4, 12, 0, -3, 29, -150);
        System.out.println(suma(list2));

        //Zadanie 3
        List<List<String>> list3 = List.of(List.of("a", "b", "c"), List.of("c", "d", "f"), List.of("d", "f", "g"));
        System.out.println(countElements(list3));

        //Zadanie 4
        List<Integer> list4 = List.of(1, 2, 3, 5, -6, -1, -1, 2, 3);
        System.out.println(evenPositiveSquare(list4));

        //Zadanie 5
        List<String> list5 = List.of("cherry", "blueberry", "citrus", "apple", "apricot", "banana", "coconut");
        System.out.println(srt(list5));

        //Zadanie 6
        List<Integer> list6 = List.of(1, 2, 3);
        System.out.println(perm(list6));

        //Zadanie 7
        int N = 3;
        List<Integer> list7 =  List.of(1, 2, 3, 5, 7, 12, 30);
        System.out.println(check(N, list7));
    }
}
