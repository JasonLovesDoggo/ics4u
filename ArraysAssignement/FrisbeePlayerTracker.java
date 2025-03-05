/*
Name: Jason, Zoe
Date: March 4, 2025
Teacher: Ms. Krasteva
Description: Practice using and manipulating parallel arrays. This program ranks frisbee players based on their goals, assists, second assists, throwaways and ds, and includes an array to store the ratio of assists + second assists to the number of throwaways.
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

public class FrisbeePlayerTracker {
	// Parallel arrays to store player information
	private static String[] names;
	private static int[] goals;
	private static int[] assists;
	private static int[] secondAssists;
	private static int[] throwaways;
	private static int[] ds;
	private static double[] ratio;
	private static int[] totalScore;
	private static int playerCount;


	private static final String DEFAULT_FILE_PATH = "./data.txt"; // Default file path for saving/loading data

	// Initialize arrays with a default capacity
	public FrisbeePlayerTracker() {
		int initialCapacity = 10;
		names = new String[initialCapacity];
		goals = new int[initialCapacity];
		assists = new int[initialCapacity];
		secondAssists = new int[initialCapacity];
		throwaways = new int[initialCapacity];
		ds = new int[initialCapacity];
		ratio = new double[initialCapacity];
		totalScore = new int[initialCapacity];
		playerCount = 0;
	}

	public FrisbeePlayerTracker(String filePath) {
		this();
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
				writer.print(goals[i] + " ");
				writer.print(assists[i] + " ");
				writer.print(secondAssists[i] + " ");
				writer.print(throwaways[i] + " ");
				writer.println(ds[i]);
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
			names = new String[playerCount];
			goals = new int[playerCount];
			assists = new int[playerCount];
			secondAssists = new int[playerCount];
			throwaways = new int[playerCount];
			ds = new int[playerCount];
			ratio = new double[playerCount];
			totalScore = new int[playerCount];

			// Read player data
			for (int i = 0; i < playerCount; i++) {
				names[i] = fileScanner.nextLine();
				goals[i] = fileScanner.nextInt();
				assists[i] = fileScanner.nextInt();
				secondAssists[i] = fileScanner.nextInt();
				throwaways[i] = fileScanner.nextInt();
				ds[i] = fileScanner.nextInt();

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
		double throwawayValue = throwaways[index] == 0 ? 1 : throwaways[index];

		// Calculate ratio of assists and second assists to throwaways
		ratio[index] = ((double)assists[index] + secondAssists[index]) / throwawayValue;
		ratio[index] = Math.round(ratio[index] * 10.0) / 10.0;

		// Calculate total score with weighted values
		totalScore[index] = goals[index] * 3 +
				assists[index] * 3 +
				secondAssists[index] -
				throwaways[index] * 2 +
				ds[index] * 2;
	}

	// Method to add a new player
	public static void addPlayer(String name, int goal, int assist, int secondAssist, int throwaway, int defense) {
		// Resize arrays if needed
		if (playerCount >= names.length) {
			int newCapacity = names.length * 2;
			names = Arrays.copyOf(names, newCapacity);
			goals = Arrays.copyOf(goals, newCapacity);
			assists = Arrays.copyOf(assists, newCapacity);
			secondAssists = Arrays.copyOf(secondAssists, newCapacity);
			throwaways = Arrays.copyOf(throwaways, newCapacity);
			ds = Arrays.copyOf(ds, newCapacity);
			ratio = Arrays.copyOf(ratio, newCapacity);
			totalScore = Arrays.copyOf(totalScore, newCapacity);
		}

		// Add player to the arrays
		names[playerCount] = name;
		goals[playerCount] = goal;
		assists[playerCount] = assist;
		secondAssists[playerCount] = secondAssist;
		throwaways[playerCount] = throwaway;
		ds[playerCount] = defense;

		calculateScores(playerCount);
		playerCount++;
	}

	// Remove a player by name
	public static boolean removePlayer(String name) {
		for (int i = 0; i < playerCount; i++) {
			if (name.equalsIgnoreCase(names[i])) {
				// Shift elements to remove the player
				for (int j = i; j < playerCount - 1; j++) {
					names[j] = names[j + 1];
					goals[j] = goals[j + 1];
					assists[j] = assists[j + 1];
					secondAssists[j] = secondAssists[j + 1];
					throwaways[j] = throwaways[j + 1];
					ds[j] = ds[j + 1];
					ratio[j] = ratio[j + 1];
					totalScore[j] = totalScore[j + 1];
				}

				// Null out the last element and decrease player count
				names[playerCount - 1] = null;
				playerCount--;
				return true;
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
					.append("|\t").append(goals[i]).append("\t|\t")
					.append(assists[i]).append("\t|\t")
					.append(secondAssists[i]).append("\t|\t")
					.append(throwaways[i]).append("\t|\t")
					.append(ds[i]).append("\t|\t")
					.append(String.format("%.1f", ratio[i])).append("  |\t")
					.append(totalScore[i]).append("\n");
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
				return names[i] + "\n\tGoals: " + goals[i] +
						"\n\tAssists: " + assists[i] +
						"\n\tSecond assists: " + secondAssists[i] +
						"\n\tThrowaways: " + throwaways[i] +
						"\n\tDefenses: " + ds[i] +
						"\n\tRatio of assists + second assists to throwaways: " + String.format("%.1f", ratio[i]) +
						"\n\tTotal score: " + totalScore[i];
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

		new FrisbeePlayerTracker(); // Initialize the arrays

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
							System.out.println("\t- " + names[i]);
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
						System.out.println("Enter the name of the player to remove:");
						String removePlayerName = scanner.nextLine();
						if (removePlayer(removePlayerName)) {
							System.out.println("Player removed successfully.");
						} else {
							System.out.println("Player not found.");
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
		String category;

		switch (choice) {
			case "a":
				category = "goals";
				break;
			case "b":
				category = "assists";
				break;
			case "c":
				category = "total";
				break;
			case "d":
				return;
			default:
				System.out.println("Invalid choice. Returning to main menu.");
				return;
		}

		// Create a temporary copy of the arrays to sort
		Integer[] indices = new Integer[playerCount];
		for (int i = 0; i < playerCount; i++) {
			indices[i] = i;
		}

		// Sort based on the specified category
		Arrays.sort(indices, ( a, b) -> {
			switch (category) {
				case "goals":
					return Integer.compare(goals[b], goals[a]);
				case "assists":
					return Integer.compare(assists[b], assists[a]);
				case "total":
					return Integer.compare(totalScore[b], totalScore[a]);
				default:
					return 0;
			}
		});

		// Display sorted results
		System.out.println("Sorted by " + category + ":");
		System.out.println("Player name | " +
				(category.equals("goals") ? "Goals" :
						category.equals("assists") ? "Assists" :
								"Total Score"));
		for (int index : indices) {
			System.out.printf("%-20s | %d%n", names[index], // https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/How-to-format-a-Java-String-with-printf-example (Formatting Strings)
					(category.equals("goals") ? goals[index] :
							category.equals("assists") ? assists[index] :
									totalScore[index]));
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