package Attacks;
import Battlers.*;

public class DefenseDown extends Attack {

    /** DefenseDown values **/
    public DefenseDown(){
        super();
        setMpCost(10);
        setSkillName("Defense Down");
        setAttackType(EFFECTS);
        setCritAbility(false);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){return 0;}
    public boolean addEffects(Battler user, Battler target){
        target.debuff("def", 1);
        return true;
    }
    public String getMessage(Battler user, Battler target){return user.getName() + " casts Defense Down!";
    }

}
