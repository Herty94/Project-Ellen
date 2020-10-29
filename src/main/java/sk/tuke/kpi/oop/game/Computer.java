package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Computer extends AbstractActor implements EnergyConsumer{
    private boolean power;
    private final Animation animation;
    public Computer(){
        this.power = false;
        animation = new Animation("sprites/computer.png", 240/3, 48, 0.2f, Animation.PlayMode.LOOP);
        animation.pause();
        setAnimation(animation);
    }
    public int add(int a, int b){
        if(power)
            return a+b;
        return 0;
    }
    public float add(float a, float b) {
        if(power)
            return a+b;
        return 0f;
    }
    public int sub(int a, int b){
        if(power)
            return a-b;
        return 0;
    }
    public float sub(float a, float b){
        if(power)
            return a+b;
        return 0f;

    }
    private void updateAnimation(){
        if(this.power) {
            animation.play();

        }
        else
            animation.pause();
    }


    @Override
    public void setPowered(boolean power) {
        this.power = power;
        updateAnimation();
    }
}
