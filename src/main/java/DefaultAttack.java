package main.java;
public class DefaultAttack extends Attack{

    private final int mpCost = 0;
    private final int numOfHits = 1;
    private final String skillName = "Attack";

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
