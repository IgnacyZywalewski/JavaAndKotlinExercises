import java.util.HashMap;

public class Main {
    public static int missingNumber(int[] tab) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : tab) {
            if (num < 0) {
                System.out.println("Występuje liczba ujemna");
                return -1;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        if(map.size() != tab.length){
            System.out.println("Występuje element powtarzający się");
            return -1;
        }

        int missingNumber = -1;
        int flag = 0;

        for (int i = 0; i <= tab.length; i++) {
            if (!map.containsKey(i)) {
                flag++;
                missingNumber = i;
            }
        }

        if(flag > 1){
            System.out.println("Brakuje więcej niż jednego elementu");
            return -1;
        }

        return missingNumber;
    }
    public static Boolean isPalindrome(String word){
        int n = word.length();
        for(int i = 0; i < n/2; i++){
            if(word.charAt(i) != word.charAt(n - 1 - i)){
                return false;
            }
        }
        return true;
    }
    public static void printPascal(int height){
        for (int line = 1; line <= height; line++) {
            for (int j = 0; j <= height - line; j++) {
                System.out.print(" ");
            }
            int c = 1;
            for (int i = 1; i <= line; i++) {
                System.out.print(c + " ");
                c = c * (line - i) / i;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{0, 1, 2, 3, 4, 5};
        System.out.println(missingNumber(numbers));

        String word = "abba";
        System.out.println(isPalindrome(word));

        printPascal(4);
    }
}
