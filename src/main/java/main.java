import Attacks.*;
import Battlers.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class main {
	static Scanner stdin = new Scanner(System.in);
	static String userInput; // Hold user's String inputs
	static int userInt; // Hold user's integers
	static String charName;

	public static void main(String[] args) {

		System.out.println("Enter your name");
		charName = stdin.nextLine();

		Player player = null;
		System.out.println("How powerful is your character (1 - 3):");
		userInt = stdin.nextInt();

		switch (userInt) {
		case 1:
			player = Player.randomPlayer(1, charName);
			break;
		case 2:
			player = Player.randomPlayer(2, charName);
			break;
		case 3:
			player = Player.randomPlayer(3, charName);
			break;
		default:
			System.out.println("Character level outside of range");
			System.exit(-1);
			break;
		}

		System.out.println("How strong of an enemy can you manage (1 - 3):");
		userInt = stdin.nextInt();
		Enemy enemy = null;
		switch (userInt) {
		case 1:
			enemy = Enemy.randomEnemy(1);
			break;
		case 2:
			enemy = Enemy.randomEnemy(2);
			break;
		case 3:
			enemy = Enemy.randomEnemy(3);
			break;
		default:
			System.out.println("Enemy level outside of range");
			System.exit(-1);
			break;
		}

		while (player.getHP() > 0 && enemy.getHP() > 0) {
			System.out.println("Enter 1 for attack. Enter 2 for Guard. Enter -1 to quit.\n ");
			userInt = stdin.nextInt();

			if (userInt == 1) {
				player.useAction(enemy, "Attack");
				if (enemy.getHP() > 0) {
					enemy.useAction(player, "Attack");
				}
			} else if (userInt == 2) {
				player.useAction(enemy, "Guard");
				if (enemy.getHP() > 0) {
					enemy.useAction(player, "Attack");
				}
			}else if(userInt == -1) {
				System.out.print("You have quit");
				System.exit(-1);
			} else {
				System.out.println("Invalid Entry\n");
			}
			player.endTurn();
			enemy.endTurn();
		}

		if (player.getHP() == 0) {
			System.out.println("You have been defeated D:\n Please try again\n");
		} else {
			System.out.println("You have vanquished your foe :D\n Congratulations!\n");
		}

	}

}
