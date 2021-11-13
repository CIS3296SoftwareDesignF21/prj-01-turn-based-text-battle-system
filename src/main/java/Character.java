package main.java;

import java.util.concurrent.ThreadLocalRandom;

public class Character extends Battler{

    public Character(){
        super();
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }

    public Character(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def, int EvaRate){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, EvaRate);
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }

    public Character(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def);
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }

    public Character(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }

    public static Character randomCharacter(int level, String name){
        int hp, mp, atk, def;
        hp = mp = atk = def = 0;
        Character newCharacter;
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
        newCharacter = new Character(name,hp,mp,atk,def);
        return newCharacter;
    }

}
