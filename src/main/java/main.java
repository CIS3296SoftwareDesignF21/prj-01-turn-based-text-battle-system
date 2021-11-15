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

		Battler player = null;

		System.out.println("Enter 1 to be Hero. Enter 2 to be villain. Enter -1 to quit");
		userInt = stdin.nextInt();

		switch(userInt) {

			case -1:
				System.out.println("You quit");
				System.exit(0);
				break;

			case 1:
				//Character(String name, int HP,int MaxHP, int MP, int MaxMP, int Atk, int Def)
				player = new Player(charName,1500,35,45,55);
				System.out.println("You chose to be a hero " + player.getName());
				break;

			case 2:
				//Enemy(String name, int HP, int MP, int Atk, int Def)
				player = new Enemy(charName,1000,50,150,150);
				System.out.println("You chose to be a villain " + player.getName());
				break;
			default:
				System.out.println("Invalid entry.");
				System.exit(0);
				break;
		}

		Battler enemy = new Enemy("Evil Dude", ThreadLocalRandom.current().nextInt(1000,2000),
				ThreadLocalRandom.current().nextInt(1,101),
				ThreadLocalRandom.current().nextInt(30,50),ThreadLocalRandom.current().nextInt(50,60));
		int playerDamage, enemyDamage;
		DefaultAttack attack = new DefaultAttack();
		while(player.getHP() > 0  && enemy.getHP() > 0){
//			playerDamage = attack.calcDamage(player,enemy);
//			enemyDamage = attack.calcDamage(enemy,player);
//			System.out.println("" + attack.getMessage(player,enemy));
//			System.out.printf("%s lost %d hp\n",enemy.getName(),playerDamage);
//			enemy.setHP(enemy.getHP() - playerDamage);
//			System.out.printf("The enemy has %d health remaining\n\n",enemy.getHP());
//			if(enemy.getHP() > 0) {
//				System.out.println("" + attack.getMessage(enemy, player));
//				System.out.printf("%s lost %d hp\n", player.getName(), enemyDamage);
//				player.setHP(player.getHP() - enemyDamage);
//				System.out.printf("%s have %d health remaining\n\n",player.getName(),player.getHP());
			//}
		}

		if(player.getHP() < 0){
			System.out.println("You have been defeated D:\n Please try again\n");
		} else {
			System.out.println("You have vanquished your foe :D\n Congratulations!\n");
		}




	}

}

