package entities;

import rooms.Room;
import pets.*;
import java.util.ArrayList;

public class Player {
    private final String name;
    private int health;
    private int gold;
    private int goldCapacity;
	 private int petCapacity;
    private Room currentRoom;
    public static final int GLOBAL_MAX_GOLD_CAPACITY = 250;
	 private ArrayList<Pet> playerPets;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.gold = 0;
        this.goldCapacity = 100;
		  this.playerPets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(100, health);
    }

    public void reduceHealth(int amount) {
        this.health = Math.max(0, this.health - amount);
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
	 		this.gold = Math.min(this.gold + amount, goldCapacity + petCapacity);
    }
	 
	 public void addDragonGold(int amount) { //Ignores the maximum gold capacity
	 		this.gold += amount;
	 }

    public void removeGold(int amount) {
        this.gold = Math.max(0, this.gold - amount);
    }

    public int getGoldCapacity() {
        return goldCapacity;
    }
	 
	 public int getPetCapacity() {
			return petCapacity;
	 }
	 
	 public int getTotalCapacity() {
	 		return getGoldCapacity()+getPetCapacity();
	 }

    public void increaseGoldCapacity(int amount) {
        this.goldCapacity = Math.min(this.goldCapacity + amount, GLOBAL_MAX_GOLD_CAPACITY); //Calculate the capacity
    }
	 
	 public void increasePetCapacity(int amount) {
	 		this.petCapacity += amount;
	 }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        room.interact(this);
		  for(Pet playerPet : playerPets) {
		  		playerPet.interact(this);
		  }
    }

    public boolean isCritical() {
        return health <= 30;
    }

    public boolean isAlive() {
        return health > 0;
    }
	 
	 public void addPet(Pet newPet) {
	 		playerPets.add(newPet);
	 }
	 
	 public int getNumPets() {
	 		return playerPets.size();
	 }
	 
	 public String getPets() {
	 	return playerPets.toString();
	 }
}
