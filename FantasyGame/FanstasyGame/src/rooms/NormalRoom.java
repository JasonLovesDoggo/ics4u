package rooms;

import entities.*;
import utilities.Entity;

import java.util.ArrayList;

public class NormalRoom extends Room {
    private static final int MIN_GOLD = 2;
    private static final int MAX_GOLD = 20;
    private Entity entity;

    public NormalRoom(String description) {
        super(description);
        this.goldAmount = rng.nextInt(MIN_GOLD, MAX_GOLD);
        generateEntity();
    }

    private void generateEntity() {
        entity = new Shaman();
//        int chance = rng.nextInt(100);
//        if (chance < 10) {
//            entity = new Doctor();
//        } else if (chance < 20) {
//            entity = new Shaman();
//        } else if (chance < 30) {
//            entity = new Advisor();
//        } else {
//            entity = null;
//        }
    }

    @Override
    public void interact(Player player) {
        System.out.println(player.getName() + " entered " + this.description);
        if (entity != null) {
            entity.interact(player);
        }
    }

    @Override
    public void takeGold(Player player) {
        int playerCapacity = player.getGoldCapacity();
        int playerCurrentGold = player.getGold();
        int goldToTake = Math.min(goldAmount, playerCapacity - playerCurrentGold);

        if (goldToTake > 0) {
            player.addGold(goldToTake);
            goldAmount -= goldToTake;
            System.out.println(player.getName() + " took " + goldToTake + " gold.");
        } else {
            System.out.println(player.getName() + " can't carry any more gold!");
        }
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Move to another room");
        options.add("Take gold from this room");
        options.add("Search for hidden treasures");
        if (entity != null) {
            options.add("Talk to the " + entity.getClass().getSimpleName());
        }
        options.add("Rest and recover (gain 1% health)");
        return options;
    }

    @Override
    public void handleOption(int option, Player player) {
        switch (option) {
            case 0: // Move to another room - handled by Game class
                break;
            case 1: // Take gold
                takeGold(player);
                break;
            case 2: // Search for treasures
                int chance = rng.nextInt(100);
                if (chance < 20) {
                    int extraGold = rng.nextInt(1, 10);
                    goldAmount += extraGold;
                    System.out.println("You found " + extraGold + " more gold hidden in the room!");
                } else {
                    System.out.println("You search but find nothing of value.");
                }
                break;
            case 3: // Talk to entity
                if (entity != null) {
                    entity.interact(player);
                }
                break;
            case 4: // Rest
                player.setHealth(player.getHealth() + 5);
                System.out.println("You rest for a while and recover 5% health.");
                System.out.println("Your health is now " + player.getHealth() + "%");
                break;
        }
    }
}
