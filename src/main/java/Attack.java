package main.java;
public interface Attack {
    private int mpCosts;
    private int numOfHits;
    private String name;

    public Attack(){
        mpCosts = numOfHits = 0;
        name = "";
    }
    public Attack(int mpCosts, int numOfHits, String name) {

        this.mpCost = mpCost;
        this.numOfHits = numOfHits;
        this.name = name;
    }

    //getter and setter methods
    public void setMpCosts(int mpCosts){
        this.mpCosts = mpCosts;
    }
    public int getMpCosts(){
        return mpCosts;
    }
    public void setNumOfHits(int numOfHits){
        this.numOfHits = numOfHits;
    }
    public int getNumOfHits(){
        return numOfHits;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int calcDamage(Character a, Enemy b){ //assuming a is the characters current mp and b is the amount of damage
        this.calcDamage = a - b;
        if (this.calcDamage < 0) {
            this.calcDamage = 0;
        }
    }
    public boolean isUsableMp(){
        if(mpCosts > 0)
            return mpCosts;
    }
    public String getMessage(){
        return "";
    }


}//end attack class
