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
    private Attack defaultAttack;
    private Attack currentAttack;
    private Set<Attack> specialAttacks;
    private final int buffCap = 3, buffRate = 30; //buffcap is cap of buffs
    private Map<String, Integer> buffs; //Map of buffs, string: parameter name, integer: amount * rate

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
        defaultAttack = new BasicAttack();
        currentAttack = defaultAttack;
        specialAttacks = new HashSet<Attack>();
        initBuffMap();
    }

    public Battler(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        this(name, HP, MaxHP, MP, MaxMP, Atk, Def, 10, 95, 10);
    }

    public Battler(String name, int HP, int MP, int Atk, int Def){
        this(name, HP, HP, MP, MP, Atk, Def);
    }

    public void initBuffMap(){
        buffs = new HashMap<String, Integer>();
        buffs.put("atk", 0);
        buffs.put("def", 0);
    }

    public void useAction(Battler target,String action){
        switch (action) {
            case "Attack":
                currentAttack.processAttack(this, target);
                break;
            case "Guard":
                System.out.println(name + " guards\n");
                setGuard(true);
                break;
            case "Cower": //nothing happens here
                System.out.println(name + " cowers in fear!\n");
                break;
            default:
                System.out.println("Invalid action selected");
                break;
        }

    }

    public void buff(String buffType, int amount){
        switch(buffType){
            case "atk": case "def":
                buffs.put(buffType, (Math.max(buffCap*-1, Math.min(buffCap, buffs.get(buffType)+amount))));
                break; //min: -buffCap, max: buffCap, add amount to value in map
            default:
                System.out.println("INVALID BUFF TYPE!"); break;
        }
    }
    public void debuff(String buffType, int amount){
        buff(buffType, amount*-1);
    }

    public double getBuffRate(String buffType){ // buff amount * buffrate in percent form
        return getBuff(buffType) * ((double)buffRate / 100);
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

    public void setGuard(boolean guard) {this.guard = guard;}

    public Attack getDefaultAttack() {return defaultAttack;}

    public void setDefaultAttack(Attack defaultAttack) {this.defaultAttack = defaultAttack;}

    public Attack getCurrentAttack() {return currentAttack;}

    public void setCurrentAttack(Attack currentAttack) {this.currentAttack = currentAttack;}

    public void defaultCurrentAttack() {currentAttack = defaultAttack;}

    public boolean usedDefaultAttack() {
        if(currentAttack == defaultAttack) return true;
        return false;
    }

    public Set<Attack> getSpecialAttacks() {return specialAttacks;}

    public void addSpecialAttack(Attack specialAttack) {this.specialAttacks.add(specialAttack);}

    public void endTurn() {
    	setGuard(false);
    }

    public Attack[] getSpecialAttacksArray(){return specialAttacks.toArray(new Attack[0]);}

    public int getBuff(String buffType){
        return buffs.get(buffType);
    }

    public Map<String, Integer> getBuffMap(){
        return buffs;
    }
}
