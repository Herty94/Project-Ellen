package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private final int max;
    private int value;
    private final List<ExhaustionEffect> list;
    public Health(int health, int max){
        this.value=health;
        this.max=max;
        this.list = new ArrayList<>();
    }
    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }
    public Health(int health){
        this.value=health;
        this.max=health;
        this.list = new ArrayList<>();
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
        if(value>0)
           this.value -=amount;
        if(value<=0) {
            this.value = 0;
            exhaust();
        }
    }
    public void exhaust(){
        this.value = 0;
        list.forEach(ExhaustionEffect::apply);
        list.removeAll(list);
    }

    public void onExhaustion(ExhaustionEffect effect){
        list.add(effect);
    }
}
