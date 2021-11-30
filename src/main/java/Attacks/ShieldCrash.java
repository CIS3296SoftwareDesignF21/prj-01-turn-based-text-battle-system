package Attacks;

import Battlers.Battler;

public class ShieldCrash extends Attack {

    /** ShieldSlash values **/
    public ShieldCrash(){
        super();
        setMpCost(35);
        setSkillName("Shield Crash");
        setCritRate(5);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){return Math.max(0, user.getAtk() * 3 - target.getDef());}
    public boolean addEffects(Battler user, Battler target){
        target.debuff("def", 2);
        return true;
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " breaks the enemy's shield into pieces!";
    }

}
