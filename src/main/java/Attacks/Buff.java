package Attacks;

import Battlers.Battler;

public class Buff extends Attack {

    /** Buff values **/
    public Buff(){
        super();
        setMpCost(25);
        setSkillName("Buff");
        setAttackType(EFFECTS);
        setCritAbility(false);
        setTargetUser(true);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){return 0;}
    public boolean addEffects(Battler user, Battler target){
        target.buff("atk", 1);
        target.buff("def", 1);
        return true;
    }
    public String getMessage(Battler user, Battler target){return user.getName() + " casts Buff!";
    }

}
