package main.java;

import java.io.*;
import java.util.*;

public class main {
	static Scanner stdin = new Scanner(System.in);
	static String userInput; // Hold user's String inputs
	static int userInt; // Hold user's integers
	static String charName;

	public static void main(String[] args) {
		System.out.println("This is to test if I can make this executable");
		Battler person1 = new Character();
		Battler person2 = new Enemy();
		
		System.out.println("Enter your name");
		charName = stdin.nextLine();

		while (userInt != -1) {
			
			
			System.out.println("Enter 1 to be Hero. Enter 2 to be villain. Enter -1 to quit");
			userInt = stdin.nextInt();
			
			switch(userInt) {
			
			case -1: 
				
				System.out.println("You quit");
				break;
				
			case 1: 
				
				person1.setName(charName);
				System.out.println("You chose to be a hero " + person1.getName());
				break;

			case 2:  
				
				person2.setName("charName");
				System.out.println("You chose to be a villain " + person2.getName());
				break;
				
			 default: 
				
				System.out.println("Invalid entry.");
				break;
			}
			
			
				
			}
		}

	}

