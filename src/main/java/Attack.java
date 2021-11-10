package main.java;
public abstract class Attack {

    /** Attack Constants **/
    private static final int PHYSICAL = 0;
    private static final int GUARANTEED = 1;

    /** Attack Parameters **/
    private int mpCost;
    private int numOfHits;
    private String skillName;
    private int critRate;
    private int critMultiplier;
    private int hitRate;
    private int attackType;

    /** Default Attack Parameters **/
    Attack(){
        mpCost = 0;
        numOfHits = 1;
        skillName = "Undefined";
        critRate = 0;
        critMultiplier = 3;
        hitRate = 100;
        attackType = PHYSICAL;
    }

    /** Abstract methods **/
    abstract int calcDamage(Battler user, Battler target);
    abstract void addEffects(Battler user, Battler target);
    abstract String getMessage(Battler user, Battler target);

    /** Methods for attacks **/
    /* Check if skill is usable with current MP */
    public boolean isUsableMp(Battler user) {
        return (user.getMP() >= mpCost);
    }
    /* Method for applying crits after calling calcDamage() */
    public int applyCrit(int damage) {
        return damage * critMultiplier;
    }
    /* Check if crit */
    public boolean crit(Battler user){
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

}//end attack class
