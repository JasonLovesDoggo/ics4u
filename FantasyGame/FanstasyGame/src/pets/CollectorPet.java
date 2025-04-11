package pets;

import entities.Player;
import rooms.Room;

public class CollectorPet extends Pet {
	
	private int totalGold;
	
	public CollectorPet(String name) {
		super(name);
	}
	
	public void addGold(int amount) {
		totalGold+=amount;
	}
	
	@Override
	public void interact(Player player) {
		Room room = player.getCurrentRoom();
		int extraGold = rand.nextInt(1+level*2);
		System.out.println("Your pet " + name + " found an extra " + extraGold + " gold");
		room.setGoldAmount(room.getGoldAmount()+extraGold);
	}
	
	@Override
	public boolean levelUp() {
		if ((Math.sqrt(totalGold) + 3) / 10 < getLevel()) {
			//System.out.println("Your pet " + name + " is trying to level up . . .");
			if (super.levelUp()) {
				System.out.println("Your pet " + name + " successfully leveled up!");
				return true;
			} else if (getLevel() == MAX_LEVEL) {
			//	System.out.println("Your pet " + name + " is already at the highest level!");
				return false;
			} else {
			//	System.out.println("Your pet " + name + " failed to level up.");
				return false;
			}
		} else {
			//System.out.println("Your pet " + name + " can't level up yet! It hasn't collected enough gold.");
			return false;
		}
	}
	
	public String toString() {
		return name + " (Niffler)";
	}
	
}