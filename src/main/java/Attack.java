package main.java;
public abstract class Attack {

    private int mpCost = 0;
    private int numOfHits = 0;
    private String skillName = "";

    public Attack(int mpCosts, int numOfHits, String skillName) {

        this.mpCost = mpCost;
        this.numOfHits = numOfHits;
        this.skillName = skillName;
    }


    public void setMpCosts(int mpCost) {
        this.mpCost = mpCost;
    }

    public int getMpCosts() {
        return mpCost;
    }

    public void setNumOfHits(int numOfHits) {
        this.numOfHits = numOfHits;
    }

    public int getNumOfHits() {
        return numOfHits;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    abstract int calcDamage(Battler user, Battler target);

    abstract int addEffects(Battler user, Battler target);

    abstract String getMessage(Battler user, Battler target);

    public boolean isUsableMp(Battler user) {
        return false;
    }
}//end attack class
