package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {
    private int temperature;
    private int damage;
    private Animation normalAnimation;
    private Animation redAnimation;
    private Animation destroyedAnimation;
    private boolean state;

    public Reactor(){
        this.temperature = 0;
        this.damage = 0;
        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.15f, Animation.PlayMode.LOOP_PINGPONG);
        this.redAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.15f, Animation.PlayMode.LOOP_PINGPONG);
        this.destroyedAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(normalAnimation);
    }

    public int getTemperature() {
        return this.temperature;
    }
    public int getDamage() {
        return this.damage;
    }
    public void increaseTemperature(int increment){
        if(increment<0)
            return;
        this.temperature+=increment;
        if(this.temperature>6000)
            this.temperature=6000;
        if(this.damage>=33&& this.damage<=66)
            this.temperature+= Math.ceil(increment*0.5);
        else if(this.damage>66)
            this.temperature+= increment;

        int old_damage=this.damage;
        if(this.temperature>=2000)
            this.damage = (int)Math.ceil(100.0/4000.0*(this.temperature - 2000));
        if(this.damage<old_damage)
            this.damage = old_damage;
        updateAnimation();
    }
    public void decreaseTemperature(int decrement){
        if(decrement<0)
            return;
        if(this.damage==100)
            return;
        if(this.damage<50)
            this.temperature-=decrement;
        else
            this.temperature-=Math.ceil(decrement*0.5);
        updateAnimation();
    }
    public void turnOff(){

    }
    public void turnOn(){

    }
    private void updateAnimation(){
        float frameRate = 0.15f-(0.12f/100.0f*this.damage);
        if(this.damage>20) {
            //  redAnimation.setFrameDuration();
            this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, frameRate, Animation.PlayMode.LOOP_PINGPONG);
            this.redAnimation = new Animation("sprites/reactor_hot.png", 80, 80, frameRate, Animation.PlayMode.LOOP_PINGPONG);

        }
        if(this.temperature>4000)
            setAnimation(this.redAnimation);
        else
            setAnimation(this.normalAnimation);
        if(this.temperature>=6000)
            setAnimation(this.destroyedAnimation);
    }
}
