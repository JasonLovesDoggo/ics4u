public class MiracleFruitTree {
    // Immutable attributes
    /** Scientific species name of the tree */
    private final String species;
    /** Geographic location where this species originated */
    private final String origin;
    /** Maximum number of fruits the tree can bear at once */
    private final int maxFruitCapacity;
    /** Base rate at which the tree grows naturally */
    private final double baseGrowthRate;
    /** Date when the tree was planted */
    private final String plantingDate;

    // Mutable attributes
    /** Current name given to the tree */
    private String name;
    /** Current location where the tree is planted */
    private String location;
    /** Current height of the tree in meters */
    private double height;
    /** Current width/spread of the tree in meters */
    private double width;
    /** Whether the tree is currently under magical enchantment */
    private boolean isEnchanted;

    // Default constructor
    public MiracleFruitTree(TreeHelper treeHelper) {
        this("Unnamed Tree", "Garden of Wonders", treeHelper);
    }

    // Main constructor
    public MiracleFruitTree(String name, String location, TreeHelper treeHelper) {
        this.species = "Miraculus Fructus";
        this.origin = "Mystic Valley";
        this.maxFruitCapacity = 100;
        this.baseGrowthRate = treeHelper.getGrowthRate(species);
        this.plantingDate = java.time.LocalDate.now().toString();

        this.name = name;
        this.location = location;
        this.height = 1.0;
        this.width = 0.5;
        this.isEnchanted = false;
    }

    // Behaviors - Changes of attributes
    public void grow() {
        double growthBonus = calculateGrowthBonus();
        this.height += growthBonus;
        this.width += growthBonus * 0.5;
    }

    public void relocate(String newLocation) {
        this.location = newLocation;
        this.height *= 0.9; // Height reduces when relocated
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public void enchant() {
        this.isEnchanted = true;
    }

    public void disenchant() {
        this.isEnchanted = false;
    }

    // Behaviors - Access to attributes
    public String getSpecies() {
        return species;
    }

    public String getOrigin() {
        return origin;
    }

    public int getMaxFruitCapacity() {
        return maxFruitCapacity;
    }

    public double getBaseGrowthRate() {
        return baseGrowthRate;
    }

    public String getPlantingDate() {
        return plantingDate;
    }

    // Behaviors - Changes of behavior
    public double calculateGrowthBonus() {
        return isEnchanted ? baseGrowthRate * 2 : baseGrowthRate;
    }

    public boolean canRelocate() {
        return height < 10.0 && isHealthy() && !getLifeStage().equals("Mature"); // Can't relocate if it's too tall, unhealthy, or already mature
    }

    public String getLifeStage() {
        if (height < 2.0) return "Sapling";
        if (height < 5.0) return "Young";
        return "Mature";
    }

    public double calculateShadowArea() {
        return Math.PI * width * width;
    }

    public boolean isHealthy() {
        return height >= width * 1.5;
    }
    /**
     * Tests all major functionalities of the MiracleFruitTree class
     * @return boolean indicating if all tests passed
     */
    public boolean test() {
        boolean allTestsPassed = true;
        System.out.println("Starting MiracleFruitTree tests...\n");

        // Test 1: Initial state
        System.out.println("Test 1: Checking initial state");
        if (height == 1.0 && width == 0.5 && !isEnchanted) {
            System.out.println("✓ Initial state test passed");
        } else {
            System.out.println("✗ Initial state test failed");
            allTestsPassed = false;
        }

        // Test 2: Growth
        System.out.println("\nTest 2: Testing growth");
        double initialHeight = height;
        grow();
        if (height > initialHeight) {
            System.out.println("✓ Growth test passed");
        } else {
            System.out.println("✗ Growth test failed");
            allTestsPassed = false;
        }

        // Test 3: Enchantment
        System.out.println("\nTest 3: Testing enchantment");
        enchant();
        if (isEnchanted && calculateGrowthBonus() == baseGrowthRate * 2) {
            System.out.println("✓ Enchantment test passed");
        } else {
            System.out.println("✗ Enchantment test failed");
            allTestsPassed = false;
        }

        // Test 4: Relocation
        System.out.println("\nTest 4: Testing relocation");
        double heightBeforeRelocation = height;
        relocate("New Test Location");
        if (height == heightBeforeRelocation * 0.9 && location.equals("New Test Location")) {
            System.out.println("✓ Relocation test passed");
        } else {
            System.out.println("✗ Relocation test failed");
            allTestsPassed = false;
        }

        // Test 5: Life stage progression
        System.out.println("\nTest 5: Testing life stage progression");
        String initialStage = getLifeStage();
        while (height < 2.0) grow(); // Grow until no longer a sapling
        if (!getLifeStage().equals(initialStage)) {
            System.out.println("✓ Life stage progression test passed");
        } else {
            System.out.println("✗ Life stage progression test failed");
            allTestsPassed = false;
        }

        // Test 6: Shadow area calculation
        System.out.println("\nTest 6: Testing shadow area calculation");
        double expectedArea = Math.PI * width * width;
        if (Math.abs(calculateShadowArea() - expectedArea) < 0.0001) {
            System.out.println("✓ Shadow area calculation test passed");
        } else {
            System.out.println("✗ Shadow area calculation test failed");
            allTestsPassed = false;
        }

        // Final results
        System.out.println("\nTest Results Summary:");
        System.out.println(allTestsPassed ? "All tests passed! ✓" : "Some tests failed! ✗");

        return allTestsPassed;
    }
}