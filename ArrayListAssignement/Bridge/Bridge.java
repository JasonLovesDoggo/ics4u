/**
 * @author Zoe Li
 * ICS4U0
 * Mrs. Krasteva
 * Bridge simulator
 */

import java.io.*;
import java.util.*;


public class Bridge{
	
	ArrayList<String> deck = new ArrayList<String>();
	String[] playerOne;
	String[] playerTwo;
	String[] playerThree;
	String[] playerFour;
	int pointsOne;
	int pointsTwo;
	int pointsThree;
	int pointsFour;
	Map<Character, Integer> cardValues = new HashMap<>(); //So that you can organize the face cards
	
	Bridge() { //Create the arrays for each of the player's hands
		playerOne = new String[13];
		playerTwo = new String[13];
		playerThree = new String[13];
		playerFour = new String[13];
	}
	
	public void setup() { //Read in all 52 cards from a file (yay!) 
		try {
			Scanner s = new Scanner(new File("originalDeck.txt"));
			String cards = s.nextLine();
			s.close();
			
			String[] temp = cards.split(" ");
			for(String card : temp) {
				deck.add(card);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Exiting program.");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public void shuffle() { //Shuffle the deck
		Collections.shuffle(deck);
	}
	
	public void deal() { //Hand out the cards, one to each player until all are gone
		for(int i = 0; i < 13; i++) {
			playerOne[i] = deck.get(0);
			deck.remove(0);
			playerTwo[i] = deck.get(0);
			deck.remove(0);
			playerThree[i] = deck.get(0);
			deck.remove(0);
			playerFour[i] = deck.get(0);
			deck.remove(0);
		}
	}
	
	/**
	 * Calculate the points for each of the hands
	 * Gives points for royals/ace, and checks for a void, singleton, or doubleton
	 * 
	 * @param hand[]	The cards for the program to analyze
	 * @return points	The number of points that the hand has scored
	 */
	
	private int points(String[] hand) {
		int diamonds = 0;
		int clubs = 0;
		int hearts = 0;
		int spades = 0;
		
		int points = 0;
		for(String card : hand) {
			//Check for royals or ace
			if (card.charAt(0) == 'A') {
				points+=4;
			} else if (card.charAt(0) == 'K') {
				points+=3;
			} else if (card.charAt(0) == 'Q') {
				points+=2;
			} else if (card.charAt(0) == 'J') {
				points++;
			}
			
			//Count the suits
			if (card.charAt(1) == 'D') {
				diamonds++;
			} else if (card.charAt(1) == 'C') {
				clubs++;
			} else if (card.charAt(1) == 'H') {
				hearts++;
			} else {
				spades++;
			}
		}
		
		if (diamonds == 0 || clubs == 0 || hearts == 0 || spades == 0) { //Void
			points+=3;
		}
		if (diamonds == 1 || clubs == 1 || hearts == 1 || spades == 1) { //Singleton
			points+=2;
		}
		if (diamonds == 2 || clubs == 2 || hearts == 2 || spades == 2) { //doubleton
			points++;
		}
		return points;
	}
	
	private void populateCardValues() { //Set the card values so you can compare face cards to numbered cards
		
		cardValues.put('1', 1);
		cardValues.put('2', 2);
		cardValues.put('3', 3);
		cardValues.put('4', 4);
		cardValues.put('5', 5);
		cardValues.put('6', 6);
		cardValues.put('7', 7);
		cardValues.put('8', 8);
		cardValues.put('9', 9);
		cardValues.put('T', 10);
		cardValues.put('J', 11);
		cardValues.put('Q', 12);
		cardValues.put('K', 13);
		cardValues.put('A', 14);

	}
	
	
	
	private void calculate() { //Get and set the points for each of the hands
		pointsOne = points(playerOne);
		pointsTwo = points(playerTwo);
		pointsThree = points(playerThree);
		pointsFour = points(playerFour);
	}
	
		
	public String getPlayerStats(int i) { //Sort the cards into their suits, then sort each card in their suits. Then, add all the cards to the string and return it
		ArrayList<Character> spades = new ArrayList<Character>();
		ArrayList<Character> hearts = new ArrayList<Character>();
		ArrayList<Character> clubs = new ArrayList<Character>();
		ArrayList<Character> diamonds = new ArrayList<Character>();
		
		ArrayList<String> hand = new ArrayList<String>();
		
		switch (i) {
			case 1:
				for(String card : playerOne) {
					hand.add(card);
				}
				break;
			case 2:
				for(String card : playerTwo) {
					hand.add(card);
				}
				break;
			case 3:
				for(String card : playerThree) {
					hand.add(card);
				}
				break;
			case 4:
				for(String card : playerFour) {
					hand.add(card);
				}
		}
		
		for(String card : hand) { //Sort cards into their suits
						
			if (card.charAt(1) == 'D') {
				diamonds.add(card.charAt(0));
			} else if (card.charAt(1) == 'C') {
				clubs.add(card.charAt(0));
			} else if (card.charAt(1) == 'H') {
				hearts.add(card.charAt(0));
			} else { //Spades
				spades.add(card.charAt(0));
			}
		}
		
		
		populateCardValues();
		
		Collections.sort(spades, (c1,c2) -> Integer.compare(cardValues.get(c2), cardValues.get(c1)));
		Collections.sort(hearts, (c1,c2) -> Integer.compare(cardValues.get(c2), cardValues.get(c1)));
		Collections.sort(diamonds, (c1,c2) -> Integer.compare(cardValues.get(c2), cardValues.get(c1)));
		Collections.sort(clubs, (c1,c2) -> Integer.compare(cardValues.get(c2), cardValues.get(c1)));
		//sortHands(); //Sort the face cards 
		
		String text = "";
		
		if (spades.isEmpty()) {
			text+="No spades!";
		} else {
			text += "Spades: ";
			for(char num : spades) {
				text+=num + " ";
			}
		}
		
		if (hearts.isEmpty()) {
			text+="\nNo hearts!";
		} else {
			text+="\n";
			text += "Hearts: ";
			for(char num : hearts) {
				text+=num + " ";
			}
		}
		
		if (diamonds.isEmpty()) {
			text+="\nNo diamonds!";
		} else {
			text+="\n";
			text += "Diamonds: ";
			for(char num : diamonds) {
				text+=num + " ";
			}
		}
		
		if (clubs.isEmpty()) {
			text+="\nNo clubs!";
		} else {
			text+="\n";
			text += "Clubs: ";
			for(char num : clubs) {
				text+=num + " ";
			}
		}
		text+="\n";
		
		return text;
		
	}
	
	public String toString() { //returns the string of all the stats for printing
		calculate(); //Make sure all points are correctly calculated
		String text = "";
		for (int i = 1; i <= 4; i++) {
			text+="\n\nStats for player " + i + ": \n";
			text+=getPlayerStats(i);
			text+="\nPoints: ";
			switch (i) {
				case 1: 
					text+=pointsOne;
					break;
				case 2:
					text+=pointsTwo;
					break;
				case 3:
					text+=pointsThree;
					break;
				default:
					text+=pointsFour;
			}
			text+="\n";
		}
		
		text+="\n"+getWinner();
		
		return text;
	}
	
	public String getWinner() { //Calculate who wins. If it is a tie, all players who tied will be printed out
		if (pointsOne > pointsTwo && pointsOne > pointsThree && pointsOne > pointsFour) {
			return "Player one wins with " + pointsOne + " points!";
		} else if (pointsTwo > pointsOne && pointsTwo > pointsThree && pointsTwo > pointsFour) {
			return "Player two wins with " + pointsTwo + " points!";
		} else if (pointsThree > pointsOne && pointsThree > pointsTwo && pointsThree > pointsFour) {
			return "Player three wins with " + pointsThree + " points!";
		} else if (pointsFour > pointsOne && pointsFour > pointsTwo && pointsFour > pointsThree) {
			return "Player four wins with " + pointsFour + " points!";
		} else { //It's a tie
			
			String temp = "player one";
			int max = pointsOne;
			if (max < pointsTwo) {
				max = pointsTwo;
				temp = "";
			} else if (max == pointsTwo) {
				if (temp.length() > 8) {
					temp+=" and";
				}
				temp+=" player two";
			}
			if (max < pointsThree) {
				max = pointsThree;
				temp = "";
			} else if (max == pointsThree) {
				if (temp.length() > 8) {
					temp+=" and";
				}
				temp+=" player three";
			}
			if (max < pointsFour) {
				max = pointsFour;
				temp = "";
			} else if (max == pointsFour) {
				if (temp.length() > 8) {
					temp+=" and";
				}
				temp+=" player four";
			}
			
			return "It's a tie between " + temp + " with " + max + " points!";
		}
	}
	
	public static void main(String[] args) {
		Bridge i = new Bridge();
		i.setup();
		i.shuffle();
		System.out.println("Deck has been shuffled");
		i.deal();
		System.out.println("Deck has been dealt.\nCalculating scores . . .");
		System.out.println(i);
		
		
	}
	
}