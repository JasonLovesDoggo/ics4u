import java.util.HashMap;
import java.util.Map;

public class TreeHelper {

    private final Map<String, Integer> growthRates;

    public TreeHelper() {
        this.growthRates = new HashMap<>();
    }

    // Add a new tree type and its growth rate
    public void addTree(String treeType, int growthRate) {
        growthRates.put(treeType, growthRate);
    }

    public int getGrowthRate(String treeType) {
        return growthRates.getOrDefault(treeType, 1); // Returns 1 if tree type is not found
    }

    public void displayAllTrees() {
        System.out.println("Tree Types and Growth Rates:");
        for (Map.Entry<String, Integer> entry : growthRates.entrySet()) {
            System.out.println("Tree: " + entry.getKey() + ", Growth Rate: " + entry.getValue());
        }
    }
}
