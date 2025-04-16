package rooms;

import entities.Player;
import utilities.Interactable;

import java.util.ArrayList;
import java.util.Random;


public abstract class Room implements Interactable {
    protected String description;
    protected ArrayList<Room> connectedRooms;
    protected int goldAmount;
    protected Random rng = new Random();

    public Room(String description) {
        this.description = description;
        this.connectedRooms = new ArrayList<>();
        this.goldAmount = 0;
    }

    public void connectRoom(Room room) {
        if (!connectedRooms.contains(room)) {
            connectedRooms.add(room);
            room.connectedRooms.add(this);
        }
    }

    public ArrayList<Room> getConnectedRooms() {
        return connectedRooms;
    }

    public String getDescription() {
        return description;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    public abstract void takeGold(Player player);

    public abstract ArrayList<String> getOptions();

    public abstract void handleOption(int option, Player player);
}
