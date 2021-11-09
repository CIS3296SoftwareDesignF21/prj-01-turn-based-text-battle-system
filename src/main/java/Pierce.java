package main.java;
public class Pierce extends Attack{

    private final int mpCost = 15;
    private final int numOfHits = 1;
    private final String skillName = "Pierce";

    /** Attack methods **/
    int calcDamage(Battler user, Battler target){
        return user.getAtk();
    }
    void addEffects(Battler user, Battler target){
        // does nothing
    }
    String getMessage(Battler user, Battler target){
        return user.getName() + " pierces through " + target.getName() + "'s defense!";
    }

}
