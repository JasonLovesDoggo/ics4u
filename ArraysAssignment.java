import java.util.*;

public class ArraysAssignment {
	private String[] names = {"Matt Murl", "Chris Kam", "Gabe Isaac", "Lucy Gao", "Joseph Wang", "Clara Liu", "Veronica Davey-Young", "Chengshu Bi", "Celine Lai", "Hannah McKenna", "Caleb Soroka"};
	private int[] goals = {19, 0, 2, 6, 12, 6, 7, 15, 10, 10, 5};
	private int[] assists = {13, 4, 6, 2, 2, 3, 1, 8, 4, 10, 3};
	private int[] secondAssists = {7, 4, 2, 3, 1, 5, 3, 3, 7, 6, 1};
	private int[] throwaways = {8, 2, 6, 5, 4, 11, 5, 16, 4, 3, 1};
	private int[] ds = {13, 0, 2, 4, 4, 5, 4, 8, 5, 4, 2};
	private double[] ratio;
	private int[] totalScore;
	
	ArraysAssignment() {
		ratio = new double[names.length];
		totalScore = new int[names.length];
	}
	
	public void setData() {
		for(int i = 0; i < names.length; i++) {
			ratio[i] = (assists[i]+secondAssists[i])/throwaways[i]; //Calculate the ratio of assists and second assists to the number of throwaways
			totalScore[i] = goals[i]*3 + assists[i]*3 + secondAssists[i] - throwaways[i]*2 + ds[i]*2;
		}
	}
	
	public void sort(String sortBy) { //Jason's weird sorting thing
	
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
		return text;
	}
	
	public static void main(String[] args) {
		ArraysAssignment a = new ArraysAssignment();
		Scanner s = new Scanner(System.in);
		a.setData();
		
		System.out.println("This program stores the stats for ultimate frisbee players.\nThere are currently " + a.names.length + " players stored in this system.");
		boolean takeInput = true;
		int choice = 0;
		
		while(takeInput) {
			System.out.println("You may:\n\t1. display all\n\t2. display the best player based on a category");
			String temp = s.nextLine();
			try {
				choice = Integer.parseInt(temp);
				if(choice >= 1 && choice <= 2) {
					takeInput = false;
				} else {
					System.out.println("Please ensure that you enter an integer that corresponds to one of the options.");
				}
			} catch (Exception e) {
				System.out.println("Please ensure you enter the integer that corresponds to one of the options.");
			}
		}
		
		switch (choice) {
			case 1:
				System.out.println(a.displayAll());
				break;
			case 2:
				break;
		}
		
	}
	
}