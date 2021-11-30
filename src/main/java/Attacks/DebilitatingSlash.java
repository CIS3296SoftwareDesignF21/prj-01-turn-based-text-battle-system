package Attacks;

import Battlers.Battler;

public class DebilitatingSlash extends Attack {

    /** DebilitatingSlash values **/
    public DebilitatingSlash(){
        super();
        setMpCost(50);
        setSkillName("Debilitating Slash");
        setCritRate(10);
        setNumOfHits(2);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){return Math.max(0, user.getAtk() * 4 - target.getDef() * 2);}
    public boolean addEffects(Battler user, Battler target){
        target.debuff("atk", 1);
        return true;
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " uses Debilitating Slash!";
    }

}
