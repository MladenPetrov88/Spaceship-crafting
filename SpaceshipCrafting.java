import java.util.*;
import java.util.stream.Collectors;

public class SpaceshipCrafting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> chemicalLiquids = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> physicalItems = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        ArrayDeque<Integer> liquidsQueue = new ArrayDeque<>();
        ArrayDeque<Integer> physicalStack = new ArrayDeque<>();

        Map<String, Integer> materials = new TreeMap<>();
        addMaterial(materials, "Glass");
        addMaterial(materials, "Aluminium");
        addMaterial(materials, "Lithium");
        addMaterial(materials, "Carbon fiber");
        for (Integer chemicalLiquid : chemicalLiquids) {
            liquidsQueue.offer(chemicalLiquid);
        }

        for (Integer physicalItem : physicalItems) {
            physicalStack.push(physicalItem);
        }

        while (!liquidsQueue.isEmpty() && !physicalStack.isEmpty()) {
            int liquid = liquidsQueue.poll();
            int item = physicalStack.pop();
            int sum = liquid + item;
            switch (sum) {
                case 25:
                    materials.put("Glass",materials.get("Glass")+1);
                    break;
                case 50:
                    materials.put("Aluminium",materials.get("Aluminium")+1);
                    break;
                case 75:
                    materials.put("Lithium",materials.get("Lithium")+1);
                    break;
                case 100:
                    materials.put("Carbon fiber",materials.get("Carbon fiber")+1);
                    break;
                default:
                    physicalStack.push(item+3);
                    break;
            }

        }
        if (materials.get("Glass")>=1&&materials.get("Aluminium")>=1&&materials.get("Lithium")>=1&&materials.get("Carbon fiber")>=1) {
            System.out.println("Wohoo! You succeeded in building the spaceship!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to build the spaceship.");
        }

        if (liquidsQueue.isEmpty()){
            System.out.println("Liquids left: none");
        }else {
            System.out.print("Liquids left: ");
            printChemicals(liquidsQueue);
        }
        if (physicalStack.isEmpty()){
            System.out.println("Physical items left: none");
        }else {
            System.out.print("Physical items left: ");
            printPhysical(physicalStack);
        }

        materials.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(e-> System.out.println(e.getKey() + ": " + e.getValue()));
    }


    private static void addMaterial(Map<String, Integer> materials, String currentMaterial) {
        if (materials.containsKey(currentMaterial)) {
            int count = materials.get(currentMaterial);
            materials.put(currentMaterial, count + 1);
        } else {
            materials.put(currentMaterial, 0);

        }
    }
    private static void printChemicals (ArrayDeque<Integer> chemical){
        while (!chemical.isEmpty()){
            if (chemical.size()==1){
                System.out.print(chemical.poll());
            }else {
                System.out.print(chemical.poll()+", ");
            }
        }
        System.out.println();
    }
    private static void printPhysical(ArrayDeque<Integer> physical) {
        while (!physical.isEmpty()){
            if (physical.size()==1){
                System.out.print(physical.pop());
            }else {
                System.out.print(physical.pop()+", ");
            }
        }
        System.out.println();
    }
}