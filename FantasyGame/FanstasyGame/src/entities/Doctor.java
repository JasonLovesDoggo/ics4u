package entities;

import utilities.Entity;

import java.util.Scanner;

public class Doctor implements Entity {
    private static final int HEALING_COST = 40;

    @Override
    public void interact(Player player) {
        System.out.println("You've encountered a doctor who can heal you for " + HEALING_COST + " gold.");
        if (player.getGold() >= HEALING_COST) {
            System.out.println("Do you want to be healed to full health? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("y")) {
                player.removeGold(HEALING_COST);
                player.setHealth(100);
                System.out.println("The doctor has healed you to full health!");
            } else {
                System.out.println("You decline the doctor's services.");
            }
        } else {
            System.out.println("You don't have enough gold to pay the doctor.");
        }
    }
}

