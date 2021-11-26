package Attacks;
import Battlers.*;

public class Heal extends Attack {

    /** Heal values **/
    public Heal(){
        super();
        setMpCost(10);
        setSkillName("Heal");
        setCritAbility(false);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.min(0, target.getMaxHP() / -3);
    }
    public boolean addEffects(Battler user, Battler target){
        return false;
    }
    public String getMessage(Battler user, Battler target){
        return target.getName() + " casts Heal!";
    }

}
