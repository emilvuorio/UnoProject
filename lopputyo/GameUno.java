package lopputyo;
import java.util.Random;
import java.util.Scanner;
public class GameUno {
	
	private static Scanner reader = new Scanner(System.in);
	
	public static void startGame() {
		
			
		
		System.out.println("Lets shuffle the deck first...");
		getDeck.shuffle(); //creates the deck from getDeck class
		getDeck.drawHand(); //draws hand for players
		
		System.out.println("Player 1's hand: " +getDeck.player1Hand); //outputs players hands
		System.out.println("Player 2's hand: " +getDeck.player2Hand);
		String goesFirst = ""; //initializing goesFirst variable
		int decider = drawStartingOrder(); //draws starting order
		if (decider == 0) { //assigns text to variable goesFirst
			 goesFirst = "Player 1";
		}
		
		if (decider == 1 ) {
			goesFirst = "Player 2";
		}
		System.out.println("Let's play! " +goesFirst +" starts");
		
		String drawCard = getDeck.drawCard(); //gets the starting card
		while(isSpecialCard(drawCard)) {
			drawCard = getDeck.drawCard();
		}
		System.out.println("First card!" + "\n" +drawCard);
		String playedCard = drawCard; //this is for later use
		String cardOnTop = cardOnTop(playedCard); //later use
		boolean gameOn = true; //loops the game
		boolean skipTurn = false;
		int roundCounter = 0;
		boolean canPlay = true;
		int cardPicker = 0;//initializing variable
		boolean checkWin = false; //Initializing checkWin flag
		
		while(true) {
		try {
		while(gameOn) { //this is where the actual game starts
			roundCounter++;
			if (decider == 0) {
				
				System.out.println("Player 1, Which card do you want to play?");
				drawHand(decider);
				System.out.println("If you don't have valid card, draw one by typing 0");
				System.out.println("Card On Top: " +cardOnTop); //Prints the card on top
				cardPicker = Integer.parseInt(reader.nextLine()) - 1; //I'm reducing one since its more user friendly to input with correct numbers 
				if (cardPicker >= 0) { // if you dont have to draw a card
					playedCard = getDeck.player1Hand.get(cardPicker); //users card
					
					canPlay = checkIfValid(playedCard, cardOnTop); //check if card is valid
					if (canPlay) { // if it was valid continue
						cardOnTop = cardOnTop(playedCard); //play the card on the table
						getDeck.player1Hand.remove(cardPicker); //update hand
						if(getDeck.player1Hand.size() == 1) {
							System.out.println("UNO UNO UNO!!!");
						}
						if(isSpecialCard(playedCard)) {
							if(defineSpecialCardAction(decider, playedCard));
							skipTurn = true;
							cardOnTop = defineColor(playedCard);

						}
						checkWin = checkWin(); //check if it was last card
						if (checkWin == true) { //if it was, stop loop
							break;
						}
						if(skipTurn) {
							skipTurn(decider); // check if played card was returned with value skip turn
						}
						else {
							decider++; //if it wasn't player 2's turn
						}
						skipTurn = false; // resets skipTurn value before new round
					}
					else if(canPlay == false) { //if the card wasn't valid
						System.out.println("You can't play that card, you lost your turn");
						decider++; // p2 turn
					}
				}
				else if(cardPicker < 0) { //if user needs to draw a card
					drawCard = getDeck.drawCard(); // new card
					getDeck.player1Hand.add(drawCard); //inserts it into hand
					decider++; //p2 turn
				}
				
				
			}
			// FROM HERE THE SAME THING IS REPEATED OVER AGAIN BUT P2 PERSPECTIVE
			if (decider == 1) {
				
				System.out.println("Player 2, Which card do you want to play?");
				drawHand(decider);
				System.out.println("If you don't have valid card, draw one by typing 0");
				System.out.println("Card On Top: " +cardOnTop);
				cardPicker = Integer.parseInt(reader.nextLine()) - 1;
				if (cardPicker >= 0) {
					playedCard = getDeck.player2Hand.get(cardPicker);
					
					canPlay = checkIfValid(playedCard, cardOnTop);
					if (canPlay) {
						
						cardOnTop = cardOnTop(playedCard);
						getDeck.player2Hand.remove(cardPicker);
						if(getDeck.player2Hand.size() == 1) {
							System.out.println("UNO UNO UNO!!!");
						}
						
						if(isSpecialCard(playedCard)) {
							defineSpecialCardAction(decider, playedCard);
							cardOnTop = defineColor(playedCard);
							skipTurn = true;
						}
						checkWin = checkWin();
						if (checkWin == true) {
							break;
						}
						if(skipTurn) {
							skipTurn(decider);
						}
						else {
							decider--;
						}
						skipTurn = false;
					}
					else if(canPlay == false) {
						System.out.println("You can't play that card, you lost your turn");
						decider--;
					}
					
				}
				else if(cardPicker < 0) {
					drawCard = getDeck.drawCard();
					getDeck.player2Hand.add(drawCard);
					decider--;
				}
			}
		}
		
		endCredits.gameEnded(decider, roundCounter); // after the game has ended rolls the credits
		
		}
	catch (Exception e) {
			System.out.println("Something went wrong " +e); //if user inputs wrong input
			
		}
		}
	}
	






	private static void drawHand(int decider) { //this method prints hand in  more readable way
        if (decider == 0) { // first it determines which turns is it

            System.out.println("Player 1, input number that matches the card you want to play!");
            for (int i = 0; i < getDeck.player1Hand.size(); i++) { //loops trough the arraylist
                int x = i + 1; //this prints the number you need to input to play the card
                System.out.print(x + ": " +getDeck.player1Hand.get(i)+ " | "); 
                if (i % 2 != 0) { //for every other it creates line break
                    System.out.print("\n");
                }
            }
            System.out.println("Here's your cards");

        }
        if (decider == 1) { // same but p2's perspective

            System.out.println("Player 2, input number that matches the card you want to play!");
            for (int i = 0; i < getDeck.player2Hand.size(); i++) {
                int x = i + 1;
                System.out.print(x + ": " +getDeck.player2Hand.get(i)+ " | ");
                if (i % 2 != 0) {
                    System.out.print("\n");
                }
            }
            System.out.println(" Here's your cards");
        }

    }







	private static String defineColor(String playedCard) { // this method is for defining if the card is black and color needs to be changed
		while(true) { 
		int colorPicker = 0;
		try {
		if (playedCard.contains("Black")) { //check if black
			String[] color = {"Green", "Red", "Blue", "Yellow"};
			System.out.println("Input number that matches the wanted color!");
			for (int i = 0; i < color.length; i++) { //loops trough list of colors
				int x = i +1;
				System.out.println(x +" " + color[i]);
			}
			colorPicker = Integer.parseInt(reader.nextLine()) -1;
			String chosenColor = color[colorPicker];
			return chosenColor;
			
		
		}
		else {
			return playedCard; //if card wasnt black
		}
		} catch (Exception e){
			System.out.println("Something went wrong" +e);
		}
		}
	}


	private static boolean defineSpecialCardAction(int decider, String playedCard) { //for defining what to do if special card is played
		
		if (playedCard.contains("+2")) { // adds 2 to enemys hand
			if (decider == 0) {
				for (int i = 0; i < 2; i++) {
					String drawCard = getDeck.drawCard();
					getDeck.player2Hand.add(drawCard);
					
				}
				return true; //return skipTurn = true
			}
			
			if (decider == 1) {
				for (int i = 0; i < 2; i++) {
					String drawCard = getDeck.drawCard();
					getDeck.player1Hand.add(drawCard);
					
				}
				return true;
			}
		}
		if(playedCard.contains("+4")) { //adds 4 to enemys hand
			if (decider == 0) {
				for (int i = 0; i < 4; i++) {
					String drawCard = getDeck.drawCard();
					getDeck.player2Hand.add(drawCard);
					
					
				}
				return true;
			}
			
			if (decider == 1) {
				for (int i = 0; i < 4; i++) {
					String drawCard = getDeck.drawCard();
					getDeck.player1Hand.add(drawCard);
					
				}
			}return true;
			
		}
		if(playedCard.contains("Reverse")) {
			return true; //since this only made for 2 players it will only skip turns
		}
		if(playedCard.contains("Skip")) {
			return true; //skips turn
		}
		return false; //if it was normal card
		
	}
	
	private static int skipTurn(int decider) { //skips the turn
		if (decider == 0) {
			return decider = 0;
		}
		else {
			return decider = 1;
		}
		
	}
		


	private static boolean checkIfValid(String playedCard, String cardOnTop) { //method check if its valid
		String[] splitPlayedCard = playedCard.split("\\s", 2); //splits the card between color and values
		String[] splitCardOnTop = cardOnTop.split("\\s", 2);
		if (playedCard.contains("Black")) { // if it is black card
			return true;
		}
		for (int i = 0; i < splitPlayedCard.length; i++) { //compare if any matches
			for (int x = 0; x <splitCardOnTop.length; x++) {
				if (splitPlayedCard[i].equals(splitCardOnTop[x])) {
					return true;
				}
			}
		}
		
		
		
		return false; // returns false if it wasn't valid card
	}


	public static String cardOnTop(String playedCard) { // checks the card on top
		String cardOnTop = playedCard;
		return cardOnTop;
	}
	
	public static boolean isSpecialCard(String playedCard) {
		if (playedCard.contains("Black") || playedCard.contains("+") || playedCard.contains("Skip")
				|| playedCard.contains("Reverse")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static int drawStartingOrder() { // draws starting order
		Random rand = new Random();
		int decider = rand.nextInt(2);
		return decider;

	}
	
	public static boolean checkWin(){ // check if win
		if((getDeck.player1Hand.size() == 0) || (getDeck.player2Hand.size() == 0)) {
			return true;
		}
		else {
			return false;
		}
	}
}
	