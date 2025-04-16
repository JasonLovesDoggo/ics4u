package rooms;

import entities.Player;
import moria.Moria;
import java.util.ArrayList;

public class GrandHallRoom extends NormalRoom {
    public GrandHallRoom(String description) {
        super(description);
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = super.getOptions();
        options.add("Leave the castle");
        return options;
    }

    @Override
    public void handleOption(int option, Player player) {
        if (option < super.getOptions().size()) {
            super.handleOption(option, player);
            return;
        }


        System.out.println("\n" + player.getName() + " decides to leave the castle with " + player.getGold() + " gold!");
        if (player.getGold() > 200) {
            System.out.println("What a successful adventure! You've gathered a fortune! \n(Press enter to continue)\n . . .");
        } else if (player.getGold() >= 100) {
            System.out.println("A decent haul. Your elf friends will be impressed.\n(Press enter to continue)\n . . .");
        } else if (player.getGold() > 0) {
            System.out.println("Not much gold, but at least you survived.");
        } else {
            System.out.println("You leave empty-handed. Better luck next time!");
        }
        
		  if (player.getGold() < 100) {
		  		System.exit(0);
		  }
		  //Moria start!
		  Moria moria = new Moria(player);
		  
    }
}
