/*
Name: Jason, Zoe
Date: March 4, 2025
Teacher: Ms. Krasteva
Description: Practice using and manipulating parallel arrays. This program ranks frisbee players based on their goals, assists, second assists, throwaways and ds, and includes an array to store the ratio of assists + second assists to the number of throwaways.
*/
/* 
TODOs:
[DONE] search results by player - Zoe
- sort players by total score/each category - Jason
[DONE] Add player in memory - Jason
- Remove player from memory - Jason
[DONE] Save current data to file - Jason
[DONE] Load data from file - Zoe
[DONE] Help page where we describe what all values are - Zoe
*/

import java.io.PrintWriter;
import java.util.*;
import java.io.File;

public class ArraysAssignment{
	
	Scanner s;
	private String[] names;
	private int[] goals;
	private int[] assists;
	private int[] secondAssists;
	private int[] throwaways;
	private int[] ds;
	private double[] ratio;
	private int[] totalScore;

	ArraysAssignment() {
		try {
			readData();
		} catch (Exception e) {
			System.out.println("Error initializing data: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean saveData() {
		// Returns true if the data was saved successfully, false otherwise.

		try {
			File file = new File("./data.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter writer = new PrintWriter(file);
			writer.println(names.length);
			for(int i = 0; i < names.length; i++) {
				writer.println(names[i]);
				writer.print(goals[i] + " ");
				writer.print(assists[i] + " ");
				writer.print(secondAssists[i] + " ");
				writer.print(throwaways[i] + " ");
				writer.println(ds[i]);
			}
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void readData() {
		try {
			String pwd = new File("").getAbsolutePath();
			File file = new File("./data.txt");
			// validate that the file exists
			if (!file.exists()) {
				System.out.println("File not found. Please ensure a data.txt file exists in the same directory as this program. Please make it relative to " + pwd );
				System.exit(2);
			}
			s = new Scanner(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int n = s.nextInt(); //read in the amount players
		s.nextLine(); //clear buffer
		
		//Set the sizes of each of the arrays
		names = new String[n];
		goals = new int[n];
		assists = new int[n];
		secondAssists = new int[n];
		throwaways = new int[n];
		ds = new int[n];
		ratio = new double[n];
		totalScore = new int[n];
		
		for(int i = 0; i < n; i++) {
			names[i] = s.nextLine();
			goals[i] = s.nextInt();
			assists[i] = s.nextInt();
			secondAssists[i] = s.nextInt();
			throwaways[i] = s.nextInt();
			ds[i] = s.nextInt();
			if (s.hasNextLine()) {
				s.nextLine(); //Clear buffer
			}
			calculateScores(i);
		}
	}

	private void calculateScores(int i) {
		double ratioValue = ((double)assists[i]+secondAssists[i])/throwaways[i]; // Calculate the ratio of assists and second assists to the number of throwaways
		ratio[i] = Math.round(ratioValue*10)/10.0;
		totalScore[i] = goals[i]*3 + assists[i]*3 + secondAssists[i] - throwaways[i]*2 + ds[i]*2;
	}

	public void sort(String sortBy) { //Copy the arrays, then sort them. Return sorted arrays (no return type right now because I'm lazy)
		
	}
	
	public String displayAll() {
		StringBuilder text = new StringBuilder("\tPlayer name\t\t\t\t|\tG\t|\tA\t|\t2A\t|\tTA\t|\tDs\t| Ratio |\tTotal\n");
		text.append("--------------------------------------------------------------------------");
		text.append("\n");
		for(int i = 0; i < names.length; i++) {
			if (names[i].length() > 20) {
				text.append("\t").append(names[i], 0, 17).append("...\t");
			} else if (names[i].length() > 17) {
				text.append("\t").append(names[i]).append("\t");
			} else if (names[i].length() > 14) {
				text.append("\t").append(names[i]).append("\t\t");
			} else if (names[i].length() > 11) {
				text.append("\t").append(names[i]).append("\t\t\t");
			} else if (names[i].length() > 8) {
				text.append("\t").append(names[i]).append("\t\t\t\t");
			} else if (names[i].length() > 5) {
				text.append("\t").append(names[i]).append("\t\t\t\t\t");
			} else {
				text.append("\t").append(names[i]).append("\t\t\t\t\t\t");
			}
			text.append("|\t").append(goals[i]).append("\t|\t").append(assists[i]).append("\t|\t").append(secondAssists[i]).append("\t|\t").append(throwaways[i]).append("\t|\t").append(ds[i]).append("\t|\t").append(ratio[i]).append("  |\t").append(totalScore[i]).append("\n");
		}
		text.append("--------------------------------------------------------------------------");
		return text.toString();
	}

	public String helpPage() {
		return """
                Frisbee Player Ranking Program Help
                --------------------------------
                
                Program Overview:
                - Display all players
                - Sort players by category
                - View individual player stats
                - Add and save players
                - Upload data from file
                
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
	
	public String viewPlayer(String name) {
		int player = -1;
		for(int i = 0; i < names.length; i++) {
			if (name.equalsIgnoreCase(names[i])) {
				player = i;
			}
		}
		if (player >= 0) {
			String text = names[player];
			text+="\n\tGoals: "+goals[player]+"\n\tAssists: "+assists[player]+"\n\tSecond assists: "+secondAssists[player]+"\n\tThrowaways: "+throwaways[player]+"\n\tDefenses: "+ds[player]+"\n\tRatio of assists + second assists to throwaways: "+ratio[player]+"\n\tTotal score: "+totalScore[player];
			return text;
		} else {
			return "No player by the name of "+ name+" found.";
		}
		
	}
	public void addPlayer(String _name, int _goals, int _assists, int _secondAssists, int _throwaways, int _defenses) {
		// Extend the length of all arrays by one
		names = Arrays.copyOf(names, names.length + 1);
		goals = Arrays.copyOf(goals, goals.length + 1);
		assists = Arrays.copyOf(assists, assists.length + 1);
		secondAssists = Arrays.copyOf(secondAssists, secondAssists.length + 1);
		throwaways = Arrays.copyOf(throwaways, throwaways.length + 1);
		ds = Arrays.copyOf(ds, ds.length + 1);
		ratio = Arrays.copyOf(ratio, ratio.length + 1);
		totalScore = Arrays.copyOf(totalScore, totalScore.length + 1);

		// Add the new player to the arrays
		names[names.length - 1] = _name;
		goals[goals.length - 1] = _goals;
		assists[assists.length - 1] = _assists;
		secondAssists[secondAssists.length - 1] = _secondAssists;
		throwaways[throwaways.length - 1] = _throwaways;
		ds[ds.length - 1] = _defenses;
		ratio[ratio.length - 1] = _assists + _secondAssists;

		int i = names.length - 1;
		calculateScores(i);
	}

	public static void main(String[] args) {
		ArraysAssignment a = new ArraysAssignment();		
		Scanner s = new Scanner(System.in);
		a.readData();
		System.out.println(a.helpPage());
		System.out.println("Press enter to continue to the program.");
		s.nextLine();
		
		while (true) {
			System.out.println("There are currently " + a.names.length + " players stored in this system.");
			boolean takeInput = true;
			int choice = 0;
			
			while(takeInput) {
				System.out.println("You may:\n\t1. display all\n\t2. sort players by a category\n\t3. view a player's stats\n\t4. add a player in memory\n\t5. save current data to a file\n\t6. load data from a file\n\t7. visit the help page\n\t8. exit the program");
				String temp = s.nextLine();
				try {
					choice = Integer.parseInt(temp);
					if (choice >= 1 && choice <= 8) {
						takeInput = false;
					} else {
						System.out.println("Please ensure that you enter an integer that corresponds to one of the options.");
						System.out.println("Press enter to continue.");
						s.nextLine();
					}
				} catch (Exception e){
					System.out.println("Please ensure you enter the integer that corresponds to one of the options.");
					System.out.println("Press enter to continue.");
					s.nextLine();
				}
			}
			
			switch (choice) {
				case 1: //Display all the players and their stats
					System.out.println(a.displayAll());
					System.out.println("Press enter to return to the main menu.");
					s.nextLine();
					break;
				case 2: //Sort players by a category
					
					break;
				case 3: //View a certain player's stats
					System.out.println("Please pick a player: ");
					for(String player : a.names) {
						System.out.println("\t-\t " + player);
					}
					String player = s.nextLine();
					System.out.println(a.viewPlayer(player));
					System.out.println("Press enter to return to the main menu.");
					s.nextLine();
					break;
				case 4: //Add a player in memory
					System.out.println("Please enter the player's name: ");
					String name = s.nextLine();
					System.out.println("Please enter the player's goals: ");
					int goals = s.nextInt();
					System.out.println("Please enter the player's assists: ");
					int assists = s.nextInt();
					System.out.println("Please enter the player's second assists: ");
					int secondAssists = s.nextInt();
					System.out.println("Please enter the player's throwaways: ");
					int throwaways = s.nextInt();
					System.out.println("Please enter the player's defenses: ");
					int defenses = s.nextInt();

					a.addPlayer(name, goals, assists, secondAssists, throwaways, defenses);
					break;
				case 5: //Save current data to a file
					if (a.saveData()) {
						System.out.println("Data saved successfully.");
					} else {
						System.out.println("Data could not be saved, please try again.");
					}
					
					break;
				case 6:
					a.readData(); //Reads in the data from a file
					System.out.println("File read. Data updated.\nPress enter to return to the main menu.");
					s.nextLine();
					break;
				case 7: 
					System.out.println(a.helpPage()); //Call & print the help page
					System.out.println("Press enter to return to the main menu.");
					s.nextLine();
					break; 
				case 8:
					System.out.println("Thank you for using this program. \nGoodbye!");
					System.exit(0);
			}
		}

		
	}
}
