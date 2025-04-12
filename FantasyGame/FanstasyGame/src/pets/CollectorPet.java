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
		if (room.getClass().getSimpleName().equals("NormalRoom")) {
			int extraGold = rand.nextInt((1+level)*2) + 1;
			System.out.println("\nYour pet " + name + " found an extra " + extraGold + " gold");
			player.addGold(extraGold);
			totalGold += extraGold;
		}
		if (totalGold % 20 == 0) {
			levelUp();
		}
	}
	
	@Override
	public boolean levelUp() {
		if ((Math.sqrt(totalGold) + 3) / 10 < getLevel()) {
			if (super.levelUp()) {
				System.out.println("\nYour pet " + name + " successfully leveled up!");
				return true;
			} else if (getLevel() == MAX_LEVEL) {
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String toString() {
		return name + " (Niffler, Lvl " + level +")";
	}
	
}
