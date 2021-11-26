package Battlers;

import Attacks.*;


public class Mage extends Player{
//    public int[] levelStats(int level){
//        int hpModifier, mpModifier, atkModifier, defModifier;
//        hpModifier = 40 * level;
//        mpModifier = 45 * level;
//        atkModifier = 10 * level;
//        defModifier = 5 * level;
//        int [] stats = {Rates.rand(400,500),Rates.rand(100,200), Rates.rand(30,40), Rates.rand(40,50)};
//
//    }

//    public Mage(){
//        super();
//    }


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
