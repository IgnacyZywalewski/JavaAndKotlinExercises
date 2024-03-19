import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Main {
    public enum CostType {
        REFUELING("Tankowanie"),
        SERVICE("Serwis"),
        PARKING("Parking"),
        INSURANCE("Ubezpieczenie"),
        TICKET("Mandat");

        private final String costType;

        CostType(String costType) {
            this.costType = costType;
        }

        public String getCostType() {
            return costType;
        }
    }
    public static class Cost {
        private final CostType type;
        private final LocalDate date;
        private final int amount;

        public Cost(CostType type, LocalDate date, int amount){
            this.type = type;
            this.date = date;
            this.amount = amount;
        }

        public CostType getType() {
            return type;
        }

        public LocalDate getDate() {
            return date;
        }

        public int getAmount() {
            return amount;
        }
    }
    public static final class DataProvider {
        private DataProvider() {
        }

        public static ArrayList<Cost> getGeneralCosts() {
            ArrayList<Cost> items = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                items.add(new Cost(
                        CostType.values()[new Random().nextInt(CostType.values().length)],
                        LocalDate.of(
                                2022,
                                new Random().nextInt(12) + 1,
                                new Random().nextInt(28) + 1),
                        new Random().nextInt(5000)));
            }
            return items;
        }
    }


    public static Map<Month, ArrayList<String>> groupedCostMap(ArrayList<Cost> costs){
        Map<Month, ArrayList<String>> map = new TreeMap<>();

        for(Cost c : costs){
            Month month = c.getDate().getMonth();
            String s = "Cost(type=" + c.getType().toString() + ", date=" + c.getDate().toString() + ", amount=" + c.getAmount() + ")";
            map.computeIfAbsent(month, k -> new ArrayList<>()).add(s);
        }
        return map;
    }

    public static void printAllCosts(ArrayList<Cost> costs){
        Map<Month, List<Cost>> map = new TreeMap<>();

        for(Cost c : costs){
            Month month = c.getDate().getMonth();
            map.computeIfAbsent(month, k -> new ArrayList<>()).add(c);
        }
        
    }

    public static void main(String[] args) {
        //Zadanie 1
        System.out.println(groupedCostMap(DataProvider.getGeneralCosts()));

        //Zadanie 2
        printAllCosts(DataProvider.getGeneralCosts());
    }
}
