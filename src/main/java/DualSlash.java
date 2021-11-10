package main.java;
public class DualSlash extends Attack{

    /** Default DualSlash values **/
    DualSlash(){
        super();
        setMpCost(10);
        setNumOfHits(2);
        setSkillName("Dual Slash");
    }

    /** Attack methods **/
    int calcDamage(Battler user, Battler target){
        return user.getAtk() * 2 - target.getDef();
    }
    void addEffects(Battler user, Battler target){
        // does nothing
    }
    String getMessage(Battler user, Battler target){
        return user.getName() + " attacks twice!";
    }

}
