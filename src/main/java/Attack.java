package main.java;
public abstract class Attack {

    /** Attack Parameters **/
    private int mpCost;
    private int numOfHits;
    private String skillName;
    private double critRate;
    private double hitRate;

    /** Default Attack Parameters **/
    Attack(){
        mpCost = 0;
        numOfHits = 1;
        skillName = "Undefined";
        critRate = 0;
        hitRate = 1;
    }

    /** Abstract methods **/
    abstract int calcDamage(Battler user, Battler target);
    abstract void addEffects(Battler user, Battler target);
    abstract String getMessage(Battler user, Battler target);

    /** Methods for attacks **/
    public boolean isUsableMp(Battler user) {
        return (user.getMP() >= mpCost);
    }

    /** Getters and setters for variables **/
    public void setMpCost(int mpCost) { this.mpCost = mpCost;}
    public int getMpCost() { return mpCost;}
    public void setNumOfHits(int numOfHits) { this.numOfHits = numOfHits;}
    public int getNumOfHits() { return numOfHits;}
    public void setSkillName(String skillName) { this.skillName = skillName;}
    public String getSkillName() { return skillName;}
    public void setCritRate(int critRate) { this.critRate = critRate;}
    public double getCritRate() { return critRate;}
    public void setHitRate(int hitRate) { this.hitRate = hitRate;}
    public double getHitRate() { return hitRate;}

}//end attack class
