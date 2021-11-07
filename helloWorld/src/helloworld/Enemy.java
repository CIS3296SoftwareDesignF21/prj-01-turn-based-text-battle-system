package helloworld;

public class Enemy extends Battler{

    public Enemy(){
        super();
    }

    public Enemy(String name, int HP,int MaxHP, int MP, int MaxMP, int Atk, int Def){
        super(name, HP, MaxHP, MP, MaxMP, Atk, Def);
    }


}
