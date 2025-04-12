package entities;

import utilities.Entity;

import java.util.*;

public class Shaman implements Entity {
    @Override
    public void interact(Player player) {
        Random rng = new Random();
        System.out.println("A mystical shaman offers to increase your gold carrying capacity.");
        System.out.println("The shaman performs a ritual...");
        if (player.getGoldCapacity() >= Player.GLOBAL_MAX_GOLD_CAPACITY+player.getPetCapacity()) {
            System.out.println("The shaman cries out, IT WONT WORK! You are already at maximum capacity!");
            return;
        } else if (rng.nextInt(10) > 5) { // 50% chance to fail and do nothing
            System.out.println("The shaman's magic fails! You feel a strange sensation, but nothing happens.");
            return;
        } else if (rng.nextInt(50) >= 48 ) { // 4% chance to backfire
            System.out.println("The shaman's magic backfires! You feel a surge of energy, but in the chaos, Sam, who is now a goblin sneaks in and robs you!!!.");
            player.reduceHealth(30); // Reduce health by 30%
            player.removeGold(9999); // Remove all gold
            System.out.println("Your health is now " + player.getHealth() + "%");
            return;

        }
        int increase = rng.nextInt(10, 30);
        player.increaseGoldCapacity(increase);
        System.out.println("Your gold carrying capacity has been increased by " + increase + "!\n(Press enter to continue)\n . . .");
		  Scanner s = new Scanner(System.in);
		  s.nextLine();
    }
}
