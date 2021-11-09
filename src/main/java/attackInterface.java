package main.java;

public interface attackInterface {

    void setMpCosts(int mpCosts);

    int getMpCosts();

    void setNumOfHits(int numOfHits);

    int getNumOfHits();

    void setSkillName(String skillName);

    String getSkillName();

    int calcDamage(Battler user, Battler target);

    int addEffects(Battler user, Battler target);

    boolean isUsableMp(Battler user);

    String getMessage(Battler user, Battler target);
}
