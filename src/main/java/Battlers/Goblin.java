package Battlers;
import Attacks.*;

public class Goblin extends Enemy{

    public Goblin(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
    }

    public static Goblin randomGoblin(int level, String name){
        // Order of stats is hp, mp, atk, def
        int [] stats = Player.returnRandomStats(level);
        Goblin newGoblin = new Goblin(name, stats[0], stats[1], stats[2], stats[3]);
        newGoblin.addSpecialAttack(new DesperateHit());
        switch(level){
            case 1:
                break;
            case 2:
                newGoblin.addSpecialAttack(new Bash());
                break;
            case 3:
                newGoblin.addSpecialAttack(new Bash());
                newGoblin.addSpecialAttack(new Pierce());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }

        return newGoblin;
    }
}
