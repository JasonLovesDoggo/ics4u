package pets;

import utilities.Interactable;
import entities.Player;
import rooms.*;
import java.util.Random;

public class LuckyPet extends Pet {
		
	public LuckyPet(String name) {
		super(name);
	}
	
	@Override
	public void interact(Player player) {
		Room room = player.getCurrentRoom();
		if (levelUp() && room instanceof NormalRoom) {
			if (rand.nextInt(10)%2 == 0) { //Randomly select which chance to change
				((NormalRoom)room).changeShamanChance((int)level/2);
				System.out.println("The chance of finding a shaman has been changed!");
			} else {
				((NormalRoom)room).changeDoctorChance((int)level/2);
				System.out.println("The chance of finding a doctor has been changed!");
			}
		}
	}
	
	public String toString() {
		return name + " (Lucky creature)";
	}
	
}