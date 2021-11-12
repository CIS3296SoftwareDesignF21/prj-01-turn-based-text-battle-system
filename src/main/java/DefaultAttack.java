package main.java;
public class DefaultAttack extends Attack{

    /** DefaultAttack values **/
    DefaultAttack(){
        super();
        setSkillName("Attack");
    }

    /** Attack methods **/
    int calcDamage(Battler user, Battler target){
        return user.getAtk() * 2 - target.getDef();
    }
    void addEffects(Battler user, Battler target){
        // does nothing
    }
    String getMessage(Battler user, Battler target){
        return user.getName() + " attacks!";
    }

}
