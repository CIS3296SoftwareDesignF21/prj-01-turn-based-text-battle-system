package Attacks;
import Battlers.*;

public class DefaultAttack extends Attack {

    /** DefaultAttack values **/
    public DefaultAttack(){
        super();
        setSkillName("Attack");
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getAtk() * 2 - target.getDef());
    }
    public void addEffects(Battler user, Battler target){
        // does nothing
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " attacks!";
    }

}
