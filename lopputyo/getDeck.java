package lopputyo;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class getDeck { //class that generates UnoDeck and player's hands
	private static Random rand = new Random();
	public static ArrayList<String> cards = new ArrayList<String>(); //ArrayList for card deck
	public static ArrayList<String> player1Hand = new  ArrayList<String>(); //For player 1
	
	public static ArrayList<String> player2Hand = new  ArrayList<String>(); //For player 2
	
	
	public static void shuffle() { //generates the deck, there are plenty of better ways to do this but this is how I did it
		for (int i = 0; i < 13; i++) {
			if (i < 10) {
			cards.add("Red " +i);
			cards.add("Red " +i);
			}
			if (i == 10) {
				cards.add("Red Skip");
				cards.add("Red Skip");
			}
			if (i == 11) {
				cards.add("Red Reverse");
				cards.add("Red Reverse");
			}
			if (i == 12) {
				cards.add("Red +2");
				cards.add("Red +2");
			}
		}
		for (int i = 0; i < 13; i++) {
			if (i < 10) {
			cards.add("Yellow " +i);
			cards.add("Yellow " +i);
			}
			if (i == 10) {
				cards.add("Yellow Skip");
				cards.add("Yellow Skip");
			}
			if (i == 11) {
				cards.add("Yellow Reverse");
				cards.add("Yellow Reverse");
			}
			if (i == 12) {
				cards.add("Yellow +2");
				cards.add("Yellow +2");
			}
		}
		for (int i = 0; i < 13; i++) {
			if (i < 10) {
			cards.add("Blue " +i);
			cards.add("Blue " +i);
			}
			if (i == 10) {
				cards.add("Blue Skip");
				cards.add("Blue Skip");
			}
			if (i == 11) {
				cards.add("Blue Reverse");
				cards.add("Blue Reverse");
			}
			if (i == 12) {
				cards.add("Blue +2");
				cards.add("Blue +2");
			}
		}
		for (int i = 0; i < 13; i++) {
			if (i < 10) {
			cards.add("Green " +i);
			cards.add("Green " +i);
			}
			if (i == 10) {
				cards.add("Green Skip");
				cards.add("Green Skip");
			}
			if (i == 11) {
				cards.add("Green Reverse");
				cards.add("Green Reverse");
			}
			if (i == 12) {
				cards.add("Green +2");
				cards.add("Green +2");
			}
		}
		for (int i = 0; i < 4; i++) {
			if (i > 2) {
				cards.add("Black +4");
				cards.add("Black +4");
			}
			if (i < 2) {
				cards.add("Black Wild");
				cards.add("Black Wild");
			}
		}
		System.out.println("Done!" + "\n" +"Let's start dealing cards!");
		
	}
	
	public static void drawHand() { // this generates players hand
		
	player1Hand.clear();
	player2Hand.clear();
	int handSize = 7; //define hand size in the beginnning
	for (int x = 0; x < handSize; x++) {
	int index = rand.nextInt(cards.size()); // generates random index of cards ArrayList
	player1Hand.add(cards.get(index)); //adds it to player1 hand
}
	Collections.sort(getDeck.player1Hand);
	for (int x = 0; x < handSize; x++) { //same for player 2
	int index = rand.nextInt(getDeck.cards.size());
	player2Hand.add(getDeck.cards.get(index));
}
	Collections.sort(getDeck.player2Hand);
	}
	
	public static String drawCard() { //this is for drawing a new card
	int index = rand.nextInt(cards.size());
	String newCard = cards.get(index);
	return newCard;
	}
	

}
