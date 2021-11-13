package main.java;

import java.io.*;
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

		Character player = null;
		System.out.println("How powerful is your character (1 - 3):");
		userInt = stdin.nextInt();

		switch(userInt){
			case 1:
				player = Character.randomCharacter(1,charName);
				break;
			case 2:
				player = Character.randomCharacter(2,charName);
				break;
			case 3:
				player = Character.randomCharacter(3,charName);
				break;
			default:
				System.out.println("Character level outside of range");
				System.exit(-1);
				break;
		}

		System.out.println("How strong of an enemy can you manage (1 - 3):");
		userInt = stdin.nextInt();
		Enemy enemy = null;
		switch (userInt){
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

//		Battler player = null;
//
//		System.out.println("Enter 1 to be Hero. Enter 2 to be villain. Enter -1 to quit");
//		userInt = stdin.nextInt();
//
//		switch(userInt) {
//
//			case -1:
//				System.out.println("You quit");
//				System.exit(0);
//				break;
//
//			case 1:
//				//Character(String name, int HP,int MaxHP, int MP, int MaxMP, int Atk, int Def)
//				player = new Character(charName,1500,35,45,55);
//				System.out.println("You chose to be a hero " + player.getName());
//				break;
//
//			case 2:
//				//Enemy(String name, int HP, int MP, int Atk, int Def)
//				player = new Enemy(charName,750,50,100,30);
//				System.out.println("You chose to be a villain " + player.getName());
//				break;
//			default:
//				System.out.println("Invalid entry.");
//				System.exit(0);
//				break;
//		}


		
		int playerDamage, enemyDamage;
		DefaultAttack attack = new DefaultAttack();
		while(player.getHP() > 0  && enemy.getHP() > 0){
			playerDamage = attack.calcDamage(player,enemy);
			enemyDamage = attack.calcDamage(enemy,player);
			System.out.println("" + attack.getMessage(player,enemy));
			System.out.printf("%s lost %d hp\n",enemy.getName(),playerDamage);
			enemy.setHP(enemy.getHP() - playerDamage);
			System.out.printf("The enemy has %d health remaining\n\n",enemy.getHP());
			if(enemy.getHP() > 0) {
				System.out.println("" + attack.getMessage(enemy, player));
				System.out.printf("%s lost %d hp\n", player.getName(), enemyDamage);
				player.setHP(player.getHP() - enemyDamage);
				System.out.printf("%s has %d health remaining\n\n",player.getName(),player.getHP());
			}
		}

		if(player.getHP() < 0){
			System.out.println("You have been defeated D:\n Please try again\n");
		} else {
			System.out.println("You have vanquished your foe :D\n Congratulations!\n");
		}




	}

}

