import Attacks.*;
import Battlers.*;
import RandomGeneration.RandomEnemy;
import RandomGeneration.RandomPlayer;

import java.util.*;

public class main {
	static Scanner stdin = new Scanner(System.in);
	static String userInput; // Hold user's String inputs
	static int userInt; // Hold user's integers
	static String charName;
	static boolean fin = false;
	static Player player = null;
	static ArrayList<Player> allies = null;
	static ArrayList<Enemy> enemies = null;

	public static void main(String[] args) {
		System.out.println("Hello and welcome to the battle simulator!");
		System.out.println("Enter your name:");
		charName = stdin.nextLine();

		setDifficulty();
		battleLoop();
	}

	private static void setDifficulty(){
		int classChoice = 0;
		while(classChoice > 2 || classChoice < 1) {
			System.out.println("Would you like to play as a Fighter or a Mage?\n" +
					"1: Fighter " + "2: Mage");
			try {
				classChoice = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}

		int level = 0;
		while(level > 3 || level < 1) {
			System.out.println("Select your power level (1-3):");
			try {
				level = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}

		if(classChoice == 1){
			player = RandomPlayer.randomFighter(level,charName);
		} else {
			player = RandomPlayer.randomMage(level,charName);
		}

		userInt = -1;
		while(userInt < 0) {
			System.out.println("How many allies would you like to have?");
			try {
				userInt = stdin.nextInt();
			} catch (InputMismatchException e) {stdin.next();}
		}
		allies = RandomPlayer.generateAllies(userInt,level);

		int enemyCount = -1;
		while(enemyCount < 0){
			System.out.println("How many enemies can you handle?");
			try {
				enemyCount = stdin.nextInt();
			} catch (InputMismatchException e) {stdin.next();}
		}

		int enemyLevel = 0;
		while(enemyLevel > 3 || enemyLevel < 1) {
			System.out.println("How strong will your enemies be (1-3)?");
			try {
				enemyLevel = stdin.nextInt();
			} catch (InputMismatchException e) {
				stdin.next();
			}
		}

		enemies = RandomEnemy.generateEnemies(enemyCount,enemyLevel);

	}

	static private void battleLoop(){
		boolean skipTurn = false;
		//numbers for the actions. if the action isn't usable, it's not shown.
		int special = -3, magic = -4, guard = -5;
		if(!player.specialAttacksEmpty()) special = 2;
		if(!player.magicAttacksEmpty()) magic = Math.max(2,special+1);
		guard = Math.max(Math.max(2, special+1), magic+1);
		while(!fin){
			while (player.getHP() > 0 && enemies.size() != 0) {
				System.out.print("HP: " + player.getHP() + "/" + player.getMaxHP());
				System.out.println(" MP: " + player.getMP() + "/" + player.getMaxMP());
				System.out.print("1: Attack");
				if(special > 0) System.out.print(" " + special + ": Special");
				if(magic > 0) System.out.print(" " + magic + ": Magic");
				System.out.println(" " + guard + ": Guard -1: Quit 0: Options");
				try { //not a number
					userInt = stdin.nextInt();
				} catch (InputMismatchException e){stdin.next(); userInt = 123;}
				if(userInt == 1){ //attack
					player.defaultCurrentAttack();
					player.useAction(player.chooseTarget(enemies), "Attack");
				} else if(userInt == special && special > 0){ //special
					if(player.specialAttacksEmpty()) skipTurn = true;
					else handleMenu(player.getSpecialAttacksArray());
				} else if(userInt == magic && magic > 0){ //magic
					if(player.magicAttacksEmpty()) skipTurn = true;
					else handleMenu(player.getMagicAttacksArray());
				} else if(userInt == guard){ //guard
					player.useAction(player, "Guard");
				} else if(userInt == 0){ //options
					skipTurn = true;
					changeTextSpeed();
				} else if(userInt == -1){ //quit
					System.out.print(player.getName() + " fled the scene!\nThe battle is over!");
					System.exit(1);
				} else{ //invalid input
					System.out.println(player.getName() + " fumbled and pressed an invalid number!\n"); Attack.sleep();
				}

				if(!skipTurn) {
					int pos;
					player.endTurn();
					for(Player ally: allies) {
						if(enemies.size() != 0) {
							pos = Battler.randomEnemyPosition(enemies);
							ally.randomAttackPattern(enemies.get(pos));
							if(enemies.get(pos).getHP() <= 0)
								enemies.remove(pos);
						}
						ally.endTurn();
					}
					for(Enemy enemy: enemies) {
						if(player.getHP() <= 0)
							break;
						pos = Battler.randomPlayerPosition(allies);
						if(pos == -1) {
							enemy.randomAttackPattern(player);
						}else {
							enemy.randomAttackPattern(allies.get(pos));
							if (allies.get(pos).getHP() <= 0)
								allies.remove(pos);
						}
						enemy.endTurn();
					}
				}
				else skipTurn = false;
			}
			fin = Finish();
		}
	}

	private static void handleMenu(Attack[] attacks){
		player.setCurrentAttack(player.attackMenu(attacks));
		if(player.usedDefaultAttack()){
			player.useAction(player.chooseTarget(enemies), "Cower");
		}
		else{
			player.useAction(player.chooseTarget(enemies), "Attack");
		}
		player.defaultCurrentAttack();
	}

	private static boolean Finish() {
		Scanner sc = new Scanner(System.in);
		if(player.getHP() <= 0) {
			System.out.println("You have been defeated D:");
		}else{
			System.out.println("You have vanquished your foe(s) :D\nCongratulations!");
			System.out.println("You have acquired " + 5 + " Xp.");
			player.gainXP(5);
		}
		Attack.sleep();

		while(true) {
			System.out.println("Do you want to play again? Enter \"yes\" or \"no\".");
			String response = sc.nextLine();
			if (response.equals("yes")) {
				System.out.println("NEW ROUND!!!\n");
				setDifficulty();
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
