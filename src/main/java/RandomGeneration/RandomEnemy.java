package RandomGeneration;
import Attacks.*;
import Battlers.*;

import java.util.ArrayList;

public class RandomEnemy {
    public static Goblin randomGoblin(int level, String name){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 400 * level * Rates.rand(0,30);
        mp = 80 * level * Rates.rand(0,30);
        atk = 50 * level * Rates.rand(0,30);
        def = 40 * level * Rates.rand(0,30);
        Matk = 70 * level * Rates.rand(0,30);
        Mdef = 80* level * Rates.rand(0,30);
        Goblin goblin = new Goblin(name,hp,mp,atk,def,Matk,Mdef);
        goblin.addSpecialAttack(new DesperateHit());
        switch(level){
            case 1:
                break;
            case 2:
                goblin.addSpecialAttack(new Bash());
                break;
            case 3:
                goblin.addSpecialAttack(new Bash());
                goblin.addSpecialAttack(new Pierce());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }
        return goblin;
    }

    public static GoblinLord randomGoblinLord(int level, String name){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 700 * level * Rates.rand(0,30);
        mp = 40 * level * Rates.rand(0,30);
        atk = 70 * level * Rates.rand(0,30);
        def = 80 * level * Rates.rand(0,30);
        Matk = 40 * level * Rates.rand(0,30);
        Mdef = 40* level * Rates.rand(0,30);
        GoblinLord goblinLord = new GoblinLord(name,hp,mp,atk,def,Matk,Mdef);
        goblinLord.addSpecialAttack(new DesperateHit());
        goblinLord.addSpecialAttack(new Bash());
        switch(level){
            case 1:
                break;
            case 2:
                goblinLord.addSpecialAttack(new VariantStrike());
                goblinLord.addSpecialAttack(new Pierce());
                break;
            case 3:
                goblinLord.addSpecialAttack(new VariantStrike());
                goblinLord.addSpecialAttack(new Pierce());
                goblinLord.addMagicAttack(new Heal());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }
        return goblinLord;
    }


    //Make goblin lord's 20% chance to appear
    public static ArrayList<Enemy> generateEnemies(int enemyCount, int level){
        ArrayList<Enemy> enemies = new ArrayList<>();
        int numGoblins= 0; int numLords = 0;
        String enemyName;
        for(int i = 0; i < enemyCount; i++){
            int enemy = Rates.rand(1,5);
            if(enemy < 5){
                enemyName = String.format("Enemy Goblin #%d",++numGoblins);
                enemies.add(randomGoblin(level, enemyName));
            } else {
                enemyName = String.format("Enemy Goblin Lord #%d",++numLords);
                enemies.add(randomGoblinLord(level,enemyName));
            }
        }
        System.out.printf("Your enemies include %d normal goblins and %d mighty Goblin Lords!\n",numGoblins,numLords);
        return enemies;
    }
}
