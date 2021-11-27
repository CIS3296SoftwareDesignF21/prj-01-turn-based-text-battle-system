package Attacks;

import Battlers.Battler;

import static Battlers.Battler.DARK;

public class Plague extends Attack {

    /** Plague values **/
    public Plague(){
        super();
        setMpCost(35);
        setSkillName("Plague");
        setElement(DARK);
        setCritAbility(false);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getMAtk() * 3 - target.getMDef());
    }
    public boolean addEffects(Battler user, Battler target){
        target.debuff("atk", 1);
        target.debuff("def", 1);
        return true;
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " casts Plague!";
    }

}
