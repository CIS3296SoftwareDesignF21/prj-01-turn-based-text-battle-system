package main.java;
import main.java.Attacks.*;

import java.util.*;

public class Battler {
    private int HP;
    private int MaxHP;
    private int MP;
    private int MaxMP;
    private int Atk;
    private int Def;
    private int CritRate; //crit rate (0-100)
    private int HitRate; //hit rate (0-100)
    private int EvaRate; //evasion rate (0-100)
    private String name;
    private boolean guard;
    private Attack currentAttack;
    private Set<Attack> specialAttacks;

    public Battler(){
        this("",0,0,0,0,0,0,0,100,0);
    }

    public Battler(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def,
                   int CritRate, int HitRate, int EvaRate){
        this.name = name;
        this.HP = HP;
        this.MaxHP = MaxHP;
        this.MP = MP;
        this.MaxMP = MaxMP;
        this.Atk = Atk;
        this.Def = Def;
        this.CritRate = CritRate;
        this.HitRate = HitRate;
        this.EvaRate = EvaRate;
        guard = false;
        currentAttack = new DefaultAttack();
        specialAttacks = new HashSet<Attack>();
    }

    public Battler(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        this(name, HP, MaxHP, MP, MaxMP, Atk, Def, 10, 100, 10);
    }

    public Battler(String name, int HP, int MP, int Atk, int Def){
        this(name, HP, HP, MP, MP, Atk, Def);
    }

    public void useSkill(Battler user, Battler target){
        for(int i = 0; i < currentAttack.getNumOfHits(); i++) {
            int damage = currentAttack.damageProcessing(user, target);
            user.subtractMP(currentAttack.getMpCost());
            if(currentAttack.hitProcessing(user, target)){
                System.out.println(currentAttack.getMessage(user, target));
                currentAttack.addEffects(user, target);
                if(target.isGuarding() && damage > 0) damage /= 2; //guarding halves damage if losing health
                if(!isGuarding()) { //cant damage if guarding
                    target.subtractHP(damage);
                    if (damage >= 0) System.out.println(target.getName() + " took " + damage + " damage!");
                    else System.out.println(target.getName() + " recovered " + Math.abs(damage) + " HP");
                }
            }
        }
    }

/**Getters and setters for basic variables**/
    public int getHP() {return HP;}

    public void setHP(int HP) {this.HP = HP;}

    public void subtractHP(int HP) {
        this.HP -= HP;
        if(HP < 0) HP = 0;
    }

    public int getMaxHP() {return MaxHP;}

    public void setMaxHP(int maxHP) {MaxHP = maxHP;}

    public int getMP() {return MP;}

    public void setMP(int MP) {this.MP = MP;}

    public void subtractMP(int MP) {
        this.MP -= MP;
        if(MP < 0) MP = 0;
    }

    public int getMaxMP() {return MaxMP;}

    public void setMaxMP(int maxMP) {MaxMP = maxMP;}

    public int getAtk() {return Atk;}

    public void setAtk(int atk) {Atk = atk;}

    public int getDef() {return Def;}

    public void setDef(int def) {Def = def;}

    public int getCritRate() {return CritRate;}

    public void setCritRate(int critRate) {CritRate = critRate;}

    public int getHitRate() {return HitRate;}

    public void setHitRate(int hitRate) {HitRate = hitRate;}

    public int getEvaRate() {return EvaRate;}

    public void setEvaRate(int evaRate) {EvaRate = evaRate;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public boolean isGuarding() {return guard;}

    public void setGuard(boolean guard) {this.guard = guard;}

    public Attack getCurrentAttack() {
        return currentAttack;
    }

    public void setCurrentAttack(Attack currentAttack) {
        this.currentAttack = currentAttack;
    }

    public Set<Attack> getSpecialAttacks() {
        return specialAttacks;
    }

    public void addSpecialAttack(Attack specialAttack) {
        this.specialAttacks.add(specialAttack);
    }
}
