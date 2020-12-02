package sk.tuke.kpi.oop.game.characters;

public class Health {
    private int max;
    private int value;

    public Health(int health, int max){
        this.value=health;
        this.max=max;
    }
    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }
    public Health(int health){
        this.value=health;
        this.max=health;
    }

    public int getValue() {
        return value;
    }

    public void refill(int amount){
        this.value += amount;
        if(value>max)
            this.value = this.max;
    }
    public void restore(){
        this.value=this.max;
    }
    public void drain(int amount){
        this.value -=amount;
        if(amount<0)
            this.value=0;
    }


    public void onExhaustion(ExhaustionEffect effect){

    }
}
