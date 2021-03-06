package RandomGeneration;
import Attacks.*;
import Battlers.*;

import java.util.ArrayList;

public class RandomPlayer {
    /* Randomly Generate stats and add skills based on level */
    public static Mage randomMage(int level, String name){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 400 * level * Rates.rand(25,30);
        mp = 80 * level * Rates.rand(25,30);
        atk = 50 * level * Rates.rand(25,30);
        def = 40 * level * Rates.rand(25,30);
        Matk = 70 * level * Rates.rand(25,30);
        Mdef = 80 * level * Rates.rand(25,30);
        Mage mage = new Mage(name,hp,mp,atk,def,Matk,Mdef);
        mage.addMagicAttack(new Peer());
        switch(level){
            case 1:
                break;
            case 2:
                mage.addMagicAttack(new DefenseDown());
                mage.addMagicAttack(new DefenseUp());
                mage.addMagicAttack(new AttackDown());
                mage.addMagicAttack(new AttackUp());
                mage.addSpecialAttack(new DesperateHit());
                mage.addMagicAttack(new Holy());
                mage.addMagicAttack(new Fire());
                mage.addMagicAttack(new Freeze());
                break;
            case 3:
                mage.addMagicAttack(new DefenseDown());
                mage.addMagicAttack(new DefenseUp());
                mage.addMagicAttack(new AttackDown());
                mage.addMagicAttack(new AttackUp());
                mage.addMagicAttack(new Buff());
                mage.addSpecialAttack(new DesperateHit());
                mage.addMagicAttack(new Holy());
                mage.addMagicAttack(new Fire());
                mage.addMagicAttack(new Freeze());
                mage.addMagicAttack(new Plague());
                mage.addMagicAttack(new Shock());
                mage.addMagicAttack(new Heal());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }
        return mage;
    }
    /* Randomly Generate stats and add skills based on level */
    public static Fighter randomFighter(int level, String name){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 700 * level * Rates.rand(25,30);
        mp = 40 * level * Rates.rand(25,30);
        atk = 70 * level * Rates.rand(25,30);
        def = 80 * level * Rates.rand(25,30);
        Matk = 40 * level * Rates.rand(25,30);
        Mdef = 40* level * Rates.rand(25,30);
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
                fighter.addSpecialAttack(new ShieldCrash());
                fighter.addSpecialAttack(new Pierce());
                //debug
                /*fighter.addSpecialAttack(new AttackDown());
                fighter.addSpecialAttack(new AttackUp());
                fighter.addSpecialAttack(new Buff());
                fighter.addSpecialAttack(new DefenseDown());
                fighter.addSpecialAttack(new DefenseUp());
                fighter.addSpecialAttack(new Fire());
                fighter.addSpecialAttack(new Freeze());
                fighter.addSpecialAttack(new Heal());
                fighter.addSpecialAttack(new Holy());
                fighter.addSpecialAttack(new InstantKill());
                fighter.addSpecialAttack(new Peer());
                fighter.addSpecialAttack(new Plague());
                fighter.addSpecialAttack(new Shock());*/
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }
        return fighter;
    }
    /* Randomly Generate every ally, with a set chance of being fighter or mage */
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
        System.out.printf("Your party includes %d friendly mage(s) and %d friendly fighter(s)\n",numMages,numFighters);
        return allies;
    }
}
