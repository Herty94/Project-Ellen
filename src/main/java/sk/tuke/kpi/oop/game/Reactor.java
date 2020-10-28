package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;


import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable,Repairable{
    private int temperature;
    private int damage;
    private final Animation normalAnimation;
    private final Animation redAnimation;
    private final Animation destroyedAnimation;
    private final Animation offAnimation;
    private final Animation extinguished;
    private boolean state;
    private boolean destroyed;
    private Set<EnergyConsumer> devices;

    public Reactor(){
        this.temperature = 0;
        this.damage = 0;
        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.15f, Animation.PlayMode.LOOP_PINGPONG);
        this.redAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.15f, Animation.PlayMode.LOOP_PINGPONG);
        this.destroyedAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP);
        this.offAnimation = new Animation("sprites/reactor.png");
        this.extinguished = new Animation("sprites/reactor_extinguished.png");
        setAnimation(offAnimation);
        this.state = false;
        this.destroyed = false;
        devices = new HashSet<>();
    }

    public int getTemperature() {
        return this.temperature;
    }
    public int getDamage() {
        return this.damage;
    }
    public void increaseTemperature(int increment){
        if(!this.state)
            return;
        if(increment<0)
            return;
        this.temperature+=increment;

        if(this.damage>=33&& this.damage<=66)
            this.temperature+= Math.ceil(increment*0.5);
        else if(this.damage>66)
            this.temperature+= increment;

        int old_damage=this.damage;
        if(this.temperature>6000)
            this.temperature=6000;
        if(this.temperature>=2000)
            this.damage = (int)Math.ceil(100.0/4000.0*(this.temperature - 2000));
        if(this.damage<old_damage)
            this.damage = old_damage;
        updateAnimation();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

    public void decreaseTemperature(int decrement){
        if(!this.state)
            return;

        if(decrement<0)
            return;
        if(this.damage==100)
            return;
        if(this.damage<50)
            this.temperature-=decrement;
        else
            this.temperature-=Math.ceil(decrement*0.5);
        updateAnimation();
        if(this.temperature<=0)
            this.temperature = 0;
    }
    @Override
    public void turnOff(){
        this.state= false;
        updateAnimation();
    }
    @Override
    public void turnOn(){
        this.state = true;
        updateAnimation();
    }
    public boolean extinguish(){
        if(this.damage<=0)
            return false;
        if(this.temperature>4000)
            this.temperature= 4000;
        setAnimation(extinguished);
        return true;
    }


    @Override
    public boolean repair(){
        int pom = 0;
        if(this.damage<=0)
            return false;
        destroyed = false;
        if(this.damage<=50) {
            pom = 2000/50*(50-this.damage);
            this.damage = 0;

        }
        else
            this.damage-=50;
        this.temperature = 4000/100*this.damage+2000-pom;
        updateAnimation();
        return true;
    }
    public void addDevice(EnergyConsumer device){
        if(device== null)
            return;
        this.devices.add(device);
        updateAnimation();
    }
    public void removeDevice(EnergyConsumer device){
        if(devices == null)
            return;
        this.devices.remove(device);
        updateAnimation();
    }
    private void updateAnimation(){
        if(!this.state){
            if(!destroyed)
                setAnimation(offAnimation);
            if(devices != null)
                devices.forEach(energyConsumer -> energyConsumer.setPowered(false));
            return;
        }
        else
            if(devices != null)
                devices.forEach(energyConsumer -> energyConsumer.setPowered(true));
        float frameRate = 0.15f-(0.12f/100.0f*this.damage);
        if(this.damage>20) {
            //  redAnimation.setFrameDuration();
            this.normalAnimation.setFrameDuration(frameRate);
            this.redAnimation.setFrameDuration(frameRate);

        }
        if(this.temperature>4000)
            setAnimation(this.redAnimation);
        else
            setAnimation(this.normalAnimation);
        if(this.temperature>=6000) {
            this.destroyed = true;
            setAnimation(this.destroyedAnimation);
            this.state = false;
        }

    }


}
