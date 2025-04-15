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

    /**
     * Default constructor that initializes a new tree with default values.
     * @param treeHelper Helper class to retrieve species-specific properties.
     */
    public MiracleFruitTree(TreeHelper treeHelper) {
        this("Unnamed Tree", "Garden of Wonders", treeHelper);
    }

    /**
     * Main constructor to initialize a tree with specific properties.
     * @param name Name of the tree.
     * @param location Planting location of the tree.
     * @param treeHelper Helper class to retrieve species-specific properties.
     */
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

    /**
     * Grows the tree, increasing its height and width based on its growth rate.
     */
    public void grow() {
        double growthBonus = calculateGrowthBonus();
        this.height += growthBonus;
        this.width += growthBonus * 0.5;
    }

    /**
     * Relocates the tree to a new location and reduces its height slightly.
     * @param newLocation The new location for the tree.
     */
    public void relocate(String newLocation) {
        this.location = newLocation;
        this.height *= 0.9; // Height reduces when relocated
    }

    /**
     * Renames the tree with a new name.
     * @param newName The new name for the tree.
     */
    public void rename(String newName) {
        this.name = newName;
    }

    /**
     * Enchants the tree, doubling its growth rate.
     */
    public void enchant() {
        this.isEnchanted = true;
    }

    /**
     * Removes the enchantment from the tree.
     */
    public void disenchant() {
        this.isEnchanted = false;
    }

    /**
     * Retrieves the species name of the tree.
     * @return The species name.
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Retrieves the origin location of the tree species.
     * @return The origin location.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Retrieves the maximum number of fruits the tree can bear at once.
     * @return The maximum fruit capacity.
     */
    public int getMaxFruitCapacity() {
        return maxFruitCapacity;
    }

    /**
     * Retrieves the base growth rate of the tree.
     * @return The base growth rate.
     */
    public double getBaseGrowthRate() {
        return baseGrowthRate;
    }

    /**
     * Retrieves the planting date of the tree.
     * @return The planting date.
     */
    public String getPlantingDate() {
        return plantingDate;
    }

    /**
     * Calculates the growth bonus for the tree based on its current state.
     * @return The growth bonus.
     */
    public double calculateGrowthBonus() {
        return isEnchanted ? baseGrowthRate * 2 : baseGrowthRate;
    }

    /**
     * Determines if the tree can be relocated based on its size and health.
     * @return True if relocation is possible, otherwise false.
     */
    public boolean canRelocate() {
        return height < 10.0 && isHealthy() && !getLifeStage().equals("Mature"); // Can't relocate if it's too tall, unhealthy, or already mature
    }

    /**
     * Retrieves the life stage of the tree based on its height.
     * @return The life stage as a string.
     */
    public String getLifeStage() {
        if (height < 2.0) return "Sapling";
        if (height < 5.0) return "Young";
        return "Mature";
    }

    /**
     * Calculates the shadow area of the tree based on its width.
     * @return The shadow area in square meters.
     */
    public double calculateShadowArea() {
        return Math.PI * width * width;
    }

    /**
     * Checks if the tree is healthy based on its proportions.
     * @return True if healthy, otherwise false.
     */
    public boolean isHealthy() {
        return height >= width * 1.5;
    }

    /**
     * Tests all major functionalities of the MiracleFruitTree class.
     * @return True if all tests pass, otherwise false.
     */
    public boolean test() {
        boolean allTestsPassed = true;
        System.out.println("Starting MiracleFruitTree tests...\n");

        // Test 1: Initial state
        System.out.println("Test 1: Checking initial state");
        if (height == 1.0 && width == 0.5 && !isEnchanted) {
            System.out.println("Initial state test passed");
        } else {
            System.out.println("Initial state test failed");
            allTestsPassed = false;
        }

        // Test 2: Growth
        System.out.println("\nTest 2: Testing growth");
        double initialHeight = height;
        grow();
        if (height > initialHeight) {
            System.out.println("Growth test passed");
        } else {
            System.out.println("Growth test failed");
            allTestsPassed = false;
        }

        // Test 3: Enchantment
        System.out.println("\nTest 3: Testing enchantment");
        enchant();
        if (isEnchanted && calculateGrowthBonus() == baseGrowthRate * 2) {
            System.out.println("Enchantment test passed");
        } else {
            System.out.println("Enchantment test failed");
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
            System.out.println("Life stage progression test passed");
        } else {
            System.out.println("Life stage progression test failed");
            allTestsPassed = false;
        }

        // Test 6: Shadow area calculation
        System.out.println("\nTest 6: Testing shadow area calculation");
        double expectedArea = Math.PI * width * width;
        if (Math.abs(calculateShadowArea() - expectedArea) < 0.0001) {
            System.out.println("Shadow area calculation test passed");
        } else {
            System.out.println("Shadow area calculation test failed");
            allTestsPassed = false;
        }

        // Final results
        System.out.println("\nTest Results Summary:");
        System.out.println(allTestsPassed ? "All tests passed!" : "Some tests failed!");

        return allTestsPassed;
    }
}
