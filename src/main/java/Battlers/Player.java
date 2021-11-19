package Battlers;
import Attacks.*;

import java.util.Scanner;

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
    }

    public Player(){
        super();
        addSpecials();
    }

    public Player(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def,
                  int CritRate, int HitRate, int EvaRate){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, CritRate, HitRate, EvaRate);
        addSpecials();
    }

    public Player(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def);
        addSpecials();
    }

    public Player(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
        addSpecials();
    }

    public static Player randomPlayer(int level, String name){
        int hp, mp, atk, def;
        hp = mp = atk = def = 0;
        Player newCharacter;
        if(level == 1){
            hp = Rates.rand(1000,2000);
            mp = Rates.rand(1,101);
            atk = Rates.rand(30,50);
            def = Rates.rand(50,60);
        } else if(level == 2){
            hp = Rates.rand(1500,2500);
            mp = Rates.rand(51,151);
            atk = Rates.rand(45,65);
            def = Rates.rand(55,65);
        } else if (level == 3){
            hp = Rates.rand(2000,3000);
            mp = Rates.rand(101,201);
            atk = Rates.rand(50,70);
            def = Rates.rand(60,70);
        } else {
            System.out.println("Level must be between one and three");
            System.exit(-1);
        }
        newCharacter = new Player(name,hp,mp,atk,def);
        return newCharacter;
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
            System.out.format("%-5s"," [" + specials[i].getMpCost() + "]"); //set this to %-6s if skills use triple digit MP
            System.out.print(usable + " ");
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
                //System.out.println(getName() + " out of options selects Attack!");
                return getDefaultAttack();
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
