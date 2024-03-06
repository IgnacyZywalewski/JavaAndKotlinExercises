import java.util.HashMap;
import java.util.Map;

public class Main {
    public static String checkDivisibility(int arg){
        StringBuilder ans = new StringBuilder();
        int[] numbers = {3, 5, 7, 11, 13, 15, 21};
        Map<Integer, String> map = new HashMap<>();

        map.put(3, "trzy");
        map.put(5, "piec");
        map.put(7, "siedem");
        map.put(11, "jedenascie");
        map.put(13, "trzynascie");
        map.put(15, "pienascie");
        map.put(21, "dwadziesciajeden");

        for (int number : numbers) {
            if (arg % number == 0) {
                ans.append(map.get(number));
            }
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        System.out.println(checkDivisibility(15015));
    }
}
