package entities;

import rooms.Room;

public class Player {
    private final String name;
    private int health;
    private int gold;
    private int goldCapacity;
    private Room currentRoom;
    public static final int GLOBAL_MAX_GOLD_CAPACITY = 250;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.gold = 0;
        this.goldCapacity = 100;
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
        this.gold = Math.min(this.gold + amount, goldCapacity);
    }

    public void removeGold(int amount) {
        this.gold = Math.max(0, this.gold - amount);
    }

    public int getGoldCapacity() {
        return goldCapacity;
    }

    public void increaseGoldCapacity(int amount) {
        this.goldCapacity += amount;
        this.goldCapacity = Math.min(this.goldCapacity, GLOBAL_MAX_GOLD_CAPACITY);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        room.interact(this);
    }

    public boolean isCritical() {
        return health <= 30;
    }

    public boolean isAlive() {
        return health > 0;
    }
}