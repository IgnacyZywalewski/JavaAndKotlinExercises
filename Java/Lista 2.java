import java.util.HashSet;
import java.util.HashMap;

public class Main {
    public static int missingNumber(int[] tab) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int missingNumber = -1;

        for (int num : tab) {
            if (num < 0) {
                System.out.println("Występuje liczba ujemna");
                break;
            }

            if (map.containsKey(num)) {
                System.out.println("Występuje element powtarzający się");
                return -1;
            } else {
                map.put(num, 1);
            }
        }

        for (int i = 0; i <= tab.length; i++) {
            if (!map.containsKey(i)) {
                missingNumber = i;
                break;
            }
        }

        return missingNumber;
    }

    public static void main(String[] args) {
        int[] numbers = {0, 1, 2, 3};
        System.out.println(missingNumber(numbers));
    }
}
