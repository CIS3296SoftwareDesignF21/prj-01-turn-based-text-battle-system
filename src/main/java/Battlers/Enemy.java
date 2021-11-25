package Battlers;
import Attacks.*;

public class Enemy extends Battler{

    public void addSpecials(){
        addSpecialAttack(new Bash());
    }

    public void addResistances(){
        setResistance(FIRE, RESIST);
        setResistance(ICE, WEAK);
        setResistance(HOLY, WEAK);
        setResistance(DARK, ABSORB);
    }

    public Enemy(){
        super();
        addSpecials();
        addResistances();
    }

    public Enemy(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def,
                     int CritRate, int HitRate, int EvaRate) {
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, CritRate, HitRate, EvaRate);
        addSpecials();
        addResistances();
    }

    public Enemy(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def);
        addSpecials();
        addResistances();
    }

    public Enemy(String name, int HP, int MP, int Atk, int Def){
        super(name, HP, MP, Atk, Def);
        addSpecials();
        addResistances();
    }

    public static Enemy randomEnemy(int level){
        int hp, mp, atk, def;
        hp = mp = atk = def = 0;
        Enemy newEnemy;
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
        String enemyName = String.format("Level %d enemy",level);
        newEnemy = new Enemy(enemyName,hp,mp,atk,def);
        return newEnemy;
    }


}
