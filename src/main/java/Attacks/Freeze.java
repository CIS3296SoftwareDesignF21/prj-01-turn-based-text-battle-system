package Attacks;

import Battlers.Battler;

import static Battlers.Battler.ICE;

public class Freeze extends Attack {

    /** Freeze values **/
    public Freeze(){
        super();
        setMpCost(15);
        setSkillName("Freeze");
        setElement(ICE);
        setCritAbility(false);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getMAtk() * 3 - target.getMDef());
    }
    public boolean addEffects(Battler user, Battler target){
        return false;
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " casts Freeze!";
    }

}
