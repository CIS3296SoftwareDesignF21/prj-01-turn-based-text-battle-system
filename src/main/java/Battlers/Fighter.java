package Battlers;

import Attacks.*;


public class Fighter extends Player{

    public Fighter(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
    }

    public static Fighter randomFighter(int level, String name){
        // Order of stats is hp, mp, atk, def
        int [] stats = Player.returnRandomStats(level);
        Fighter newFighter = new Fighter(name, stats[0], stats[1], stats[2], stats[3]);
        newFighter.addSpecialAttack(new VariantStrike());
        newFighter.addSpecialAttack(new DesperateHit());
        switch(level){
            case 1:
                break;
            case 2:
                newFighter.addSpecialAttack(new DualSlash());
                newFighter.addSpecialAttack(new Bash());
                break;
            case 3:
                newFighter.addSpecialAttack(new DualSlash());
                newFighter.addSpecialAttack(new Bash());
                newFighter.addSpecialAttack(new DebilitatingSlash());
                newFighter.addSpecialAttack(new Pierce());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }

        return newFighter;
    }






}
