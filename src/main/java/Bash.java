package main.java;
public class Bash extends Attack{

    /** Default Bash values **/
    Bash(){
        super();
        setMpCost(20);
        setSkillName("Bash");
    }

    /** Attack methods **/
    int calcDamage(Battler user, Battler target){
        return user.getAtk() * 4 - target.getDef() * 2;
    }
    void addEffects(Battler user, Battler target){
        // does nothing
    }
    String getMessage(Battler user, Battler target){
        return user.getName() + " bashes into " + target.getName() + "!";
    }

}
