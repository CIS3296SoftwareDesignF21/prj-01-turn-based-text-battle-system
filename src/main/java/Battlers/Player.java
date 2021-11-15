package Battlers;
import Attacks.*;

public class Player extends Battler{

    public Player(){
        super();
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }

    public Player(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def,
                  int CritRate, int HitRate, int EvaRate){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def, CritRate, HitRate, EvaRate);
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }

    public Player(String name, int HP, int MaxHP, int MP, int MaxMP, int Atk, int Def){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def);
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }

    public Player(String name, int MaxHP, int MaxMP, int Atk, int Def){
        super(name, MaxHP, MaxMP, Atk, Def);
        addSpecialAttack(new DualSlash());
        addSpecialAttack(new Pierce());
    }
}
