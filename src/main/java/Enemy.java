package main.java;

import main.java.Attacks.*;

public class Enemy extends Battler{
    public Enemy(){
        super();
        addSpecialAttack(new Bash());
    }

    public Enemy(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def,
                     int CritRate, int HitRate, int EvaRate) {
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, CritRate, HitRate, EvaRate);
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
}
