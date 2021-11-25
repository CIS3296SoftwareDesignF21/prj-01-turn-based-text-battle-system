package Attacks;

import Battlers.Battler;

import static Battlers.Battler.HOLY;

public class Holy extends Attack {

    /** Holy values **/
    public Holy(){
        super();
        setMpCost(15);
        setSkillName("Holy");
        setElement(HOLY);
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
        return user.getName() + " casts Holy!";
    }

}
