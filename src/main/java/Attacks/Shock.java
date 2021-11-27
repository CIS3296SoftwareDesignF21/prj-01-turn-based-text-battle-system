package Attacks;

import Battlers.Battler;

import static Battlers.Battler.LIGHTNING;

public class Shock extends Attack {

    /** Shock values **/
    public Shock(){
        super();
        setMpCost(15);
        setSkillName("Shock");
        setElement(LIGHTNING);
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
        return user.getName() + " casts Shock!";
    }

}
