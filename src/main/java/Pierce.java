package main.java;
public class Pierce extends Attack{

    /** Default Pierce values **/
    Pierce(){
        super();
        setMpCost(15);
        setSkillName("Pierce");
    }

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
