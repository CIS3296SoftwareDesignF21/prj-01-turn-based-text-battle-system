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
			try {
				userInt = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}
		player = Player.randomPlayer(userInt, charName);

		//System.out.println("How strong of an enemy can you manage (1 - 3):");
		userInt = 0;
		while(userInt > 3 || userInt < 1) {
			System.out.println("Select your enemy's power level (1-3)");
			try {
				userInt = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}
		enemy = Enemy.randomEnemy(userInt);
	}

	static private void battleLoop(){
		boolean skipTurn = false;
		//numbers for the actions. if the action isn't usable, it's not shown.
		int special = -3, magic = -4, guard = -5;
		if(!player.specialAttacksEmpty()) special = 2;
		if(!player.magicAttacksEmpty()) magic = Math.max(2,special+1);
		guard = Math.max(Math.max(2, special+1), magic+1);
		while(!fin){
			while (player.getHP() > 0 && enemy.getHP() > 0) {
				//System.out.println("Enter 1 for attack. Enter 2 for Special. Enter 3 for Guard. Enter -1 to quit.");
				System.out.print("HP: " + player.getHP() + "/" + player.getMaxHP());
				System.out.println(" MP: " + player.getMP() + "/" + player.getMaxMP());
				//System.out.println("1: Attack 2: Special 3: Magic 4: Guard -1: Quit 0: Options ");
				System.out.print("1: Attack");
				if(special > 0) System.out.print(" " + special + ": Special");
				if(magic > 0) System.out.print(" " + magic + ": Magic");
				System.out.println(" " + guard + ": Guard -1: Quit 0: Options");
				try {
					userInt = stdin.nextInt();
				} catch (InputMismatchException e){stdin.next(); userInt = 123;}
				if(userInt == 1){
					player.defaultCurrentAttack();
					player.useAction(enemy, "Attack");
					if (enemy.getHP() > 0) {
						enemy.useAction(player, "Attack");
					}
				} else if(userInt == special && special > 0){
					if(player.specialAttacksEmpty()) skipTurn = true;
					else handleMenu(player.getSpecialAttacksArray());
				} else if(userInt == magic && magic > 0){
					if(player.magicAttacksEmpty()) skipTurn = true;
					else handleMenu(player.getMagicAttacksArray());
				} else if(userInt == guard){
					player.useAction(enemy, "Guard");
					if (enemy.getHP() > 0) {
						enemy.useAction(player, "Attack");
					}
				} else if(userInt == 0){
					skipTurn = true;
					changeTextSpeed();
				} else if(userInt == -1){
					System.out.print(player.getName() + " fled the scene!\nThe battle is over!");
					System.exit(1);
				} else{
					System.out.println(player.getName() + " fumbled and pressed an invalid number!\n"); Attack.sleep();
					if (enemy.getHP() > 0) {
						enemy.useAction(player, "Attack");
					}
				}
				if(!skipTurn) {
					player.endTurn();
					enemy.endTurn();
				}
				else skipTurn = false;
			}
			fin = checkIfFinished();
		}
	}

	private static void handleMenu(Attack[] attacks){
		player.setCurrentAttack(player.attackMenu(attacks));
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
		Attack.sleep();

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

	public static void changeTextSpeed(){
		userInt = 0;
		while(userInt > 5 || userInt < 1) {
			System.out.println("Enter Text Speed (1-5):");
			try{
				userInt = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}
		Attack.changeSleepTime(userInt);
	}
}
