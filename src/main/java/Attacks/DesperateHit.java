package Attacks;

import Battlers.Battler;

public class DesperateHit extends Attack {

    /** Default Desperate Hit values **/
    public DesperateHit(){
        super();
        setMpCost(30);
        setSkillName("Desperate Hit");
        setCritRate(100); //guaranteed critical hit
        setHitRate(33); //low hit rate (33%)
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, user.getAtk() * 2 - target.getDef());
    }
    public void addEffects(Battler user, Battler target){
        // does nothing
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " hits " + target.getName() + " with a desperate attack!";
    }

}
