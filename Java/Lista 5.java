import java.time.LocalDate;
import java.time.Month;
import java.util.*;
//import javafx.util.Pair;

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

        private static ArrayList<Cost> getGeneralCosts(int size) {
            ArrayList<Cost> items = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                items.add(new Cost(
                        CostType
                                .values()[new Random()
                                .nextInt(CostType.values().length)],
                        LocalDate.of(
                                2022,
                                new Random().nextInt(12) + 1,
                                new Random().nextInt(28) + 1),
                        new Random().nextInt(5000)));
            }
            return items;
        }

        static ArrayList<Car> getCars(){
            ArrayList<Car> items = new ArrayList<>();
            items.add(new Car(
                    "Domowy",
                    "Skoda",
                    "Fabia",
                    2002,
                    getGeneralCosts(100)));
            items.add(new Car(
                    "Służbowy",
                    "BMW",
                    "Coupe",
                    2015,
                    getGeneralCosts(50)));
            items.add(new Car(
                    "Kolekcjonerski",
                    "Fiat",
                    "125p",
                    1985,
                    getGeneralCosts(10)));
            return items;
        }

    }
    public static class Car {
        private final String name;
        private final String brand;
        private final String model;
        private final int yearOfProduction;
        private ArrayList<Cost> costs;

        public Car(
                String name,
                String brand,
                String model,
                int yearOfProduction,
                ArrayList<Cost> costs
        ) {
            this.name = name;
            this.brand = brand;
            this.model = model;
            this.yearOfProduction = yearOfProduction;
            this.costs = costs;
        }

        public void setCosts(ArrayList<Cost> costs) {
            this.costs = costs;
        }

        public String getName() {
            return name;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public int getYearOfProduction() {
            return yearOfProduction;
        }

        public ArrayList<Cost> getCosts() {
            return costs;
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

        for(Map.Entry<Month, List<Cost>> entry : map.entrySet()){
            System.out.println(entry.getKey());
            for(Cost c : entry.getValue()){
                System.out.println(c.getDate().getDayOfMonth() + " " + c.getType() + " " + c.getAmount() + " zł");
            }
        }

    }


    public static HashMap<CostType, Integer> getFullCosts(ArrayList<Car> carList){
        HashMap<CostType, Integer> map = new HashMap<>();

        for(Car car : carList){
            ArrayList<Cost> costs = car.getCosts();
            for(Cost cost : costs){
                map.put(cost.getType(), map.getOrDefault(cost.getType(), 0) + cost.getAmount());
            }
        }

        return map;
    }
    public static void printFullCosts(HashMap<CostType, Integer> map){
        for(Map.Entry<CostType, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    public static void main(String[] args) {
        //Zadanie 1
        System.out.println(groupedCostMap(DataProvider.getGeneralCosts(5)) + "\n");

        //Zadanie 2
        printAllCosts(DataProvider.getGeneralCosts(5));

        //Zadanie 3

        //Zadanie 4
        System.out.println();
        printFullCosts(getFullCosts(DataProvider.getCars()));
    }
}
