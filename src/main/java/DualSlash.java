package main.java;
public class DualSlash extends Attack{

    private final int mpCost = 10;
    private final int numOfHits = 2;
    private final String skillName = "Dual Slash";

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
