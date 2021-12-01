package Attacks;
import Battlers.*;
import RandomGeneration.Rates;

import java.util.HashMap;
import java.util.Map;

import static Battlers.Battler.*;

public abstract class Attack {

    /** Attack Constants **/
    public static final int DEFAULT = 0; //subject to hit/evasion rates
    public static final int GUARANTEED = 1; //guaranteed to hit, ignore hit/evasion rates
    public static final int EFFECTS = 2; //status effects/buffs,doesn't print attack/affinity message,ignore player hit/evasion rates

    /** Attack Settings **/
    private static int sleepTime = 500; //amount to wait between messages

    /** Attack Parameters **/
    private int mpCost; //cost of skill to use
    private int numOfHits; //number times calcDamage() applied
    private String skillName; //name of skill
    private int critRate; //crit rate of skill (0-100)
    private int critMultiplier; //amount multiplied when crit
    private int hitRate; //hit rate of skill (0-100)
    private int attackType; //type of skill
    private boolean critAbility; //if skill can crit
    private int variance; //random percent multiplied to attack, then added to attack (-var to +var)
    private String element; //element type of the attack
    private boolean targetUser; //if skill targets user

    /** Default Attack Parameters **/
    public Attack(){
        mpCost = 0;
        numOfHits = 1;
        skillName = "Undefined";
        critRate = 0;
        critMultiplier = 3;
        hitRate = 100;
        attackType = DEFAULT;
        critAbility = true;
        variance = 20;
        element = PHYSICAL;
        targetUser = false;
    }


    /** Abstract methods **/
    abstract public int calcDamage(Battler user, Battler target);
    abstract public boolean addEffects(Battler user, Battler target);
    abstract public String getMessage(Battler user, Battler target);

    /** Methods for attacks **/

    /* Damage Processing: Processes and Applies Critical Hit, Random Variance, and applies Elements */
    public int processDamage(Battler user, Battler target){
        int damage = calcDamage(user, target);
        Map<String, Integer> resists = target.getResistsMap();
        damage = applyElements(damage, resists); //apply elemental resistances
        String elementMessage = getElementMessage(target.getName(), resists);
        if(!elementMessage.isEmpty()){ //dont print message if no message
            System.out.println(elementMessage); sleep();
        }
        damage = applyVariance(damage);
        if (crit(user)) { //if crit
            damage = applyCrit(damage); //multiply attack
            System.out.println(getCritMessage(user,target)); sleep();
        }
        if(target.isGuarding()) { damage /= 2;} //halve damage if guarding

        //apply buffs/debuffs rate
        int damageBefore = damage;
        damage += (int)(damageBefore * user.getBuffRate("atk"));
        damage -= (int)(damageBefore * target.getBuffRate("def"));
        if((damageBefore ^ damage) < 0) damage = 0; //if signs are different

        //If you are targeting yourself, negative damage can heal
        //So we don't set a floor, but if you are attempting to hit an enemy,
        //the damage shouldn't fall below 0
        /*if(!target.equals(user) && damage < 0){
            damage = 0;
        }*/
        return damage;
    }

    /* Hit Processing: Processes Misses and Evasions */
    public boolean processHit(Battler user, Battler target){
        if (missed(user)) { //if missed
            System.out.println(getMissMessage(user, target)); sleep();
            return false;
        } else if (evaded(target)) { //if attack evaded
            System.out.println(getEvaMessage(user, target)); sleep();
            return false;
        }
        return true;
    }

    /* Attack Process: Processes Everything: Damage & Effects */
    public void processAttack(Battler user, Battler target){
        int damage;
        if(mpCost > user.getMP()){
            System.out.printf("Not enough MP! %s failed!\n",skillName); sleep();
            return;
        } else {
            user.subtractMP(mpCost);
        }
        System.out.println(getMessage(user,target)); sleep();
        for(int i = 0; i < numOfHits; i++){ //loop amount of hits
            if(processHit(user,target)){ //if attack hit
                damage = processDamage(user,target);
                target.subtractHP(damage);
                if(getAttackType() != EFFECTS) { //dont print message if its an effect
                    if (damage >= 0) //if damage
                        System.out.println(target.getName() + " took " + damage + " damage!");
                    else //if heal
                        System.out.println(target.getName() + " recovered " + Math.abs(damage) + " HP!");
                    sleep();
                }
                Map<String, Integer> oldUserBuffs = new HashMap<>(user.getBuffMap());
                Map<String, Integer> oldTargetBuffs = new HashMap<>(target.getBuffMap());
                if(addEffects(user, target)){ //if effects added & add effects
                    if(!processAllBuffs(user, oldUserBuffs, target, oldTargetBuffs)){ //if no buffs applied
                        if(damage == 0) System.out.println("Nothing happened!"); //if no damage
                    }
                }
            }
        }
        //System.out.printf("%s has %d health remaining\n\n",target.getName(),target.getHP());
        if(target.getHP() <= 0){
            System.out.println(target.getName() + " has been slain!"); sleep();
        }
        sleep();
        System.out.println();
    }
    /* Process a specific buff for the select battler */
    public boolean processBuff(Battler battler, Map<String, Integer> oldBuffs,
                               String buffType, String buffName){
        int difference = battler.getBuff(buffType) - oldBuffs.get(buffType); //buff now - buff before
        if(difference != 0){ //if buff/debuff is actually applied
            System.out.println(getBuffMessage(battler.getName(), buffName, difference)); sleep();
            return true;
        }
        return false;
    }
    /* Process buffs/debuffs only for the select battler */
    public boolean processBuffs(Battler battler, Map<String, Integer> oldBuffs){
        boolean changes;
        changes = processBuff(battler, oldBuffs, "atk", "Attack");
        changes = processBuff(battler, oldBuffs, "def", "Defense") || changes;
        return changes;
    }
    /* Process every buff/debuff for the user AND target */
    public boolean processAllBuffs(Battler user, Map<String, Integer> oldUserBuffs,
                                   Battler target, Map<String, Integer> oldTargetBuffs){
        return processBuffs(user, oldUserBuffs) || processBuffs(target, oldTargetBuffs);
    }
    /* Check if skill is usable with current MP */
    public boolean isUsableMp(Battler user) {
        return (user.getMP() >= mpCost);
    }
    /* Method for applying variance after calling calcDamage() */
    public int applyVariance(int damage){
        return damage + (int)(damage * Rates.variance(getVariance()));
    }
    /* Method for applying crits after calling calcDamage() */
    public int applyCrit(int damage) {
        return damage * critMultiplier;
    }
    /* Method for applying elemental resistances after calling calcDamage() */
    public int applyElements(int damage, Map<String, Integer> resists){
        return (int)(damage * ((double)resists.get(getElement()) / 100));
    }
    /* Check if crit */
    public boolean crit(Battler user){
        if(!canCrit()) return false;
        int rate = user.getCritRate() + getCritRate();
        return Rates.percentRateApplied(rate);
    }
    /* Check if hit */
    public boolean hit(Battler user){
        if(attackType == GUARANTEED) return true;
        int rate1 = user.getHitRate();
        if(attackType == EFFECTS) rate1 = 100;
        int rate2 = getHitRate();
        return Rates.percentRateApplied(rate1) && Rates.percentRateApplied(rate2);
    }
    /* Check if miss */
    public boolean missed(Battler user){
        return !hit(user);
    }
    /* Check if evaded */
    public boolean evaded(Battler target){
        if(attackType == GUARANTEED || attackType == EFFECTS) return false;
        int rate = target.getEvaRate();
        return Rates.percentRateApplied(rate);
    }
    /* Wait Between Messages */
    public static void sleep(){
        try {Thread.sleep(sleepTime);
        } catch (Exception e) {System.out.println(e);}
    }
    public static void changeSleepTime2(int sleepTime2){
        sleepTime = sleepTime2;
    }
    public static void changeSleepTime(int sleepTime2){ //options: 1000,750,500,250,0
        changeSleepTime2(1000 - 250 * (sleepTime2-1));
    }
    /* Get Messages for cases of a Critical Hit, Miss, or Evaded Hit, along with Buffs/Debuffs and Elements */
    public String getCritMessage(Battler user, Battler target){
        return user.getName() + " dealt a critical blow!";
    }
    public String getMissMessage(Battler user, Battler target){
        return user.getName() + " missed!";
    }
    public String getEvaMessage(Battler user, Battler target){
        return target.getName() + " evaded the attack!";
    }
    public String getBuffMessage(String name, String buffName, int difference){
        String effect = "increased";
        if(difference < 0){effect = "decreased";}
        if(Math.abs(difference) > 1){
            effect += " by " + Math.abs(difference) + " levels";
        }
        return name + "'s " + buffName.toLowerCase() + " " + effect + "!";
    }
    public String getElementMessage(String name, Map<String, Integer> resists){
        if(attackType == EFFECTS) return "";
        switch(resists.get(getElement())){
            case WEAK: return name + " is weak to " + element.toLowerCase() + " attacks!";
            //case STANDARD: return "";
            case RESIST: return name + " resists " + element.toLowerCase() + " attacks!";
            case BLOCK: return name + " blocks " + element.toLowerCase() + " attacks!";
            case ABSORB: return name + " absorbs " + element.toLowerCase() + " attacks!";
            default: return "";
        }
    }

    /** Getters and setters for variables **/
    public void setMpCost(int mpCost) { this.mpCost = mpCost;}
    public int getMpCost() { return mpCost;}
    public void setNumOfHits(int numOfHits) { this.numOfHits = numOfHits;}
    public int getNumOfHits() { return numOfHits;}
    public void setSkillName(String skillName) { this.skillName = skillName;}
    public String getSkillName() { return skillName;}
    public void setCritRate(int critRate) { this.critRate = critRate;}
    public int getCritRate() { return critRate;}
    public void setCritMultiplier(int critMultiplier) { this.critMultiplier = critMultiplier;}
    public int getCritMultiplier() { return critMultiplier;}
    public void setHitRate(int hitRate) { this.hitRate = hitRate;}
    public int getHitRate() { return hitRate;}
    public void setAttackType(int attackType) { this.attackType = attackType;}
    public int getAttackType() { return attackType;}
    public void setCritAbility(boolean critAbility) { this.critAbility = critAbility;}
    public boolean canCrit() { return critAbility;}
    public void setVariance(int variance) { this.variance = variance;}
    public int getVariance() { return variance;}
    public void setElement(String element) { this.element = element;}
    public String getElement() { return element;}
    public void setTargetUser(boolean targetUser) { this.targetUser = targetUser;}
    public boolean targetsUser() { return targetUser;}

}//end attack class
