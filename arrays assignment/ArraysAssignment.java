/*
Name: Jason, Zoe
Date: March 4, 2025
Teacher: Ms. Krasteva
Description: Practice using and manipulating parallel arrays. This program ranks frisbee players based on their goals, assists, second assists, throwaways and ds, and includes an array to store the ratio of assists + second assists to the number of throwaways.
*/
/* 
TODOs:
- search results by player - Zoe
- sort players by total score/each category - Jason
- Add player in memory - Jason
- Save current data to file - Jason
- Load data from file - Zoe
- Help page where we describe what all values are - Zoe


- Save current state in file?
*/

import java.util.*;
import java.io.File;

public class ArraysAssignment{
	
	Scanner s;
	private int n;
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
			File file = new File("stats.txt");
			s = new Scanner(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readData() {
		int n = s.nextInt(); //read in the number of players
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
			double ratioValue = ((double)assists[i]+secondAssists[i])/throwaways[i]; //Calculate the ratio of assists and second assists to the number of throwaways
			ratio[i] = Math.round(ratioValue*10)/10.0;
			totalScore[i] = goals[i]*3 + assists[i]*3 + secondAssists[i] - throwaways[i]*2 + ds[i]*2;
		}
	}
	
	public void sort(String sortBy) { //Copy the arrays, then sort them. Return sorted arrays (no return type right now because I'm lazy)
		
	}
	
	public String displayAll() {
		String text = "\tPlayer name\t\t\t\t|\tG\t|\tA\t|\t2A\t|\tTA\t|\tDs\t| Ratio |\tTotal\n";
		text+="--------------------------------------------------------------------------";
		text+="\n";
		for(int i = 0; i < names.length; i++) {
			if (names[i].length() > 20) {
				text+="\t"+names[i].substring(0, 17) +"...\t";
			} else if (names[i].length() > 17) {
				text+="\t"+names[i]+"\t";
			} else if (names[i].length() > 14) {
				text+="\t"+names[i]+"\t\t";
			} else if (names[i].length() > 11) {
				text+="\t"+names[i]+"\t\t\t";
			} else if (names[i].length() > 8) {
				text+="\t"+names[i]+"\t\t\t\t";
			} else if (names[i].length() > 5) {
				text+="\t"+names[i]+"\t\t\t\t\t";
			} else {
				text+="\t"+names[i]+"\t\t\t\t\t\t";
			}
			text+="|\t"+goals[i]+"\t|\t"+assists[i]+"\t|\t"+secondAssists[i]+"\t|\t"+throwaways[i]+"\t|\t"+ds[i]+"\t|\t"+ratio[i]+"  |\t"+totalScore[i]+"\n";
		}
		text+="--------------------------------------------------------------------------";
		return text;
	}
	
	public static void main(String[] args) {
		ArraysAssignment a = new ArraysAssignment();		
		Scanner s = new Scanner(System.in);
		a.readData();
		boolean run = true;
		
		while (run) {
			System.out.println("This program stores the stats for ultimate frisbee players.\nThere are currently " + a.names.length + " players stored in this system.");
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
				case 1:
					System.out.println(a.displayAll());
					System.out.println("Press enter to return to the main menu.");
					s.nextLine();
					break;
				case 2: 
					
					break;
				case 3: 
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7: 
					break; 
				case 8:
					System.out.println("Thank you for using this program. Goodbye!");
					System.exit(0);
					break;
			}
		}

		
	}
}
