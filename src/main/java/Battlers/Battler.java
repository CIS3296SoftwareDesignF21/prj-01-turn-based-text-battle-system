package Battlers;
import Attacks.*;

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
        this(name, HP, MaxHP, MP, MaxMP, Atk, Def, 10, 95, 10);
    }

    public Battler(String name, int HP, int MP, int Atk, int Def){
        this(name, HP, HP, MP, MP, Atk, Def);
    }

    public void useAction(Battler target,String action){
        if(action.equals("Attack")){
            currentAttack.processAttack(this,target);
        } else if(action.equals("Guard")){
        	System.out.printf(name + " is guarding\n\n");
            setGuard(true);
        } else{
            System.out.println("Invalid action selected");
        }

    }


/**Getters and setters for basic variables**/
    public int getHP() {return HP;}

    public void setHP(int HP) {this.HP = HP;}

    public void subtractHP(int HP) {
        this.HP -= HP;
        if(this.HP < 0) this.HP = 0;
    }

    public void recoverHP() {HP = MaxHP;}

    public int getMaxHP() {return MaxHP;}

    public void setMaxHP(int maxHP) {MaxHP = maxHP;}

    public int getMP() {return MP;}

    public void setMP(int MP) {this.MP = MP;}

    public void subtractMP(int MP) {
        this.MP -= MP;
        if(this.MP < 0) this.MP = 0;
    }

    public void recoverMP() {MP = MaxMP;}

    public void recoverAll() {recoverHP(); recoverMP();}

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

    public void setGuard(boolean guard) {
    	this.guard = guard;
    	//Def = Def * 2; 
    	}

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
    public void endTurn() {
        setCurrentAttack(new DefaultAttack());
    	//Def = Def / 2;
    	this.guard = false;
    	
    }

    public Attack[] getSpecialAttacksArray(){
        return specialAttacks.toArray(new Attack[1]);
    }

    public Attack attackMenu(Attack[] specials){
        attackMenuPrint(specials);
        return attackMenuSelect(specials);
    }

    public void attackMenuPrint(Attack[] specials){
        Scanner sc = new Scanner(System.in);
        System.out.println("Total MP: " + getMP());
        int i, userInt = 0, len;
        String usable;
        len = specials.length;
        boolean anyUse = false;
        for(i = 0; i < len; i++){
            //System.out.print((i+1) + ": " + specials[i].getSkillName());
            if(!specials[i].isUsableMp(this)) usable = "[X]";
            else usable = "   ";
            System.out.format("%-20s",(i+1) + ": " + specials[i].getSkillName());
            System.out.print(" [" + specials[i].getMpCost() + "]" + usable + " ");
            if(specials[i].isUsableMp(this)) anyUse = true;
            if(i % 3 == 2 || i == len-1) System.out.println();
        }
        if(!anyUse) System.out.println("-1: Cower");
    }

    public Attack attackMenuSelect(Attack[] specials){
        Scanner sc = new Scanner(System.in);
        int userInt = 0;
        while(true){
            System.out.println("Choose an action:");
            userInt = sc.nextInt();
            if(userInt == -1){
                System.out.println(getName() + " out of options selects Attack!");
                return new DefaultAttack();
            }
            else if(userInt < 1 || userInt > specials.length){ System.out.println("Invalid Skill!");}
            else if(!specials[userInt-1].isUsableMp(this)){ //if not usable
                System.out.println("Insufficient MP!");
                userInt = 0;
            }
            else break;
        }
        return specials[userInt-1];
    }
}
