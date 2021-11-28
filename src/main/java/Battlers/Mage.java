package Battlers;

import Attacks.*;


public class Mage extends Player{

    public Mage(String name, int MaxHP, int MaxMP, int Atk, int Def, int MAtk, int MDef){
        super(name, MaxHP, MaxMP, Atk, Def,MAtk,MDef);
    }

    public Mage(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
    }



}
