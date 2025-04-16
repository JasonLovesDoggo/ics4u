package rooms;

import entities.Player;

import java.util.ArrayList;

//todo: see if i can just remove this room
public class EntryPointRoom extends Room {
    public EntryPointRoom(String description) {
        super(description);
        this.goldAmount = 0;
    }

    @Override
    public void interact(Player player) {
        System.out.println(player.getName() + " entered " + this.description);
        System.out.println("This is the entrance to the castle.");
    }

    @Override
    public void takeGold(Player player) {
        System.out.println("There is no gold here.");
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Move deeper into the castle");
        options.add("Check your inventory");
        options.add("Examine the entrance");
        return options;
    }

    @Override
    public void handleOption(int option, Player player) {
        switch (option) {
            case 0: // Move to another room - handled by Game class
                break;
            case 1: // Check inventory
                System.out.println("\n==== Inventory ====");
                System.out.println("Gold: " + player.getGold() + "/" + player.getGoldCapacity());
                System.out.println("Health: " + player.getHealth() + "%");
                break;
            case 2: // Examine entrance
                System.out.println("You examine the castle entrance. Sunlight streams through the open doorway.");
                System.out.println("Ancient runes are carved into the stone arch above you.");
                System.out.println("They read: 'Beware the dragon's hoard, for its treasure comes with a price.'");
                break;
        }
    }
}
