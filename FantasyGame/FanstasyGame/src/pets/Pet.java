package pets;

import utilities.Interactable;

import java.util.Random;

public abstract class Pet implements Interactable {
    //protected static final int MAX_PETS = 5;
    protected static final int MAX_LEVEL = 10;
    protected static int petCount;
    protected String name;
    protected int level;
    Random rand;

    public Pet(String name) {
        this.name = name;
        this.level = 0;
        petCount++;
        rand = new Random();
    }

    public int getPetCount() {
        return petCount;
    }

    public String getDescription() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public boolean levelUp() {
        if (rand.nextInt(10) > 5 && level < 10) {
            level++;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return name;
    }

}