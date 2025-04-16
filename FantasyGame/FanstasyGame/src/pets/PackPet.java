package pets;

import entities.Player;

import java.util.Scanner;

public class PackPet extends Pet {

    private int capacity;
    private int counter;

    public PackPet(String name) {
        super(name);
        level = 1;
        counter = 0;

    }

    @Override
    public void interact(Player player) {
        if (counter % 20 == 0) {
            if (this.levelUp()) {
                player.increasePetCapacity(10);
            }
        }
        counter++;
    }

    @Override
    public boolean levelUp() {
        if (level < 10 && rand.nextInt(100) % 2 == 0) {
            System.out.println("Your " + name + " (Horse) successfully leveled up! Your carrying capacity has increased by 10.\n(Press enter to continue)\n . . .");
            level++;
            Scanner s = new Scanner(System.in);
            s.nextLine();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (Horse, Lvl " + level + ")";
    }
}