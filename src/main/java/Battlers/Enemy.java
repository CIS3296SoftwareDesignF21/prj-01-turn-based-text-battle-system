package Battlers;
import Attacks.*;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Battler{

    public void addSpecials(){
        addSpecialAttack(new Bash());
    }

    public Enemy(){
        super();
        addSpecials();
    }

    public Enemy(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def,
                     int CritRate, int HitRate, int EvaRate) {
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, CritRate, HitRate, EvaRate);
        addSpecials();
    }

    public Enemy(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def);
        addSpecials();
    }

    public Enemy(String name, int HP, int MP, int Atk, int Def){
        super(name, HP, MP, Atk, Def);
        addSpecials();
    }

    public static Enemy randomEnemy(int level){
        int hp, mp, atk, def;
        hp = mp = atk = def = 0;
        Enemy newEnemy;
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
        String enemyName = String.format("Level %d enemy",level);
        newEnemy = new Enemy(enemyName,hp,mp,atk,def);
        return newEnemy;
    }


}
