import java.util.*;

public class Main {
    public static void numberOfOccurrences(String word) {
        String[] array = word.split("[^\\p{L}]+");
        Map<String, Integer> map = new HashMap<>();

        for (String s : array) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (String w : map.keySet()) {
            System.out.println("Word: '" + w + "' occurs " + map.get(w) + " times.");
        }

    }

    public static Set<Integer> findDuplicates(int[] args) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : args) {
            if (!set.add(num)) {
                duplicates.add(num);
            }
        }
        return duplicates;
    }

    public static Map<Integer, Boolean> addToBoolean() {
        HashMap<Integer, Boolean> map = new HashMap<>();

        for(int i = 1; i <= 20; i++){
            if(i % 2 == 0){
                map.put(i, true);
            }
            else map.put(i, false);
        }

        return map;
    }

    public static void main(String[] args) {
        //Zadanie 1
        String sentence = "To jest przykładowe zdanie. To zdanie jest tylko przykładowe.";
        numberOfOccurrences(sentence);

        //Zadanie 2
        int[] list = {0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3, 3, 3};
        System.out.println(findDuplicates(list));

        //Zadanie 3
        System.out.println(addToBoolean());
    }
}
