package RandomGeneration;
import Attacks.*;
import Battlers.*;

import java.util.ArrayList;

public class RandomEnemy {
    public static Goblin randomGoblin(int level, String name){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 400 * level * Rates.rand(25,30); //having these set as 0 can make them immediately die
        mp = 80 * level * Rates.rand(25,30);
        atk = 50 * level * Rates.rand(25,30);
        def = 40 * level * Rates.rand(25,30);
        Matk = 70 * level * Rates.rand(25,30);
        Mdef = 80 * level * Rates.rand(25,30);
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
        hp = 1000 * level * Rates.rand(25,30);
        mp = 70 * level * Rates.rand(25,30);
        atk = 90 * level * Rates.rand(25,30);
        def = 90 * level * Rates.rand(25,30);
        Matk = 80 * level * Rates.rand(25,30);
        Mdef = 60* level * Rates.rand(25,30);
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
                goblinLord.addMagicAttack(new Plague());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }
        return goblinLord;
    }

    public static ArrayList<Enemy> generateEnemies(int enemyCount, int level){
        ArrayList<Enemy> enemies = new ArrayList<>();
        int numGoblins = 0; int numLords = 0;
        String enemyName;
        for(int i = 0; i < enemyCount; i++){
            if(Rates.percentRateApplied(20)){ //Make goblin lord's 20% chance to appear
                enemyName = String.format("Goblin Lord #%d",++numLords);
                enemies.add(randomGoblinLord(level,enemyName));
            } else {
                enemyName = String.format("Goblin #%d",++numGoblins);
                enemies.add(randomGoblin(level, enemyName));
            }
        }
        System.out.printf("Your enemies include %d normal goblin(s) and %d mighty Goblin Lord(s)!\n",numGoblins,numLords);
        return enemies;
    }
}
