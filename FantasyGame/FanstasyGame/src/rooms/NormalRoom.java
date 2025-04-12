package rooms;

import entities.*;
import utilities.Entity;
import pets.*;
import java.util.Scanner;

import java.util.ArrayList;

public class NormalRoom extends Room {
    private static final int MIN_GOLD = 2;
    private static final int MAX_GOLD = 20;
	 private static final int MAX_CHANCE = 20;
    private Entity entity;
	 private int shamanChance;
	 private int doctorChance;

    public NormalRoom(String description) {
        super(description);
        this.goldAmount = rng.nextInt(MIN_GOLD, MAX_GOLD);
		  this.shamanChance = 10;
		  this.doctorChance = 10;
        //generateEntity();
    }
	 
	 public void changeShamanChance(int newChance) {
	 		shamanChance = Math.min(newChance+shamanChance, MAX_CHANCE);
			
			System.out.println("New shaman chance: " + shamanChance);
	 }
	 
	 public void changeDoctorChance(int newChance) {
	 		doctorChance = Math.min(newChance+doctorChance, MAX_CHANCE);
			System.out.println("New doctor chance: " + doctorChance);

	 }

    private void generateEntity() {
        //entity = new Shaman();
        int chance = rng.nextInt(100);
        if (chance < 10) {
            entity = new Advisor();
        } else if (chance < shamanChance+10) {
            entity = new Shaman();
        } else if (chance < shamanChance+10+doctorChance) {
            entity = new Doctor();
        } else {
            entity = null;
        }
		  
    }
	 
    @Override
    public void interact(Player player) {
        System.out.println(player.getName() + " entered " + this.description);
        if (entity != null) {
            entity.interact(player);
        }
		  
		  int chance = rng.nextInt(100);
		  Scanner s = new Scanner(System.in);
		  
		  if (chance < 5) {
				System.out.println("\n  .VVVVVVVVV.\n VVVVVVVVVVVVV\nVVVVVVVVVVV' .\\ \n`VVVVVVVVVV_,__o\n");
		  		System.out.print("A new hedgehog has spawned! You have gained a new pet. \nWhat would you like to call it? ");
				String name = s.nextLine();
				if (name.isEmpty()) {
					name = "Pete"; //First name I could think of
				}
				Pet newPet = new LuckyPet(name);
				player.addPet(newPet);
		  } else if (chance < 10) {
				System.out.println("\n            ..--.     _..---\"\"\"\"\"-.        .-.\n           /     '--'             `\\__.---/  /\n      _   / O     |          /     /__.----'''\n     \\ \"--'\\                 \\    (\n      ''`--\" '--' \\  |-..____.-;-. )\n               / / /      `--' .' /\n             .'.'_/           `--'\n");
				System.out.print("A new niffler has spawned! You have gained a new pet. \nWhat would you like to call it? ");
				String name = s.nextLine();
				if (name.isEmpty()) {
					name = "Teddy"; //Newt Scamander's niffler is called Teddy
				}
				Pet newPet = new CollectorPet(name);
				player.addPet(newPet);
		  } else if (chance < 15) {
		  		System.out.println("\n            .'' \n  ._.-.___.' (`\\ \n //(        ( `' \n'/ )\\ ).__. ) \n' <' `\\ ._/'\\ \n   `   \\     \\ \n");
		  		System.out.print("A new horse has spawned! You have gained a new pet. \nHorses increase your carrying capacity for gold. \nWhat would you like to call it? ");
				String name = s.nextLine();
				if (name.isEmpty()) {
					name = "Camel"; //A horse named camel. Because yes. 
				}
				Pet newPet = new PackPet(name);
				player.addPet(newPet);
				newPet.interact(player);
		  }
		  
		  generateEntity();
    }

    @Override
    public void takeGold(Player player) {
        int playerCapacity = player.getTotalCapacity();
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
                    //goldAmount += extraGold;
						  player.addGold(extraGold);
                    System.out.println("You found " + extraGold + " more gold hidden in the room!");
                } else {
                    System.out.println("You search but find nothing of value.");
                }
                break;
            case 3: // Talk to entity
                if (entity != null) {
                    entity.interact(player);
						  break; //Break inside the if statement so that if the entity is null, it drops down
                }
            case 4: // Rest
                player.setHealth(player.getHealth() + 5);
                System.out.println("You rest for a while and recover 5% health.");
                System.out.println("Your health is now " + player.getHealth() + "%");
                break;
        }
    }
}
