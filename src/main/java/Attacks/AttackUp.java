package Attacks;

import Battlers.Battler;

public class AttackUp extends Attack {

    /** AttackUp values **/
    public AttackUp(){
        super();
        setMpCost(10);
        setSkillName("Defense Up");
        setAttackType(EFFECTS);
        setCritAbility(false);
        setTargetUser(true);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){return 0;}
    public boolean addEffects(Battler user, Battler target){
        target.buff("atk", 1);
        return true;
    }
    public String getMessage(Battler user, Battler target){return user.getName() + " casts Attack Up!";
    }

}
