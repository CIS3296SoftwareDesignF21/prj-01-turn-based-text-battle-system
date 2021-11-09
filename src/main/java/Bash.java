package main.java;
public class Bash extends Attack{

    private final int mpCost = 20;
    private final int numOfHits = 1;
    private final String skillName = "Bash";

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
