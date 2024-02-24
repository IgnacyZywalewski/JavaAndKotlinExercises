public class Main {
    public static String z1(int arg){
        StringBuilder ans = new StringBuilder();
        int[] numbers = {3, 5, 7, 11, 13, 15, 21};

        for (int number : numbers) {
            if (arg % number == 0) {
                if (number == 3) ans.append("trzy");
                if (number == 5) ans.append("piec");
                if (number == 7) ans.append("siedem");
                if (number == 11) ans.append("jedenascie");
                if (number == 13) ans.append("trzynascie");
                if (number == 15) ans.append("pienascie");
                if (number == 21) ans.append("dwadziesciajeden");
            }
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        System.out.println(z1(15015));
    }
}