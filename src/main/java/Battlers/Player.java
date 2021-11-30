package Battlers;
import Attacks.*;
import RandomGeneration.Rates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

public class Player extends Battler{

    public void addSpecials(){
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
        addSpecialAttack(new Bash());
        addSpecialAttack(new VariantStrike());
        addSpecialAttack(new DesperateHit());
        addSpecialAttack(new Heal());
        addSpecialAttack(new InstantKill());
        addSpecialAttack(new DefenseDown());
        addSpecialAttack(new DebilitatingSlash());
        addSpecialAttack(new Peer());
        addMagicAttack(new Fire());
        addMagicAttack(new Freeze());
        addMagicAttack(new Shock());
        addMagicAttack(new Holy());
        addMagicAttack(new Plague());
        addMagicAttack(new AttackUp());
    }

    public Player(){
        super();
    }

    public Player(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def, int MAtk, int MDef, int CritRate, int HitRate, int EvaRate, int Agility){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, MAtk, MDef, CritRate, HitRate, EvaRate, Agility);
        //addSpecials();
    }

    public Player(String name, int MaxHP, int MaxMp, int Atk, int Def, int MAtk, int MDef, int Agility){
        super(name,MaxHP,MaxMp,Atk,Def,MAtk,MDef, Agility);
        //addSpecials();
    }


    public Player(String name, int MaxHP, int MaxMP, int Atk, int Def, int Agility){
        super(name, MaxHP, MaxMP, Atk, Def, Agility);
    }

    //Plan to make protected in the future
    /*public static Player randomPlayer(int level, String name){
        int hp, mp, atk, def;
        hp = mp = atk = def = 0;
        Player newCharacter;
        if(level == 1){
            hp = Rates.rand(500,1000);
            mp = Rates.rand(1,101);
            atk = Rates.rand(30,50);
            def = Rates.rand(50,60);
        } else if(level == 2){
            hp = Rates.rand(1000,1500);
            mp = Rates.rand(51,151);
            atk = Rates.rand(45,65);
            def = Rates.rand(55,65);
        } else if (level == 3){
            hp = Rates.rand(1500,2000);
            mp = Rates.rand(101,201);
            atk = Rates.rand(50,70);
            def = Rates.rand(60,70);
        } else {
            System.out.println("Level must be between one and three");
            System.exit(-1);
        }
        newCharacter = new Player(name,hp,mp,atk,def);
        return newCharacter;
    }*/

    public static int[] returnRandomStats(int level){
        int hp, mp, atk, def;
        hp = mp = atk = def = 0;
        if(level == 1){
            hp = Rates.rand(500,1000);
            mp = Rates.rand(1,101);
            atk = Rates.rand(30,50);
            def = Rates.rand(50,60);
        } else if(level == 2){
            hp = Rates.rand(1000,1500);
            mp = Rates.rand(51,151);
            atk = Rates.rand(45,65);
            def = Rates.rand(55,65);
        } else if (level == 3){
            hp = Rates.rand(1500,2000);
            mp = Rates.rand(101,201);
            atk = Rates.rand(50,70);
            def = Rates.rand(60,70);
        } else {
            System.out.println("Level must be between one and three");
            System.exit(-1);
        }
        return new int[]{hp,mp,atk,def};
    }



    public Attack attackMenu(Attack[] specials){
        attackMenuPrint(specials);
        return attackMenuSelect(specials);
    }

    public void attackMenuPrint(Attack[] specials){
        System.out.println("MP: " + getMP() + "/" + getMaxMP());
        int i, len;
        String usable;
        len = specials.length;
        boolean anyUse = false;
        for(i = 0; i < len; i++){ //print everything in specials array
            //System.out.print((i+1) + ": " + specials[i].getSkillName());
            if(!specials[i].isUsableMp(this)) usable = "[X]";
            else usable = "   ";
            System.out.format("%-3s", (i+1) + ":");
            System.out.format("%-18s",specials[i].getSkillName());
            System.out.format("%-4s","[" + specials[i].getMpCost() + "]"); //set this to %-5s if skills use triple digit MP
            System.out.print(usable + " ");
            if(specials[i].isUsableMp(this)) anyUse = true;
            if(i % 3 == 2 || i == len-1) System.out.println(); //3 skills per row
        }
        if(!anyUse) System.out.println("-1: Cower");
    }

    public Attack attackMenuSelect(Attack[] specials){
        Scanner sc = new Scanner(System.in);
        int userInt;
        while(true){
            System.out.println("Choose an action:");
            try {
                userInt = sc.nextInt();
            } catch (InputMismatchException e){sc.next(); userInt = 0;}
            if(userInt == -1){
                //System.out.println(getName() + " out of options selects Attack!");
                return getDefaultAttack();
            }
            else if(userInt < 1 || userInt > specials.length){ System.out.println("Invalid Skill!");}
            else if(!specials[userInt-1].isUsableMp(this)){ //if not usable
                System.out.println("Insufficient MP!");
            }
            else break;
        }
        return specials[userInt-1];
    }

    Map<Integer, Integer> xpPerLevel = new LinkedHashMap<>();
    private int currentLevel = 1;
    private int experience = 1;
    private Integer reqXP = null;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.currentLevel = level;
    }

    public int getLevel() {
        return currentLevel;
    }

    public void gainXP(int amount) {
        checkCurrentXP();//check xp and level up
    }

    public Map<Integer, Integer> loadXpPerLevel() {

        try (Stream<String> lines = lines(Paths.get("xpPerLevel.txt"))) {
            lines.forEach(line -> xpPerLevel.put(currentLevel++, Integer.valueOf(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xpPerLevel;
    }

    public void levelUp() {
        System.out.println("   LEVELING UP  \n");
        System.out.println("NEW LEVEL ACQUIRED\n");
        System.out.println("You are on level " + (++currentLevel) + " Experience gained is " + reqXP.toString() + "!");
    }

    public void checkCurrentXP() {
        reqXP = xpPerLevel.get(currentLevel);
        while(experience < reqXP ){
            if (null !=reqXP ) {
                if (experience <= reqXP) {
                    levelUp();
                }
            }
            break;
        }

    }
}
