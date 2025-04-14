import entities.Player;
import rooms.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private ArrayList<Room> rooms;
    private Room entryPoint;
    private Room grandHall;
    private final Scanner scanner;
    private final Random rng;

    public Game() {
        scanner = new Scanner(System.in);
        rooms = new ArrayList<>();
        rng = new Random();
        initializeGame();
    }

    private void initializeGame() {
        System.out.println("Welcome to the Castle Adventure!");
        System.out.println("What is your name, brave elf?");
        String playerName = scanner.nextLine();

        if (playerName.isEmpty()) {
            System.out.println("Welcome oh brave Zoeiye\"s li!");
            playerName = "Zoeiye\"s li";
        }
        player = new Player(playerName);

        createRooms();
        connectRooms();

        assert entryPoint != null;
        player.setCurrentRoom(entryPoint);
    }

    private void createRooms() {
        // Create all rooms
        entryPoint = new EntryPointRoom("Castle Entrance");
        rooms.add(entryPoint);

        grandHall = new GrandHallRoom("Grand Hall");
        rooms.add(grandHall);

        // Create a random number of rooms between 6-10
        // todo: infinite rooms?
        int numRooms = rng.nextInt(6, 10);
        String[] roomNames = {
                "Armory", "Library", "Kitchen", "Barracks", "Dungeon",
                "Throne rooms.Room", "Guard rooms.Room", "Servant Quarters", "Chapel",
                "Observatory", "Dining Hall", "Wine Cellar", "Crypt"
        };

        for (int i = 0; i < numRooms; i++) {
            String roomName = roomNames[rng.nextInt(roomNames.length)];
            roomName += " " + (i + 1); // Add a number to make each name unique

            int roomType = rng.nextInt(100);
            Room newRoom;

            if (roomType < 40) {
                newRoom = new NormalRoom(roomName);
            } else if (roomType < 60) {
                newRoom = new EmptyRoom(roomName);
            } else if (roomType < 90) {
                newRoom = new RadioactiveRoom(roomName);
            } else {
                newRoom = new DragonRoom(roomName);
            }

            rooms.add(newRoom);
        }
    }

    private void connectRooms() {
        // Connect entry point to grand hall
        entryPoint.connectRoom(grandHall);

        // Connect all other rooms to create a network
        // First make sure every room is connected to at least one other room
        for (int i = 2; i < rooms.size(); i++) {
            // Connect to a random room from existing rooms
            int targetRoomIndex = rng.nextInt(1, i);
            rooms.get(i).connectRoom(rooms.get(targetRoomIndex));
        }

        // Add some additional random connections for complexity
        int additionalConnections = rng.nextInt(3, 8);
        for (int i = 0; i < additionalConnections; i++) {
            int roomA = rng.nextInt(1, rooms.size());
            int roomB = rng.nextInt(1, rooms.size());

            // Don't connect a room to itself
            if (roomA != roomB) {
                rooms.get(roomA).connectRoom(rooms.get(roomB));
            }
        }
    }

    public void start() {
        System.out.println("\nYou are an elf on a quest to gather as much gold as possible from this mysterious castle.");
        System.out.println("Be careful of radioactive rooms and creatures that may harm you.");
        System.out.println("If your health drops to 30% or below, you should consider leaving the castle.");
        System.out.println("Remember: You can only leave the castle from the Grand Hall.");
        System.out.println("Good luck, " + player.getName() + "!\n");

        gameLoop();
    }

    private void gameLoop() {
        boolean running = true;

        while (running) {
            if (!player.isAlive()) {
                System.out.println("\nGame Over! " + player.getName() + " has died.");
                break;
            }

            // Check if player's health is critical
            if (player.isCritical()) {
                System.out.println("\nWarning: Your health is critical! You should return to the Grand Hall and leave the castle soon.");
            }

            displayStatus();
            handleRoomOptions();
        }
    }

    private void displayStatus() {
        System.out.println("\n==== Status ====");
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getHealth() + "%");
        System.out.println("Gold: " + player.getGold() + "/" + player.getTotalCapacity());
        System.out.println("Current Location: " + player.getCurrentRoom().getDescription());
		  if (player.getNumPets() > 0) {
		  		System.out.println("Pets: " + player.getPets());
		  }
    }

    private void handleRoomOptions() {
        Room currentRoom = player.getCurrentRoom();
        ArrayList<String> options = currentRoom.getOptions();

        System.out.println("\nWhat would you like to do?");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        int choice = getPlayerChoice(options.size());

        if (choice == 0 && options.get(0).contains("Move")) {
            // Handle movement to another room
            moveToAnotherRoom();
        } else {
            // Handle other room-specific options
            currentRoom.handleOption(choice, player);
        }
		  
		  if (choice == 0 && options.get(0).contains("Leave")) {
		  		moveToAnotherRoom();
		  }
    }

    private int getPlayerChoice(int max) {
        while (true) {
            try {
                System.out.print("Enter your choice (1-" + max + "): ");
                int choice = Integer.parseInt(scanner.nextLine()) - 1;
                if (choice >= 0 && choice < max) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void moveToAnotherRoom() {
        ArrayList<Room> connectedRooms = player.getCurrentRoom().getConnectedRooms();

        if (connectedRooms.isEmpty()) {
            System.out.println("There are no connected rooms available.");
            return;
        }

        System.out.println("\nConnected rooms:");
        for (int i = 0; i < connectedRooms.size(); i++) {
            System.out.println((i + 1) + ". " + connectedRooms.get(i).getDescription());
        }

        int roomChoice = getPlayerChoice(connectedRooms.size());
        player.setCurrentRoom(connectedRooms.get(roomChoice));
    }
}
