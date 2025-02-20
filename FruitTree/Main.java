public class Main {
    public static void main(String[] args) {
        TreeHelper treeHelper = new TreeHelper();
        treeHelper.addTree("Miraculus Fructus", 1); // Growth rate of the species (e.g.)

        MiracleFruitTree tree = new MiracleFruitTree("Magic Tree", "Enchanted Forest", treeHelper);

        // Perform operations
        tree.grow();
        System.out.println("Height after growing: " + tree.calculateShadowArea());
        tree.enchant();
        tree.grow();
        System.out.println("Height after enchanting and growing: " + tree.calculateShadowArea());
    }
}
