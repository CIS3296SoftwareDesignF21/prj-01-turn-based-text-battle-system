package Battlers;
import Attacks.*;

public class GoblinLord extends Enemy{

    public void addResistances(){
        setResistance(FIRE, BLOCK);
        setResistance(ICE, STANDARD);
        setResistance(LIGHTNING, RESIST);
        setResistance(HOLY, WEAK);
        setResistance(DARK, ABSORB);
        setResistance(PHYSICAL, RESIST);
    }

    public GoblinLord(String name, int MaxHP, int MaxMP, int Atk, int Def, int Agility){
        super(name, MaxHP, MaxMP, Atk, Def, Agility);
        addResistances();
    }

    public GoblinLord(String name,int hp,int mp,int atk,int def,int Matk,int Mdef, int Agility){
        super(name,hp,mp,atk,def,Matk,Mdef, Agility);
        addResistances();
    }

}
