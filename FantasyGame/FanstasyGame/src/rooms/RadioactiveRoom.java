package rooms;

import entities.Player;

import java.util.ArrayList;

public class RadioactiveRoom extends Room {
    private static final int RADIATION_DAMAGE = 5; // 5% health damage

    public RadioactiveRoom(String description) {
        super(description);
        // Either 0 or a lot of gold
        this.goldAmount = rng.nextBoolean() ? 0 : 60 + rng.nextInt(40);
    }

    @Override
    public void interact(Player player) {
        System.out.println(player.getName() + " entered " + this.description);
        System.out.println("Warning: This room is radioactive!");
        player.reduceHealth(RADIATION_DAMAGE);
        System.out.println(player.getName() + "'s health is now " + player.getHealth() + "%");
    }

    @Override
    public void takeGold(Player player) {
        if (goldAmount > 0) {
            int goldToTake = 1; // Take 1 gold at a time to control radiation exposure
            player.addGold(goldToTake);
            goldAmount -= goldToTake;
            player.reduceHealth(rng.nextInt(0,7)); // up to 6% health for each gold
            System.out.println(player.getName() + " took 1 gold but was exposed to radiation!");
            System.out.println(player.getName() + "'s health is now " + player.getHealth() + "%");
        } else {
            System.out.println("There is no gold in this room.");
        }
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Move to another room");
        options.add("Take gold (causes up to 6% radiation damage per gold)");
        options.add("Search for hazmat suit");
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
            case 2: // Search for hazmat suit
                int chance = rng.nextInt(100);
                if (chance < 5) {
                    System.out.println("You found a damaged hazmat suit! It will protect you from radiation for a short time.");
                    System.out.println("You can now take gold without radiation damage for this turn.");
                    int goldToTake = Math.min(goldAmount, player.getGoldCapacity() - player.getGold());
                    if (goldToTake > 0) {
                        player.addGold(goldToTake);
                        goldAmount -= goldToTake;
                        System.out.println(player.getName() + " took " + goldToTake + " gold safely.");
                    } else {
                        System.out.println("You can't carry any more gold!");
                    }
                } else {
                    System.out.println("You search but find no protection against the radiation.");
                    player.reduceHealth(2);
                    System.out.println("The search exposed you to more radiation. Health reduced by 2%.");
                    System.out.println(player.getName() + "'s health is now " + player.getHealth() + "%");
                }
                break;
        }
    }
}