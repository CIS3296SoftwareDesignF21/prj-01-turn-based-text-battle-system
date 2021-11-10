package main.java;
public abstract class Attack {

    /** Attack Parameters **/
    private int mpCost;
    private int numOfHits;
    private String skillName;
    private int critRate;
    private int critMultiplier;
    private int hitRate;

    /** Default Attack Parameters **/
    Attack(){
        mpCost = 0;
        numOfHits = 1;
        skillName = "Undefined";
        critRate = 0;
        critMultiplier = 3;
        hitRate = 100;
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
    public boolean isCrit(Battler user){
        int rate = user.getCritRate() + getCritRate();
        return isRateApplied(rate);
    }
    /* Check if hit */
    public boolean isHit(Battler user){
        int rate1 = user.getHitRate();
        int rate2 = getHitRate();
        return isRateApplied(rate1) && isRateApplied(rate2);
    }
    /* Check if miss */
    public boolean isMiss(Battler user){
        return !isHit(user);
    }
    /* Check if evaded */
    public boolean isEvade(Battler target){
        int rate = target.getEvaRate();
        return isRateApplied(rate);
    }
    /* Calculate a percentage chance into a boolean
    * rate: a number from 0 to 100 */
    public boolean isRateApplied(int rate){
        if(rate > 100) rate = 100;
        else if(rate < 0) rate = 0;
        if(rand(rate,100) <= rate) return true;
        return false;
    }
    /* Random function for easy use */
    public int rand(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
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

}//end attack class
