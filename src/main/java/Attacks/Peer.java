package Attacks;

import Battlers.Battler;

public class Peer extends Attack {

    /** Peer values **/
    public Peer(){
        super();
        setMpCost(15);
        setSkillName("Peer");
        setCritAbility(false);
        setAttackType(EFFECTS);
    }

    /** Attack methods **/
    public int calcDamage(Battler user, Battler target){
        return 0;
    }
    public boolean addEffects(Battler user, Battler target){
        System.out.println(target.getName() + "'s HP: " + target.getHP() + "/" + target.getMaxHP()); sleep();
        System.out.println(target.getName() + "'s MP: " + target.getMP() + "/" + target.getMaxMP()); sleep();
        return false; //just dont want it to print "nothing happened!"
    }
    public String getMessage(Battler user, Battler target){
        return user.getName() + " peers through the enemy's defenses!";
    }

}
