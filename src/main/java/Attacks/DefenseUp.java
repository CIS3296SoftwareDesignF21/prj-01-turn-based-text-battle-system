package Attacks;

import Battlers.Battler;

public class DefenseUp extends Attack {

    /** DefenseUp values **/
    public DefenseUp(){
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
        target.buff("def", 1);
        return true;
    }
    public String getMessage(Battler user, Battler target){return user.getName() + " casts Defense Up!";
    }

}
