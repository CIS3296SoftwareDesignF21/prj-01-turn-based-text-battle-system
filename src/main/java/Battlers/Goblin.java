package Battlers;
import Attacks.*;

public class Goblin extends Enemy{

    public Goblin(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
    }

    public Goblin(String name,int hp,int mp,int atk,int def,int Matk,int Mdef){
        super(name,hp,mp,atk,def,Matk,Mdef);
    }



}
