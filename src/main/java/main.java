import Attacks.*;
import Battlers.*;

import java.util.*;

public class main {
	static Scanner stdin = new Scanner(System.in);
	static String userInput; // Hold user's String inputs
	static int userInt; // Hold user's integers
	static String charName;
	static boolean fin = false;
	static Player player = null;
	static Enemy enemy = null;

	public static void main(String[] args) {
		System.out.println("Enter your name:");
		charName = stdin.nextLine();

		setDifficulty();
		battleLoop();
	}

	private static void setDifficulty(){
		//System.out.println("How powerful is your character (1 - 3):");
		userInt = 0;
		while(userInt > 3 || userInt < 1) {
			System.out.println("Select your power level (1-3):");
			userInt = stdin.nextInt();
		}
		player = Player.randomPlayer(userInt, charName);

		//System.out.println("How strong of an enemy can you manage (1 - 3):");
		userInt = 0;
		while(userInt > 3 || userInt < 1) {
			System.out.println("Select your enemy's power level (1-3)");
			userInt = stdin.nextInt();
		}
		enemy = Enemy.randomEnemy(userInt);
	}

	static private void battleLoop(){
		while(!fin){
			while (player.getHP() > 0 && enemy.getHP() > 0) {
				//System.out.println("Enter 1 for attack. Enter 2 for Special. Enter 3 for Guard. Enter -1 to quit.");
				System.out.println("1: Attack 2: Special 3: Guard -1: Quit");
				userInt = stdin.nextInt();
				switch(userInt){
					case 1:
						player.defaultCurrentAttack();
						player.useAction(enemy, "Attack");
						if (enemy.getHP() > 0) {
							enemy.useAction(player, "Attack");
						}
						break;
					case 2:
						player.setCurrentAttack(player.attackMenu(player.getSpecialAttacksArray()));
						if(player.usedDefaultAttack()){
							player.useAction(enemy, "Cower");
						}
						else{
							player.useAction(enemy, "Attack");
						}
						if (enemy.getHP() > 0) {
							enemy.useAction(player, "Attack");
						}
						player.defaultCurrentAttack();
						break;
					case 3:
						player.useAction(enemy, "Guard");
						if (enemy.getHP() > 0) {
							enemy.useAction(player, "Attack");
						}
						break;
					case -1:
						System.out.print(player.getName() + " fled the scene!\nThe battle is over!");
						System.exit(1);
						break;
					default:
						System.out.println(player.getName() + " fumbled and pressed an invalid number!");
						if (enemy.getHP() > 0) {
							enemy.useAction(player, "Attack");
						}
						break;
				}
				player.endTurn();
				enemy.endTurn();
			}
			fin = checkIfFinished();
		}
	}

	private static boolean checkIfFinished() {
		Scanner sc = new Scanner(System.in);
		if(player.getHP() <= 0){
			System.out.println("You have been defeated D:");
		} else if(enemy.getHP() <= 0) {
			System.out.println("You have vanquished your foe :D\nCongratulations!");
		}
		else{
			System.out.println("Something ended... I guess???");
		}

		while(true) {
			System.out.println("Do you want to play again? Enter \"yes\" or \"no\".");
			String response = sc.nextLine();
			if (response.equals("yes")) {
				System.out.println("NEW ROUND!!!\n");
				setDifficulty();
				player.recoverAll();
				enemy.recoverAll();
				fin = false;
				break;
			} else if (response.equals("no")) {
				System.out.print("Thank you for playing.");
				fin = true;
				break;
			}
		}
		return fin;
	}
}
