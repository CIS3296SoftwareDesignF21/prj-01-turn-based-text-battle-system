package main.java;

import java.io.*;
import java.util.*;

public class main {
	static Scanner stdin = new Scanner(System.in);
	static String userInput; // Hold user's String inputs
	static int userInt; // Hold user's integers

	public static void main(String[] args) {
		System.out.println("This is to test if I can make this executable");
		Battler person1 = new Character();
		Battler person2 = new Enemy();

		while (userInt != -1) {
			System.out.println("Enter 1 to be Hero. Enter 2 to be villain. Enter -1 to quit");
			userInt = stdin.nextInt();

			if (userInt == 1) {

				System.out.println("You chose " + person1.getName());

			} else if (userInt == 2) {

				System.out.println("You chose " + person2.getName());

			}
			else {
				
				System.out.println("You quit.");
			}
				

		}

	}
}
