package Attacks;

import Battlers.Battler;

import static Battlers.Battler.FIRE;

public class Fire extends Attack {

    /** DefaultAttack values **/
    public Fire(){
        super();
        setMpCost(15);
        setSkillName("Fire");
        setElement(FIRE);
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
        return user.getName() + " casts Fire!";
    }

}
