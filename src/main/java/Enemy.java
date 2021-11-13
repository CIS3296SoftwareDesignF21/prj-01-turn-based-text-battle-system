package main.java;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Battler{
    public Enemy(){
        super();
        addSpecialAttack(new Bash());
    }

    public Enemy(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def, int EvaRate) {
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, EvaRate);
        addSpecialAttack(new Bash());
    }

    public Enemy(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def);
        addSpecialAttack(new Bash());
    }

    public Enemy(String name, int HP, int MP, int Atk, int Def){
        super(name, HP, MP, Atk, Def);
        addSpecialAttack(new Bash());
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

//    public Enemy(int level, String name){
//        int hp, mp, atk, def;
//        if(level == 1){
//            hp = ThreadLocalRandom.current().nextInt(1000,2000);
//            mp = ThreadLocalRandom.current().nextInt(1,101);
//            atk = ThreadLocalRandom.current().nextInt(30,50);
//            def = ThreadLocalRandom.current().nextInt(50,60);
//            setStats(hp,mp,atk,def);
//            //    private Set<Attack> specialAttacks;
//        } else if(level == 2){
//
//        } else if (level == 3){
//
//        } else {
//            System.out.println("Level must be between one and three");
//            System.exit(-1);
//        }
//    }
//
//    //Used in generating a random enemy since we can't use the super constructor when we need to have multiple cases
//    public void setStats(int hp,int mp,int atk,int def){
//        super.setHP(hp);
//        super.setMaxHP(hp);
//        super.setMP(mp);
//        super.setMaxMP(mp);
//        super.setAtk(atk);
//        super.setDef(def);
//        super.setEvaRate(10);
//        super.setName("Level one enemy");
//        super.setGuard(false);
//        super.setCurrentAttack(new DefaultAttack());
//    }

}
