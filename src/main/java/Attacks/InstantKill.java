package Attacks;

import Battlers.Battler;

public class InstantKill extends Attack {

    /** DefaultAttack values **/
    public InstantKill(){
        super();
        setSkillName("Instant Kill");
        setVariance(0);
        setAttackType(GUARANTEED);
        setCritAbility(false);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return Math.max(0, target.getHP());
    }
    public boolean addEffects(Battler user, Battler target){
        return false;
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " kills " + target.getName() + " immediately!";
    }

}
