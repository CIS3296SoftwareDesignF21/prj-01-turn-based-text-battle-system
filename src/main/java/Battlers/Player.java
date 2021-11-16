package Battlers;
import Attacks.*;

import java.util.concurrent.ThreadLocalRandom;

public class Player extends Battler{

    public void addSpecials(){
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
        /*addSpecialAttack(new Bash());
        addSpecialAttack(new VariantStrike());
        addSpecialAttack(new DesperateHit());
        addSpecialAttack(new Heal());*/
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
            hp = ThreadLocalRandom.current().nextInt(1000,2000);
            mp = ThreadLocalRandom.current().nextInt(1,101);
            atk = ThreadLocalRandom.current().nextInt(30,50);
            def = ThreadLocalRandom.current().nextInt(50,60);
        } else if(level == 2){
            hp = ThreadLocalRandom.current().nextInt(1500,2500);
            mp = ThreadLocalRandom.current().nextInt(51,151);
            atk = ThreadLocalRandom.current().nextInt(45,65);
            def = ThreadLocalRandom.current().nextInt(55,65);
        } else if (level == 3){
            hp = ThreadLocalRandom.current().nextInt(2000,3000);
            mp = ThreadLocalRandom.current().nextInt(101,201);
            atk = ThreadLocalRandom.current().nextInt(50,70);
            def = ThreadLocalRandom.current().nextInt(60,70);
        } else {
            System.out.println("Level must be between one and three");
            System.exit(-1);
        }
        newCharacter = new Player(name,hp,mp,atk,def);
        return newCharacter;
    }
}
