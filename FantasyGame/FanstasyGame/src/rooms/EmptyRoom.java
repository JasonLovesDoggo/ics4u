package rooms;

import entities.Player;

import java.util.ArrayList;

public class EmptyRoom extends Room {
    public EmptyRoom(String description) {
        super(description);
        this.goldAmount = 0;
    }

    @Override
    public void interact(Player player) {
        System.out.println(player.getName() + " entered " + this.description);
        System.out.println("The room is completely empty.");
    }

    @Override
    public void takeGold(Player player) {
        System.out.println("There is no gold in this room.");
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Move to another room");
        options.add("Inspect the walls");
        options.add("Look for hidden passages");
        options.add("Rest and recover (gain 5% health)");
        return options;
    }

    @Override
    public void handleOption(int option, Player player) {
        switch (option) {
            case 0: // Move to another room - handled by Game class
                break;
            case 1: // Inspect walls
                System.out.println("You inspect the walls but find nothing unusual.");
                break;
            case 2:
                int chance = rng.nextInt(10);
                if (chance == 6) {
                    System.out.println("You found a hidden passage! But it's too dark to explore without a torch.");
                } else {
                    System.out.println("You look carefully but find no hidden passages.");
                }
                break;
            case 3: // Rest
                player.setHealth(player.getHealth() + 1);
                System.out.println("You rest for a while and recover 1% health.");
                System.out.println("Your health is now " + player.getHealth() + "%");
                break;
        }
    }
}