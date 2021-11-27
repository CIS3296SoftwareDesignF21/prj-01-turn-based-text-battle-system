package Battlers;

import Attacks.*;


public class Mage extends Player{

    public Mage(String name, int MaxHP, int MaxMP, int Atk, int Def, int MAtk, int MDef){
        super(name, MaxHP, MaxMP, Atk, Def);
    }

    public Mage(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
    }



    public static Mage randomMage(int level, String name){
        // Order of stats is hp, mp, atk, def
        int [] stats = Player.returnRandomStats(level);
        Mage newMage = new Mage(name, stats[0], stats[1], stats[2], stats[3]);
        newMage.addSpecialAttack(new Peer());
        switch(level){
            case 1:
                break;
            case 2:
                newMage.addSpecialAttack(new DefenseDown());
                break;
            case 3:
                newMage.addSpecialAttack(new DefenseDown());
                newMage.addSpecialAttack(new Heal());
                break;
            default:
                System.out.println("Level must be between one and three");
                System.exit(-1);
        }

        return newMage;
    }



}
