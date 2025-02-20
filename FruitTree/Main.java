public class Main {
    public static void main(String[] args) {
        // Initialize TreeHelper with some growth rates
        TreeHelper treeHelper = new TreeHelper();
        treeHelper.addTree("Miraculus Fructus", 2);
        treeHelper.addTree("Oak", 1);
        treeHelper.addTree("Willow", 3);

        System.out.println("=== Tree Helper Configuration ===");
        treeHelper.displayAllTrees();
        System.out.println("\n=== Starting Tests ===");

        // Test base MiracleFruitTree
        System.out.println("\n--- Testing MiracleFruitTree ---");
        MiracleFruitTree baseTree = new MiracleFruitTree("Test Tree", "Test Garden", treeHelper);
        baseTree.test();

        // Test SentientMiracleFruitTree
        System.out.println("\n--- Testing SentientMiracleFruitTree ---");
        SentientMiracleFruitTree sentientTree = new SentientMiracleFruitTree(
                "Wise Tree",
                "Sacred Grove",
                treeHelper,
                0,  // emotional state
                5,  // defense
                15.0,  // telepathic range
                6,  // nature affinity
                "Deer",  // favorite visitor
                3,  // wake factor
                2   // emotional stability
        );
        sentientTree.test();

        // Demonstrate interaction between the two types
        System.out.println("\n=== Demonstrating Tree Interaction ===");
        baseTree.enchant();
        sentientTree.callOnNature();

        System.out.println("Base Tree Status:");
        System.out.println("- Life Stage: " + baseTree.getLifeStage());
        System.out.println("- Growth Bonus: " + baseTree.calculateGrowthBonus());

        System.out.println("\nSentient Tree Status:");
        System.out.println("- Consciousness: " + sentientTree.checkConsciousness());
        System.out.println("- Emotional State: " + sentientTree.getEmotionalState());
        System.out.println("- Defense Level: " + sentientTree.getDefense());
    }
}