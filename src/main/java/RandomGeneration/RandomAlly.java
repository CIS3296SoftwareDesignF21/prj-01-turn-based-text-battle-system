package RandomGeneration;
import Attacks.*;
import Battlers.*;

import java.util.ArrayList;

public class RandomAlly {

//    public Mage randomMage(int level, String name){
//        return null;
//    }
//
//    public Mage randomMage(String name){
//        return null;
//    }
//
//    public Mage randomMage(){
//        return null;
//    }
//
//
//    public Fighter randomFighter(int level, String name){
//        return null;
//    }
//
//    public Fighter randomFighter(String name){
//        return null;
//    }

//Instead of recording weights to add to stats, we will just have a static baseline for a class's stats at a level,
    //and make it so that there is a constant variance applied, and that a stat can never go down from it's previous level.
    //Also, this way we can print out the stat increases, and the new skills learned/resistances gained
    //This also allows easier ways to have different classes gain new stats more than others, for example the mage gaining
    //magic defense and mp more than hp or atk.

    public static Mage randomMage(int level, int index){
        int hp, mp, atk, def, Matk, Mdef;
        hp = 400 * level * Rates.rand(0,30);
        mp = 80 * level * Rates.rand(0,30);
        atk = 50 * level * Rates.rand(0,30);
        def = 40 * level * Rates.rand(0,30);
        Matk = 70 * level * Rates.rand(0,30);
        Mdef = 80* level * Rates.rand(0,30);
        String name = String.format("Ally Mage # %d",index);
        Mage randomMage;
        return null;
    }

    public static Fighter randomFighter(int level, int index){
        return null;
    }

    public static ArrayList<Player> generateAllies(int allyCount, int level){
        ArrayList<Player> allies = new ArrayList<>();
        int numMages= 0; int numFighters = 0;
        for(int i = 0; i < allyCount; i++){
             int ally = Rates.rand(1,2);
             if(ally == 1){
                allies.add(randomFighter(level, ++numFighters));
             } else {

                 allies.add(randomMage(level,++numMages));
             }
        }

        return allies;
    }





}
