package Attacks;
import Battlers.*;

public class BasicAttack extends Attack {

    /** DefaultAttack values **/
    public BasicAttack(){
        super();
        setSkillName("Attack");
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getAtk() * 2 - target.getDef());
    }
    public boolean addEffects(Battler user, Battler target){
        return false;
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " attacks!";
    }

}
