package main.java.Attacks;

import main.java.Attacks.Attack;
import main.java.Battler;

public class Pierce extends Attack {

    /** Default Pierce values **/
    public Pierce(){
        super();
        setMpCost(15);
        setSkillName("Pierce");
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getAtk());
    }
    public void addEffects(Battler user, Battler target){
        // does nothing
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " pierces through " + target.getName() + "'s defense!";
    }

}
