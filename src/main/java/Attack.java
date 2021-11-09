package main.java;
public abstract class Attack implements attackInterface {


    private int mpCost = 0;
    private int numOfHits = 0;
    private String skillName = "";

    public Attack(int mpCosts, int numOfHits, String skillName) {

        this.mpCost = mpCost;
        this.numOfHits = numOfHits;
        this.skillName = skillName;
    }


    @Override
    public void setMpCosts(int mpCosts) {
        
    }

    @Override
    public int getMpCosts() {
        return 0;
    }

    @Override
    public void setNumOfHits(int numOfHits) {

    }

    @Override
    public int getNumOfHits() {
        return 0;
    }

    @Override
    public void setSkillName(String skillName) {

    }

    @Override
    public String getSkillName() {
        return null;
    }

    @Override
    public int calcDamage(Battler user, Battler target) {
        return 0;
    }

    @Override
    public int addEffects(Battler user, Battler target) {
        return 0;
    }

    @Override
    public boolean isUsableMp(Battler user) {
        return false;
    }

    @Override
    public String getMessage(Battler user, Battler target) {
        return null;
    }
}//end attack class
