package Attacks;
import Battlers.*;

public class Bash extends Attack {

    /** Default Bash values **/
    public Bash(){
        super();
        setMpCost(20);
        setSkillName("Bash");
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getAtk() * 4 - target.getDef() * 2);
    }
    public void addEffects(Battler user, Battler target){
        // does nothing
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " bashes into " + target.getName() + "!";
    }

}
