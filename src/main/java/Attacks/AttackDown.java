package Attacks;

import Battlers.Battler;

public class AttackDown extends Attack {

    /** AttackDown values **/
    public AttackDown(){
        super();
        setMpCost(10);
        setSkillName("Attack Down");
        setAttackType(EFFECTS);
        setCritAbility(false);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){return 0;}
    public boolean addEffects(Battler user, Battler target){
        target.debuff("atk", 1);
        return true;
    }
    public String getMessage(Battler user, Battler target){return user.getName() + " casts Attack Down!";
    }

}
