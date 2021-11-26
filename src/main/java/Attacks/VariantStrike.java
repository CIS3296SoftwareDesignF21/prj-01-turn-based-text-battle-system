package Attacks;

import Battlers.Battler;

public class VariantStrike extends Attack {

    /** Default Variant Strike values **/
    public VariantStrike(){
        super();
        setMpCost(15);
        setSkillName("Variant Strike");
        setVariance(100); //can be anywhere between 0 and double damage
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getAtk() * 2 - target.getDef());
    }
    public boolean addEffects(Battler user, Battler target){
        return false;
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " uses Variant Strike!";
    }

}
