package Battlers;
import Attacks.*;
import RandomGeneration.Rates;

import java.util.*;

public class Battler {
    /** Battler Constants **/
    /* Affinity Levels */
    public static final int WEAK = 200;
    public static final int STANDARD = 100;
    public static final int RESIST = 50;
    public static final int BLOCK = 0;
    public static final int ABSORB = -100;
    /* Affinity/Element Types */
    public static final String PHYSICAL = "Physical";
    public static final String FIRE = "Fire";
    public static final String ICE = "Ice";
    public static final String LIGHTNING = "Lightning";
    public static final String HOLY = "Holy";
    public static final String DARK = "Dark";
    /* Battler Variables */
    private int HP;
    private int MaxHP;
    private int MP;
    private int MaxMP;
    private int Atk;
    private int Def;
    private int MAtk;
    private int MDef;
    private int CritRate; //crit rate (0-100)
    private int HitRate; //hit rate (0-100)
    private int EvaRate; //evasion rate (0-100)
    private int Agility;
    private String name;
    private boolean guard;
    private Attack defaultAttack;
    private Attack currentAttack;
    private ArrayList<Attack> specialAttacks;
    private ArrayList<Attack> magicAttacks;
    private final int buffCap = 3, buffRate = 30; //buffcap is cap of buffs, rate is % applied to stat
    private Map<String, Integer> buffs; //Map of buffs, string: parameter name, integer: amount * rate
    private Map<String, Integer> resists; //Map of resistances, string: affinity name, integer: resistance percent

    public Battler(){
        this("",0,0,0,0,0,0,0,0,10,100,10);
    }

    public Battler(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def, int MAtk, int MDef,
                   int CritRate, int HitRate, int EvaRate){
        this.name = name;
        this.HP = HP;
        this.MaxHP = MaxHP;
        this.MP = MP;
        this.MaxMP = MaxMP;
        this.Atk = Atk;
        this.Def = Def;
        this.MAtk = MAtk;
        this.MDef = MDef;
        this.CritRate = CritRate;
        this.HitRate = HitRate;
        this.EvaRate = EvaRate;
        this.Agility = Agility; 
        guard = false;
        defaultAttack = new BasicAttack();
        currentAttack = defaultAttack;
        specialAttacks = new ArrayList<>();
        magicAttacks = new ArrayList<>();
        initBuffMap();
        initResistsMap();
    }

    public Battler(String name, int MaxHP, int MaxMp, int Atk, int Def, int MAtk, int MDef){
        this(name,MaxHP,MaxHP,MaxMp,MaxMp,Atk,Def,MAtk,MDef,10,100,10);
    }


    public Battler(String name, int HP, int MP, int Atk, int Def){
        this(name, HP, HP, MP, MP, Atk, Def, Atk, Def, 10, 100, 10);
    }

    public void initBuffMap(){
        buffs = new HashMap<>();
        buffs.put("atk", 0);
        buffs.put("def", 0);
    }

    public void initResistsMap(){
        resists = new HashMap<>();
        resists.put(FIRE, STANDARD);
        resists.put(ICE, STANDARD);
        resists.put(LIGHTNING, STANDARD);
        resists.put(PHYSICAL, STANDARD);
        resists.put(HOLY, STANDARD);
        resists.put(DARK, STANDARD);
    }

    /* Use the action correlated within the string: attack, guard, or cower */
    public void useAction(Battler target,String action){
        switch (action) {
            case "Attack": //use attack
                currentAttack.processAttack(this, target);
                break;
            case "Guard": //set guard flag to true, halves damage
                System.out.println(name + " guards\n"); Attack.sleep();
                setGuard(true);
                break;
            case "Cower": //nothing happens here
                System.out.println(name + " cowers in fear!\n"); Attack.sleep();
                break;
            default:
                System.out.println("Invalid action selected"); Attack.sleep();
                break;
        }
    }

    /* show a list of enemies and let the user choose the target of the attack */
    public int chooseTargetPosition(ArrayList<Enemy> targets){
        //Battler target = null;
        int targetInput = -2;
        Scanner stdin = new Scanner(System.in);
        if(currentAttack.targetsUser()) {
            targetInput = -1; //target = this;
        }else {
            int i = 0;
            int len = targets.size();
            for (Enemy battler : targets) {
                //System.out.printf("%d) %s \n", ++i, battler.getName());
                System.out.format("%-3s", (i+1) + ":");
                System.out.format("%-18s",battler.getName());
                if(i % 3 == 2 || i == len-1) System.out.println(); //3 enemies per row
                i++;
            }
            while (targetInput > len || targetInput < 1){
                System.out.println("Choose a target:");
                try {
                    targetInput = stdin.nextInt();
                } catch (InputMismatchException e) {stdin.next();}
            }
            targetInput--;
            //target = targets.get(targetInput - 1);
        }
        return targetInput;
    }

    /* randomly choose a target for the enemy side */
    public static int randomPlayerPosition(ArrayList<? extends Battler> targets){
        int numTargets = targets.size();
        if(numTargets == 0)
            return -1;
        return Rates.rand(-1,numTargets - 1); //-1: player, rest: allies
    }

    /* randomly choose a target for the ally side */
    public static int randomEnemyPosition(ArrayList<? extends Battler> targets){
        int numTargets = targets.size();
        return Rates.rand(0,numTargets - 1);
    }

    /* randomly choose an attack from the usable skills */
    public void randomAttackPattern(Battler target){
        //Need to think about how to set attack probabilities based on currently available attacks
        //Perhaps need to create an Arraylist with currently usable attacks to pull from, and worry about their percentages later

        ArrayList<Attack> usableAttacks = new ArrayList<>();
        usableAttacks.add(getDefaultAttack()); //add default attack

        for(Attack attack: this.getSpecialAttacks()){ //add usable special attacks
            if(attack.getMpCost() <= this.getMP()){
                usableAttacks.add(attack);
            }
        }

        for(Attack attack: this.getMagicAttacks()){ //add usable magic attacks
            if(attack.getMpCost() <= this.getMP()){
//              if(!attack.getSkillName().equals("Heal"))
                usableAttacks.add(attack);
            }
        }

        int numUsableAttacks = usableAttacks.size();

        Attack currentAttack = usableAttacks.get(Rates.rand(0, numUsableAttacks - 1)); //randomly choose
        //use attack
        if(currentAttack.targetsUser()) //if targets user
            currentAttack.processAttack(this,this);
        else
            currentAttack.processAttack(this,target);
    }

    /* add buff to buff map and add amount */
    public void buff(String buffType, int amount){
        if(buffs.replace(buffType, (Math.max(buffCap*-1, Math.min(buffCap, buffs.get(buffType)+amount)))) == null){
            System.out.println("INVALID BUFF TYPE!");
        }
    }
    /* add buff to buff map and decrease amount */
    public void debuff(String buffType, int amount){
        buff(buffType, amount*-1);
    }
    /* get the percent effect of the buff/debuff */
    public double getBuffRate(String buffType){ // buff amount * buffrate in percent form
        return getBuff(buffType) * ((double)buffRate / 100);
    }


/**Getters and setters for basic variables**/
    public int getHP() {return HP;}

    public void setHP(int HP) {this.HP = HP;}

    public void subtractHP(int HP) {
        this.HP = Math.min(MaxHP, Math.max(0, this.HP - HP));
    }

    public void recoverHP() {HP = MaxHP;}

    public int getMaxHP() {return MaxHP;}

    public void setMaxHP(int maxHP) {MaxHP = maxHP;}

    public int getMP() {return MP;}

    public void setMP(int MP) {this.MP = MP;}

    public void subtractMP(int MP) {
        this.MP = Math.min(MaxMP, Math.max(0, this.MP - MP));
    }

    public void recoverMP() {MP = MaxMP;}

    public void recoverAll() {recoverHP(); recoverMP();}

    public int getMaxMP() {return MaxMP;}

    public void setMaxMP(int maxMP) {MaxMP = maxMP;}

    public int getAtk() {return Atk;}

    public void setAtk(int atk) {Atk = atk;}

    public int getDef() {return Def;}

    public void setDef(int def) {Def = def;}

    public int getMAtk() {return MAtk;}

    public void setMAtk(int matk) {MAtk = matk;}

    public int getMDef() {return MDef;}

    public void setMDef(int mdef) {MDef = mdef;}

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
        return (currentAttack == defaultAttack);
    }

    public ArrayList<Attack> getSpecialAttacks() {return specialAttacks;}

    public void addSpecialAttack(Attack specialAttack) {this.specialAttacks.add(specialAttack);}

    public void endTurn() {
    	setGuard(false);
    }

    public Attack[] getSpecialAttacksArray(){return specialAttacks.toArray(new Attack[0]);}

    public boolean specialAttacksEmpty(){
        return specialAttacks.isEmpty();
    }

    public void addMagicAttack(Attack magicAttack) {this.magicAttacks.add(magicAttack);}

    public ArrayList<Attack> getMagicAttacks() {return magicAttacks;}

    public Attack[] getMagicAttacksArray(){return magicAttacks.toArray(new Attack[0]);}

    public boolean magicAttacksEmpty(){
        return magicAttacks.isEmpty();
    }

    public int getBuff(String buffType){
        return buffs.get(buffType);
    }

    public Map<String, Integer> getBuffMap(){
        return buffs;
    }

    public Map<String, Integer> getResistsMap(){
        return resists;
    }

    public void setResistance(String element, int affinity){
        if(resists.replace(element, affinity) == null){
            System.out.println("INVALID ELEMENT TYPE!!!");
        }
    }
}
