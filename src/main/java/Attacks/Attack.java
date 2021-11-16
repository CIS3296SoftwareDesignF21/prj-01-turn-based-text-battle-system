package Attacks;
import Battlers.*;

public abstract class Attack {

    /** Attack Constants **/
    private static final int PHYSICAL = 0; //subject to hit/evasion rates
    private static final int GUARANTEED = 1; //guaranteed to hit, ignore hit/evasion rates

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

    /** Default Attack Parameters **/
    public Attack(){
        mpCost = 0;
        numOfHits = 1;
        skillName = "Undefined";
        critRate = 0;
        critMultiplier = 3;
        hitRate = 100;
        attackType = PHYSICAL;
        critAbility = true;
        variance = 20;
    }


    /** Abstract methods **/
    abstract public int calcDamage(Battler user, Battler target);
    abstract public void addEffects(Battler user, Battler target);
    abstract public String getMessage(Battler user, Battler target);

    /** Methods for attacks **/

    /* Damage Processing: Processes and Applies Critical Hits and Random Variance */
    public int processDamage(Battler user, Battler target){
        int damage = calcDamage(user, target);
        damage = applyVariance(damage);
        if (crit(user)) { //if crit
            damage = applyCrit(damage); //multiply attack
            System.out.println(getCritMessage(user,target));
        }
        if(target.isGuarding()) { damage /= 2;}

        //If you are targeting yourself, negative damage can heal
        //So we don't set a floor, but if you are attempting to hit an enemy,
        //the damage shouldn't fall below 0
        if(!target.equals(user) && damage < 0){
            damage = 0;
        }
        return damage;
    }
    /* Hit Processing: Processes Misses and Evasions */
    public boolean processHit(Battler user, Battler target){
        if (missed(user)) { //if missed
            System.out.println(getMissMessage(user, target));
            return false;
        } else if (evaded(target)) { //if attack evaded
            System.out.println(getEvaMessage(user, target));
            return false;
        }
        return true;
    }

    public void processAttack(Battler user, Battler target){
        int damage = 0;
        if(mpCost > user.getMP()){
            System.out.printf("Not enough MP! %s failed!\n",skillName);
            return;
        } else {
            user.subtractMP(mpCost);
        }
        for(int i = 0; i < numOfHits; i++){
            System.out.println(getMessage(user,target));
            if(processHit(user,target)){
                damage = processDamage(user,target);
                addEffects(user,target);
                target.subtractHP(damage);
                if(damage >= 0)
                    System.out.println(target.getName() + " took " + damage + " damage!");
                else
                    System.out.println(target.getName() + " recovered " + Math.abs(damage) + " HP");
            }
        }
        System.out.printf("%s has %d health remaining\n\n",target.getName(),target.getHP());
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
        int rate2 = getHitRate();
        return Rates.percentRateApplied(rate1) && Rates.percentRateApplied(rate2);
    }
    /* Check if miss */
    public boolean missed(Battler user){
        return !hit(user);
    }
    /* Check if evaded */
    public boolean evaded(Battler target){
        if(attackType == GUARANTEED) return false;
        int rate = target.getEvaRate();
        return Rates.percentRateApplied(rate);
    }
    /* Get Messages for cases of a Critical Hit, Miss, or Evaded Hit */
    public String getCritMessage(Battler user, Battler target){
        return user.getName() + " dealt a critical blow!";
    }
    public String getMissMessage(Battler user, Battler target){
        return user.getName() + " missed!";
    }
    public String getEvaMessage(Battler user, Battler target){
        return target.getName() + " evaded the attack!";
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
    public void setHitRate(int hitRate) { this.hitRate = hitRate;}
    public int getHitRate() { return hitRate;}
    public void setAttackType(int attackType) { this.attackType = attackType;}
    public int getAttackType() { return attackType;}
    public void setCritAbility(boolean critAbility) { this.critAbility = critAbility;}
    public boolean canCrit() { return critAbility;}
    public void setVariance(int variance) { this.variance = variance;}
    public int getVariance() { return variance;}

}//end attack class
