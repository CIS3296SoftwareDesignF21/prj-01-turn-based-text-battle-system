package Battlers;
import Attacks.*;
import RandomGeneration.Rates;

import java.util.*;

public class Goblin extends Enemy{

    //Attack names and their relative probabilities

//    private  Map<String,Integer> attackProbabilities;
//
//    private void setAttackProbabilities(){
//        attackProbabilities = new HashMap<>(){
//            {
//                put("Desperate Hit",34);
//                put("Bash",33);
//                put("Pierce",33);
//            }
//        };
//    }

    public void addResistances(){
        setResistance(FIRE, RESIST);
        setResistance(ICE, STANDARD);
        setResistance(LIGHTNING, STANDARD);
        setResistance(HOLY, WEAK);
        setResistance(DARK, RESIST);
        setResistance(PHYSICAL, STANDARD);
    }

    public Goblin(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
        addResistances();
        //setAttackProbabilities();
    }

    public Goblin(String name,int hp,int mp,int atk,int def,int Matk,int Mdef){
        super(name,hp,mp,atk,def,Matk,Mdef);
        addResistances();
        //setAttackProbabilities();
    }



}
