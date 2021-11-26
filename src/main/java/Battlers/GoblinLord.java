package Battlers;
import Attacks.*;

public class GoblinLord extends Enemy{

    public GoblinLord(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
    }

    public static GoblinLord randomGoblin(int level, String name){
        // Order of stats is hp, mp, atk, def
        int [] stats = Player.returnRandomStats(level);
        GoblinLord newGoblin = new GoblinLord(name, stats[0], stats[1], stats[2], stats[3]);
        newGoblin.addSpecialAttack(new DesperateHit());
        newGoblin.addSpecialAttack(new Bash());
        switch(level){
            case 1:
                break;
            case 2:
                newGoblin.addSpecialAttack(new VariantStrike());
                newGoblin.addSpecialAttack(new Pierce());
                break;
            case 3:
                newGoblin.addSpecialAttack(new VariantStrike());
                newGoblin.addSpecialAttack(new Pierce());
                newGoblin.addSpecialAttack(new Heal());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }

        return newGoblin;
    }
}
