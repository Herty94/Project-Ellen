package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private final Switchable device;
    private boolean state;
    private final Animation animation;
    public PowerSwitch(Switchable device){
        this.device = device;
        animation = new Animation("sprites/switch.png");
        setAnimation(animation);
    }
    public void toggle(){
        this.state = !this.state;
        updateState();
    }

    public Switchable getDevice(){
       return this.device;
    }
    public void switchOn(){
        this.state = true;
        updateState();
    }
    public void switchOff(){
        this.state = false;
        updateState();
    }
    private void updateState(){
        if(device == null)
            return;
        if(this.state) {
            device.turnOn();
            animation.setTint(Color.WHITE);
        }
        else {
            device.turnOff();
            animation.setTint(Color.GRAY);
        }
    }

}
