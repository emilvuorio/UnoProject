package lopputyo;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class endCredits {
	
	
	public static void gameEnded(int winner, int rounds) {
		Scanner reader = new Scanner(System.in);
		String playerWon = " ";
		if (winner == 0) {
			System.out.println("PLAYER 1 WON");
			playerWon = "Player 1";
		}
		
		else if (winner == 1 ) {
			System.out.println("PLAYER 2 WON");
			playerWon = "Player 2";
		}
		System.out.println("Game lasted "+rounds +" rounds");
		createFile(rounds);
		WriteToFile(playerWon, rounds);
		System.out.println("Do you want to play again?");
		System.out.println("Input 1 to play again or 2 to exit program");
		while(true) {
		try {	
		
		int choice = reader.nextInt();
		
	    if (choice == 1) {
	    	 GameUno.startGame(); // starts the game
	      }
	      else if (choice == 2) { // just wanted to try this function out
	    	  System.exit(0);
	      }
	      
	      else {
	    	  System.out.println("Something went wrong, please restart the program"); //if something goes wrong
	      }
		} catch (Exception e) {
			System.out.println("Something went wrong" +e);
		}
		}
	}

	private static void createFile(int rounds) {
		try {
		      File myObj = new File("D:\\AMK\\Ohjelmointi\\java\\UnoWinIn" +rounds+"rounds.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created and game saved: " + myObj.getName());
		        
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  
		
	}

	private static void WriteToFile(String playerWon, int rounds) {
		try {
		      FileWriter myWriter = new FileWriter("D:\\AMK\\Ohjelmointi\\java\\UnoWins.txt");
		      myWriter.write("This game's winner is " +playerWon +"!!! Player won in " +rounds +" rounds!");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}

}
