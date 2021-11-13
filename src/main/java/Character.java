package main.java;
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
}
