package main.java;
import java.util.*;

public class Battler {
    private int HP;
    private int MaxHP;
    private int MP;
    private int MaxMP;
    private int Atk;
    private int Def;
    private int EvaRate; //evasion rate (0-100)
    private String name;
    private boolean guard;
    private Attack currentAttack;
    private Set<Attack> specialAttacks;

    public Battler(){
        this("",0,0,0,0,0,100,0);
    }

    public Battler(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def,
                    int EvaRate){
        this.name = name;
        this.HP = HP;
        this.MaxHP = MaxHP;
        this.MP = MP;
        this.MaxMP = MaxMP;
        this.Atk = Atk;
        this.Def = Def;
        this.EvaRate = EvaRate;
        guard = false;
        currentAttack = new DefaultAttack();
    }

    public Battler(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        this(name, HP, MaxHP, MP, MaxMP, Atk, Def, 10);
    }

    public Battler(String name, int HP, int MP, int Atk, int Def){
        this(name, HP, HP, MP, MP, Atk, Def);
    }

    public void useSkill(Battler user, Battler target){
        int damage = currentAttack.calcDamage(user, target);
        damage = currentAttack.applyVariance(damage);
        if(currentAttack.crit(user)){ //if crit
            int oldDamage = damage;
            damage = currentAttack.applyCrit(damage); //multiply attack
            if(oldDamage < damage){ //if damage was actually changed (you can remove this check lol)
                System.out.println(currentAttack.getCritMessage(user, target));
            }
        }
        if(currentAttack.missed(user)){ //if missed
            System.out.println(currentAttack.getMissMessage(user, target));
        }
        else if(currentAttack.evaded(target)){ //if attack evaded
            System.out.println(currentAttack.getEvaMessage(user, target));
        }
        else{ //if hit

        }
    }

/**Getters and setters for basic variables**/
    public int getHP() {return HP;}

    public void setHP(int HP) {this.HP = HP;}

    public int getMaxHP() {return MaxHP;}

    public void setMaxHP(int maxHP) {MaxHP = maxHP;}

    public int getMP() {return MP;}

    public void setMP(int MP) {this.MP = MP;}

    public int getMaxMP() {return MaxMP;}

    public void setMaxMP(int maxMP) {MaxMP = maxMP;}

    public int getAtk() {return Atk;}

    public void setAtk(int atk) {Atk = atk;}

    public int getDef() {return Def;}

    public void setDef(int def) {Def = def;}

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
