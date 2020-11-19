package com.techelevator;

import java.util.Scanner;

/*
 Write a command line program which prompts the user for the total bill, and the amount tendered. It should then
 display the change required.

 $ java MakeChange
 Please enter the amount of the bill: 23.65
 Please enter the amount tendered: 100.00
 The change required is 76.35
 */
public class MakeChange {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);

		printMenu();
		String menuChoice = userInput.nextLine();
		double billAmount = Double.parseDouble(menuChoice);
		System.out.println("Please enter the amount tendered: ");
		System.out.println("-------------------------------------");
		String userTender = userInput.nextLine();
		double tenderedAmount = Double.parseDouble(userTender);
		double change = tenderedAmount - billAmount;

		System.out.println("Your change is:  " + change);
		System.out.println("-------------------------------------");

		userInput.close();
	}

	public static void printMenu() {
		System.out.println("Please enter the amount of the bill: ");
		System.out.println("-------------------------------------");

	}

}
