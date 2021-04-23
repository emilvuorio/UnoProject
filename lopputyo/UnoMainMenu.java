package lopputyo;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UnoMainMenu {
	
	/*Since this is my first ever bigger project and also a school project,
	 *I'm experiencing with different methods and ways to do stuff
	 * This program is written between 21.4.2021 and 23.4.2021 as a final project of my first ever Java Course "Basics of programming" at TUAS
	 * Code is 90 % original written by Emil Vuorio
	 * Only things that are copied are reading, creating and writing to a file from w3schools.com
	 */
	
	private static Scanner reader = new Scanner(System.in); // for the next loop to know how to continue program
	public static void main (String args[]) { 
		
		//some basic info about the program
		System.out.println("Welcome to Uno");
		System.out.println("This program is written as a part of the course \"OPR 2021\". This program is made by Emil Vuorio");
		int choice = 0; // this will be used to determine what user wants to do later
		
		while(true) { //this while loops function is to make sure that users inputs correct input
		try {	
		System.out.println("Input \"1\" to start the game, input \"2\" to print instructions");
		
		 choice = Integer.parseInt(reader.nextLine()); //we have to parse it to integer so the loop functions properly
		
			if (choice == 1 || choice == 2){ // if user inputs correct input loop ends
				break;
			}
		
		
			else {
				System.out.println("You have to input either 1 or 2"); // if user inputs wrong number the loop continues
				continue;
			}
		}
		catch (Exception e) { //if input is invalid
			System.out.println("You have to input either 1 or 2"); //if user inputs wrong input such as letter the loop continues
			
		}		
			
		} // end of while loop
		
		
		if (choice == 1) { //program keeps going on users input
			GameUno.startGame(); // changes to another class called GameUno and starts method startGame
		}
		else if (choice == 2) {
			printInstructions(); //program prints instructions written in text file
		}
		else {
			System.out.println("Something went wrong"); // if somehow choice is not 1 or 2
			main(args); // starts main again
		}
}
	
	private static void printInstructions() { // this method prints instructions for the game
		  try {
		      File myObj = new File("D:\\AMK\\Ohjelmointi\\java\\unoInstructions.txt"); // location of the text file that contains instructions
		      Scanner myReader = new Scanner(myObj); // scanner to read the file
		      while (myReader.hasNextLine()) { //loop to print the file
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		  } catch (FileNotFoundException e) { // if something goes wrong
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		  }
		  finally { // this is a must in order to start new try method
			  
		  }
		  
		      int choice = 0; // this will be used to determine what user wants to do later
		      while(true) { // loop to make sure user inputs correct input
		    	  try {
		    	  System.out.println("Input \"1\" to start the game or \"2\" to exit program");
		      choice = Integer.parseInt(reader.nextLine()); //we have to parse it to integer so the loop functions properly
		      if (choice == 1 || choice == 2){  // if user inputs correct input loop ends
					break;
				}
			
			
				else {
					System.out.println("You have to input either 1 or 2"); // if user inputs wrong number
					continue;
				}
		      
		     
		    
		    } catch (Exception e) {
		    	System.out.println("You have to input either 1 or 2"); // if user inputs wrong input e.g letter
		    }
	}
		      
		      
		      if (choice == 1) {
		    	 GameUno.startGame(); // starts the game
		      }
		      else if (choice == 2) { // just wanted to try this function out
		    	  System.exit(0);
		      }
		      
		      else {
		    	  System.out.println("Something went wrong, please restart the program"); //if something goes wrong
		      }

}
}