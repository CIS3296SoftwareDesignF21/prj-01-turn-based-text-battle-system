package RandomGeneration;
import Attacks.*;
import Battlers.*;

import java.util.ArrayList;

public class RandomPlayer {


    public static Mage randomMage(int level, String name){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 400 * level * Rates.rand(1,30);
        mp = 80 * level * Rates.rand(1,30);
        atk = 50 * level * Rates.rand(1,30);
        def = 40 * level * Rates.rand(1,30);
        Matk = 70 * level * Rates.rand(1,30);
        Mdef = 80 * level * Rates.rand(1,30);
        Mage mage = new Mage(name,hp,mp,atk,def,Matk,Mdef);
        mage.addMagicAttack(new Peer());
        switch(level){
            case 1:
                break;
            case 2:
                mage.addSpecialAttack(new DefenseDown());
                break;
            case 3:
                mage.addSpecialAttack(new DefenseDown());
                mage.addSpecialAttack(new Heal());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }
        return mage;
    }

    public static Fighter randomFighter(int level, String name){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 700 * level * Rates.rand(1,30);
        mp = 40 * level * Rates.rand(1,30);
        atk = 70 * level * Rates.rand(1,30);
        def = 80 * level * Rates.rand(1,30);
        Matk = 40 * level * Rates.rand(1,30);
        Mdef = 40* level * Rates.rand(1,30);
        Fighter fighter = new Fighter(name,hp,mp,atk,def,Matk,Mdef);
        fighter.addSpecialAttack(new VariantStrike());
        fighter.addSpecialAttack(new DesperateHit());
        switch(level){
            case 1:
                break;
            case 2:
                fighter.addSpecialAttack(new DualSlash());
                fighter.addSpecialAttack(new Bash());
                break;
            case 3:
                fighter.addSpecialAttack(new DualSlash());
                fighter.addSpecialAttack(new Bash());
                fighter.addSpecialAttack(new DebilitatingSlash());
                fighter.addSpecialAttack(new Pierce());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }
        return fighter;
    }

    public static ArrayList<Player> generateAllies(int allyCount, int level){
        ArrayList<Player> allies = new ArrayList<>();
        int numMages = 0; int numFighters = 0;
        String allyName;
        for(int i = 0; i < allyCount; i++){
             if(Rates.percentRateApplied(50)){ //50% chance
                 allyName = String.format("Allied Fighter #%d",++numFighters);
                 allies.add(randomFighter(level, allyName));
             } else {
                 allyName = String.format("Allied Mage #%d",++numMages);
                 allies.add(randomMage(level,allyName));
             }
        }
        System.out.printf("Your party includes %d friendly mages and %d friendly fighters\n",numMages,numFighters);
        return allies;
    }
}
