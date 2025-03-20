/*
Name: Jason, Zoe
Date: March 5, 2025
Teacher: Ms. Krasteva
Description: Practice using and manipulating 2D arrays. This program ranks frisbee players based on their goals, assists, second assists, throwaways and ds, and includes a calculation for the ratio of assists + second assists to the number of throwaways.
*/
/*
TODOs:
[DONE] search results by player - Zoe
[DONE] sort players by total score/each category - Jason
[DONE] Add player in memory - Jason
[DONE] Remove player from memory - Jason
[DONE] Save current data to file - Jason
[DONE] Load data from file - Zoe
[DONE] Help page where we describe what all values are - Zoe
*/

import java.io.*;
import java.util.*;

public class FrisbeePlayerTrackerV2 {
    // Constants for array indices and initial capacity
    private static final int GOALS = 0;
    private static final int ASSISTS = 1;
    private static final int SECOND_ASSISTS = 2;
    private static final int THROWAWAYS = 3;
    private static final int DS = 4;
    private static final int RATIO = 5;
    private static final int TOTAL_SCORE = 6;
    private static final int STAT_COUNT = 7; // Total number of statistics columns
    // Player data storage
    private static String[] names;
    private static double[][] playerStats; // 2D array for all player stats
    private static int playerCount;
    private static String DEFAULT_FILE_PATH = "./data.txt";

    // Initialize arrays with a default capacity
    public FrisbeePlayerTrackerV2() {
        int initialCapacity = 10;
        names = new String[initialCapacity];
        playerStats = new double[initialCapacity][STAT_COUNT];
        playerCount = 0;
    }

    public FrisbeePlayerTrackerV2(String filePath) {
        this();
        DEFAULT_FILE_PATH = filePath;
        loadData(filePath);
    }

    public static boolean saveData() {
        return saveData(DEFAULT_FILE_PATH);
    }

    public static boolean saveData(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write number of players first
            writer.println(playerCount);

            // Write each player's data
            for (int i = 0; i < playerCount; i++) {
                writer.println(names[i]);
                writer.print((int)playerStats[i][GOALS] + " ");
                writer.print((int)playerStats[i][ASSISTS] + " ");
                writer.print((int)playerStats[i][SECOND_ASSISTS] + " ");
                writer.print((int)playerStats[i][THROWAWAYS] + " ");
                writer.println((int)playerStats[i][DS]);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            return false;
        }
    }

    // Load data from file
    public static void loadData() {
        loadData(DEFAULT_FILE_PATH);
    }

    public static void loadData(String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            // Reset current arrays
            playerCount = fileScanner.nextInt();
            fileScanner.nextLine(); // Clear buffer

            // Ensure arrays are large enough
            if (names.length < playerCount) {
                names = new String[playerCount];
                playerStats = new double[playerCount][STAT_COUNT];
            }

            // Read player data
            for (int i = 0; i < playerCount; i++) {
                names[i] = fileScanner.nextLine();
                playerStats[i][GOALS] = fileScanner.nextInt();
                playerStats[i][ASSISTS] = fileScanner.nextInt();
                playerStats[i][SECOND_ASSISTS] = fileScanner.nextInt();
                playerStats[i][THROWAWAYS] = fileScanner.nextInt();
                playerStats[i][DS] = fileScanner.nextInt();

                // Recalculate derived values
                calculateScores(i);

                // Clear buffer if needed
                if (fileScanner.hasNextLine()) {
                    fileScanner.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with empty player list.");
            playerCount = 0;
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            playerCount = 0;
        }
    }

    // Method to calculate scores for a player
    private static void calculateScores(int index) {
        // Prevent division by zero
        double throwawayValue = playerStats[index][THROWAWAYS] == 0 ? 1 : playerStats[index][THROWAWAYS];

        // Calculate ratio of assists and second assists to throwaways
        playerStats[index][RATIO] = Math.round((playerStats[index][ASSISTS] + playerStats[index][SECOND_ASSISTS]) / throwawayValue * 10.0) / 10.0;

        // Calculate total score with weighted values
        playerStats[index][TOTAL_SCORE] =
                playerStats[index][GOALS] * 3 +
                        playerStats[index][ASSISTS] * 3 +
                        playerStats[index][SECOND_ASSISTS] -
                        playerStats[index][THROWAWAYS] * 2 +
                        playerStats[index][DS] * 2;
    }

    // Method to add a new player
    public static void addPlayer(String name, int goal, int assist, int secondAssist, int throwaway, int defense) {
        // Resize arrays if needed
        if (playerCount >= names.length) {
            int newCapacity = names.length * 2;
            names = Arrays.copyOf(names, newCapacity);

            double[][] newPlayerStats = new double[newCapacity][STAT_COUNT];
            for (int i = 0; i < playerCount; i++) {
                System.arraycopy(playerStats[i], 0, newPlayerStats[i], 0, STAT_COUNT);
            }
            playerStats = newPlayerStats;
        }

        // Add player to the arrays
        names[playerCount] = name;
        playerStats[playerCount][GOALS] = goal;
        playerStats[playerCount][ASSISTS] = assist;
        playerStats[playerCount][SECOND_ASSISTS] = secondAssist;
        playerStats[playerCount][THROWAWAYS] = throwaway; // 3 is the index of the throwaways stat
        playerStats[playerCount][DS] = defense;

        calculateScores(playerCount);
        playerCount++;
    }

    // Remove a player by index
    public static boolean removePlayerByIndex(int index) {
        if (index >= 0 && index < playerCount) {
            // Shift elements to remove the player
            for (int j = index; j < playerCount - 1; j++) {
                names[j] = names[j + 1];
                System.arraycopy(playerStats[j+1], 0, playerStats[j], 0, STAT_COUNT);
            }

            // Null out the last element and decrease player count
            names[playerCount - 1] = null;
            playerCount--;
            return true;
        }
        return false;
    }

    // Remove a player by name
    public static boolean removePlayer(String name) {
        for (int i = 0; i < playerCount; i++) {
            if (name.equalsIgnoreCase(names[i])) {
                return removePlayerByIndex(i);
            }
        }
        return false;
    }

    // Display all players
    public static String displayAll() {
        if (playerCount == 0) {
            return "No players in the system.";
        }

        StringBuilder text = new StringBuilder("\tPlayer name\t\t\t\t|\tG\t|\tA\t|\t2A\t|\tTA\t|\tDs\t| Ratio |\tTotal\n");
        text.append("--------------------------------------------------------------------------\n");

        for (int i = 0; i < playerCount; i++) {
            text.append(formatPlayerName(names[i]))
                    .append("|\t").append((int)playerStats[i][GOALS]).append("\t|\t")
                    .append((int)playerStats[i][ASSISTS]).append("\t|\t")
                    .append((int)playerStats[i][SECOND_ASSISTS]).append("\t|\t")
                    .append((int)playerStats[i][THROWAWAYS]).append("\t|\t")
                    .append((int)playerStats[i][DS]).append("\t|\t")
                    .append(String.format("%.1f", playerStats[i][RATIO])).append("  |\t")
                    .append((int)playerStats[i][TOTAL_SCORE]).append("\n");
        }

        text.append("--------------------------------------------------------------------------");
        return text.toString();
    }

    // Format player name for display
    private static String formatPlayerName(String name) {
        if (name.length() > 20) {
            return "\t" + name.substring(0, 17) + "...\t";
        } else if (name.length() > 17) {
            return "\t" + name + "\t";
        } else if (name.length() > 14) {
            return "\t" + name + "\t\t";
        } else if (name.length() > 11) {
            return "\t" + name + "\t\t\t";
        } else if (name.length() > 8) {
            return "\t" + name + "\t\t\t\t";
        } else if (name.length() > 5) {
            return "\t" + name + "\t\t\t\t\t";
        } else {
            return "\t" + name + "\t\t\t\t\t\t";
        }
    }

    // View a specific player's stats
    public static String viewPlayer(String name) {
        for (int i = 0; i < playerCount; i++) {
            if (name.equalsIgnoreCase(names[i])) {
                return names[i] +
                        "\n\tGoals: " + (int)playerStats[i][GOALS] +
                        "\n\tAssists: " + (int)playerStats[i][ASSISTS] +
                        "\n\tSecond assists: " + (int)playerStats[i][SECOND_ASSISTS] +
                        "\n\tThrowaways: " + (int)playerStats[i][THROWAWAYS] +
                        "\n\tDefenses: " + (int)playerStats[i][DS] +
                        "\n\tRatio of assists + second assists to throwaways: " + String.format("%.1f", playerStats[i][RATIO]) +
                        "\n\tTotal score: " + (int)playerStats[i][TOTAL_SCORE];
            }
        }
        return "No player by the name of " + name + " found.";
    }

    // Help page
    public static String helpPage() {
        return """
                Frisbee Player Ranking Program Help
                --------------------------------
                
                Program Overview:
                - Display all players
                - Sort players by category
                - View individual player stats
                - Add and remove players
                
                Stat Abbreviations:
                \tG   = Goals
                \tA   = Assists
                \t2A  = Second Assist
                \tTA  = Throwaways
                \tDs  = Defenses/Blocks
                \tRatio = Assists + Second Assists to Throwaways
                \tTotal = Weighted Score
                
                Stat Weightings:
                \tGoals:          +3 points
                \tAssists:        +3 points
                \tSecond Assists: +1 point
                \tThrowaways:     -2 points
                \tDefenses:       +2 points""";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an instance of the class to initialize arrays
        new FrisbeePlayerTrackerV2(DEFAULT_FILE_PATH);

        // Pre-populate with some initial data
        addPlayer("Jason", 5, 3, 2, 1, 4);
        addPlayer("Zoe", 4, 4, 3, 2, 3);
        addPlayer("Joe", 3, 2, 1, 3, 2);

        System.out.println(helpPage());
        System.out.println("Press enter to continue to the program.");
        scanner.nextLine();

        while (true) {
            System.out.println("\nThere are currently " + playerCount + " players stored in this system.");
            System.out.println("You may:\n\t1. display all\n\t2. sort players by a category\n\t3. view a player's stats\n\t4. add a player in memory\n\t5. remove a player\n\t6. save data to file\n\t7. load data from file\n\t8. visit the help page\n\t9. exit the program");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: // Display all
                        System.out.println(displayAll());
                        pressEnterToContinue(scanner);
                        break;
                    case 2: // Sort players
                        sortPlayersMenu(scanner);
                        break;
                    case 3: // View player stats
                        System.out.println("Players:");
                        for (int i = 0; i < playerCount; i++) {
                            System.out.println("\t" + (i+1) + ". " + names[i]);
                        }
                        System.out.println("Please pick a player: ");
                        String playerName = scanner.nextLine();
                        System.out.println(viewPlayer(playerName));
                        pressEnterToContinue(scanner);
                        break;
                    case 4: // Add player
                        addPlayerMenu(scanner);
                        break;
                    case 5: // Remove player
                        if (playerCount == 0) {
                            System.out.println("No players to remove.");
                        } else {
                            System.out.println("Select a player to remove:");
                            for (int i = 0; i < playerCount; i++) {
                                System.out.println("\t" + (i+1) + ". " + names[i]);
                            }
                            System.out.print("Enter player number: ");
                            try {
                                int playerIndex = Integer.parseInt(scanner.nextLine()) - 1;
                                if (removePlayerByIndex(playerIndex)) {
                                    System.out.println("Player removed successfully.");
                                } else {
                                    System.out.println("Invalid player number.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid number.");
                            }
                        }
                        pressEnterToContinue(scanner);
                        break;
                    case 6: // Save to file
                        if (saveData()) {
                            System.out.println("Data saved successfully.");
                        } else {
                            System.out.println("Failed to save data.");
                        }
                        pressEnterToContinue(scanner);
                        break;
                    case 7: // Load from file
                        loadData();
                        System.out.println("Data loaded. " + playerCount + " players imported.");
                        pressEnterToContinue(scanner);
                        break;
                    case 8: // Help page
                        System.out.println(helpPage());
                        pressEnterToContinue(scanner);
                        break;
                    case 9: // Exit
                        System.out.println("Thank you for using this program. \nGoodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Sort players by category
    private static void sortPlayersMenu(Scanner scanner) {
        System.out.println("Sort players by:");
        System.out.println("a. Goals");
        System.out.println("b. Assists");
        System.out.println("c. Total Score");
        System.out.println("d. Return to main menu");

        String choice = scanner.nextLine().toLowerCase();
        int categoryIndex;

        switch (choice) {
            case "a":
                categoryIndex = GOALS;
                break;
            case "b":
                categoryIndex = ASSISTS;
                break;
            case "c":
                categoryIndex = TOTAL_SCORE;
                break;
            case "d":
                return;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }

        // Create a temporary copy of the indices to sort
        Integer[] indices = new Integer[playerCount];
        for (int i = 0; i < playerCount; i++) {
            indices[i] = i;
        }

        // Sort based on the specified category
        final int finalCategoryIndex = categoryIndex;
        Arrays.sort(indices, (a, b) -> Double.compare(playerStats[b][finalCategoryIndex], playerStats[a][finalCategoryIndex]));

        // Display sorted results
        System.out.println("Sorted by " +
                (categoryIndex == GOALS ? "goals" :
                        categoryIndex == ASSISTS ? "assists" : "total score") + ":");

        System.out.println("Player name | Value");
        System.out.println("------------------");
        for (int index : indices) {
            System.out.printf("%-20s | %d%n", names[index],
                    (int)playerStats[index][finalCategoryIndex]);
        }

        pressEnterToContinue(scanner);
    }

    // Helper method to pause and continue
    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press enter to return to the main menu.");
        scanner.nextLine();
    }

    // Add player through menu
    private static void addPlayerMenu(Scanner scanner) {
        try {
            System.out.println("Please enter the player's name: ");
            String name = scanner.nextLine();

            System.out.println("Please enter the player's goals: ");
            int goals = Integer.parseInt(scanner.nextLine());

            System.out.println("Please enter the player's assists: ");
            int assists = Integer.parseInt(scanner.nextLine());

            System.out.println("Please enter the player's second assists: ");
            int secondAssists = Integer.parseInt(scanner.nextLine());

            System.out.println("Please enter the player's throwaways: ");
            int throwaways = Integer.parseInt(scanner.nextLine());

            System.out.println("Please enter the player's defenses: ");
            int defenses = Integer.parseInt(scanner.nextLine());

            addPlayer(name, goals, assists, secondAssists, throwaways, defenses);
            System.out.println("Player added successfully.");
            pressEnterToContinue(scanner);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Player not added.");
            pressEnterToContinue(scanner);
        }
    }
}


