import Attacks.*;
import Battlers.*;
import RandomGeneration.RandomEnemy;
import RandomGeneration.RandomPlayer;

import java.io.IOException;
import java.util.*;

public class main {
	static Scanner stdin = new Scanner(System.in);
	//static String userInput; // Hold user's String inputs
	static int userInt; // Hold user's integers
	static String charName;
	static boolean fin = false;
	static Player player = null;
	static ArrayList<Player> allies = null;
	static ArrayList<Enemy> enemies = null;
	static int le = 1;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		System.out.println("Hello and welcome to the battle simulator!");
		System.out.println("Enter your name:");
		charName = stdin.nextLine(); //take user input

		setDifficulty(); //uses user input to allow player to choose difficulty level of themself, their allies, and their enemies 
		battleLoop(); //main battle loop 
	}

	/* Set difficulty and other gameplay parameters */
	private static void setDifficulty(){
		int classChoice = 0;
		while(classChoice > 2 || classChoice < 1) { //choose class
			System.out.println("Would you like to play as a Fighter or a Mage?\n" +
					"1: Fighter " + "2: Mage");
			try {
				classChoice = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}

		int level = 0;
		while(level > 3 || level < 1) { //choose level (adds certain skills, increases stats)
			System.out.println("Select your power level (1-3):");
			try {
				level = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}

		if(classChoice == 1){ //instantiate a RandomPlayer object with user input 
			player = RandomPlayer.randomFighter(level,charName); 
		} else {
			player = RandomPlayer.randomMage(level,charName);
		}

		int enemyLevel = 0;
		//choose enemy level (adds certain skills, increases stats)
		while(enemyLevel > 3 || enemyLevel < 1) { //instantiate an RandomEnemy object with user input 
			System.out.println("Select your enemies' power level (1-3):");
			try {
				enemyLevel = stdin.nextInt();
			} catch (InputMismatchException e) {stdin.next();}
		}

		userInt = -1;
		while(userInt < 0) { //choose amount of allies (nonplayable character that attack enemy)
			System.out.println("How many allies do you want?");
			try {
				userInt = stdin.nextInt();
			} catch (InputMismatchException e) {stdin.next();}
		}
		allies = RandomPlayer.generateAllies(userInt,level); //generate allies according to user input

		int enemyCount = -1;
		while(enemyCount < 0){ //choose amount of enemies (nonplayable character that you & allies attack)
			System.out.println("How many enemies can you handle?");
			try {
				enemyCount = stdin.nextInt();
			} catch (InputMismatchException e) {stdin.next();}
		}

		enemies = RandomEnemy.generateEnemies(enemyCount,enemyLevel); //generate enemies according to user input 
		System.out.println();
	}

	/* Main battle program. battle commences here */
	static private void battleLoop() throws ClassNotFoundException, IOException {
		Attack[] specialAttacks = player.getSpecialAttacksArray();
		Attack[] magicAttacks = player.getMagicAttacksArray();
		boolean skipTurn = false;
		//numbers for the actions. if the action isn't usable, it's not shown.
		int special = -3, magic = -4, guard = -5;
		if(!player.specialAttacksEmpty()) special = 2;
		if(!player.magicAttacksEmpty()) magic = Math.max(2,special+1);
		guard = Math.max(Math.max(2, special+1), magic+1);
		while(!fin){
			while (player.getHP() > 0 && enemies.size() != 0) { //while player and all enemies have hp
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
					useAttackExtended();
					//player.useAction(player.chooseTarget(enemies), "Attack");
				} else if(userInt == special && special > 0){ //special
					handleMenu(specialAttacks);
				} else if(userInt == magic && magic > 0){ //magic
					handleMenu(magicAttacks);
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
					//Since we removed the removeDeadNpcs function so we didn't have to loop through
					//The enemies, we don't need this anymore
					//However, this could also be more concise for if we implement attacks that could
					//Damage multiple enemies in the same turn, theoretically killing multiple of them

					//enemies.removeIf(enemy -> enemy.getHP() <= 0);
					for(Player ally: allies) { //allies attack
						if(player.getHP() <= 0) break;
						if(enemies.size() != 0) {
							pos = Battler.randomEnemyPosition(enemies);
							ally.randomAttackPattern(enemies.get(pos));
							if(enemies.get(pos).getHP() <= 0) //if dead
								enemies.remove(pos);
						}
					}
					for(Enemy enemy: enemies) { //enemies attack
						if(player.getHP() <= 0) break;
						pos = Battler.randomPlayerPosition(allies);
						if(pos == -1) {
							enemy.randomAttackPattern(player);
						}else {
							enemy.randomAttackPattern(allies.get(pos));
							if (allies.get(pos).getHP() <= 0) //if dead
								allies.remove(pos);
						}
					}
					//end turn
					for(Player ally: allies){
						ally.endTurn();
					}
					for(Enemy enemy: enemies){
						enemy.endTurn();
					}
					player.endTurn();
				}
				else skipTurn = false;
			}
			fin = finishBattle();
		}
	}

	/* Attack handling. deals with selecting target & removing enemy */
	private static void useAttackExtended(){
		int targetPos = player.chooseTargetPosition(enemies);
		if(targetPos == -1){ //target is user
			player.useAction(player, "Attack");
		}else if(targetPos < -1){ //target is ???
			player.useAction(null, "Attack");
		}else{ //target is enemy
			player.useAction(enemies.get(targetPos), "Attack");
			if(enemies.get(targetPos).getHP() <= 0) //if dead
				enemies.remove(targetPos);
		}
	}
	/* Menu handling. deals with selecting from a menu */
	private static void handleMenu(Attack[] attacks){
		player.setCurrentAttack(player.attackMenu(attacks));
		if(player.usedDefaultAttack()){
			player.useAction(player, "Cower");
		}else{
			useAttackExtended();
		}
		player.defaultCurrentAttack();
	}
	/* Handles the end of the program/battle */
	private static boolean finishBattle() throws ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		//check battle result
		if(player.getHP() <= 0) { //lost
			System.out.println("You have been defeated D:");
		}else{ //won
			System.out.println("You have vanquished your foe(s) :D\nCongratulations!");
			//level up
			player.loadXpPerLevel();
			player.setLevel(le);
			le++;
			player.gainXP(le);
		}
		Attack.sleep();

		while(true) { //ask to play again
			System.out.println("Do you want to play again? Enter \"yes\" or \"no\".");
			String response = sc.nextLine();
			if (response.equals("yes")) {
				System.out.println("NEW ROUND!!!\n");
				//reset parameters
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
	/* Change speed of text. called within Options*/
	public static void changeTextSpeed(){
		userInt = 0;
		while(userInt > 5 || userInt < 1) {
			System.out.println("Enter Text Speed (1-5):");
			try{
				userInt = stdin.nextInt();
			} catch (InputMismatchException e){stdin.next();}
		}
		Attack.changeSleepTime(userInt); //change text speed based on input
	}
}
