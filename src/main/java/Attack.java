package main.java;
public abstract class Attack {

    /** Attack Parameters **/
    private int mpCost;
    private int numOfHits;
    private String skillName;

    /** Abstract methods **/
    abstract int calcDamage(Battler user, Battler target);
    abstract void addEffects(Battler user, Battler target);
    abstract String getMessage(Battler user, Battler target);

    /** Methods for attacks **/
    public boolean isUsableMp(Battler user) {
        return (user.getMP() >= mpCost);
    }

    /** Getters and setters for variables **/
    public void setMpCosts(int mpCost) { this.mpCost = mpCost;}
    public int getMpCosts() { return mpCost;}
    public void setNumOfHits(int numOfHits) { this.numOfHits = numOfHits;}
    public int getNumOfHits() { return numOfHits;}
    public void setSkillName(String skillName) { this.skillName = skillName;}
    public String getSkillName() { return skillName;}

}//end attack class
