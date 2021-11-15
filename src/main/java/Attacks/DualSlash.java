package Attacks;
import Battlers.*;


public class DualSlash extends Attack {

    /** Default DualSlash values **/
    public DualSlash(){
        super();
        setMpCost(10);
        setNumOfHits(2);
        setSkillName("Dual Slash");
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getAtk() * 2 - target.getDef());
    }
    public void addEffects(Battler user, Battler target){
        // does nothing
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " attacks twice!";
    }

}
