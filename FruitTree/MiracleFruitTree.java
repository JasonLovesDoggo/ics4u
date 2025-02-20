public class MiracleFruitTree {
    // Immutable attributes
    private final String species;
    private final String origin;
    private final int maxFruitCapacity;
    private final double baseGrowthRate;
    private final String plantingDate;

    // Mutable attributes
    private String name;
    private String location;
    private double height;
    private double width;
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
        // Uses the TreeHelper to get the growth rate of the species. This is a dependency injection!
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
        return height < 60.0 && isHealthy() && !getLifeStage().equals("Mature"); // Can't relocate if it's too tall, unhealthy, or already mature
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
}
