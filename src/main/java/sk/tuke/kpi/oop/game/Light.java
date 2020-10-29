package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable,EnergyConsumer{

    private boolean electricity;
    private boolean state;
    private final Animation onLight;
    private final Animation offLight;



    public Light(){
        this.state = false;
        this.electricity = false;
        onLight = new Animation("sprites/light_on.png");
        offLight = new Animation("sprites/light_off.png");
        updateAnimation();
    }

    @Override
    public void turnOn(){
        this.state = true;
        updateAnimation();
    }
    @Override
    public void turnOff(){
        this.state = false;
        updateAnimation();
    }

    public void toggle(){
        this.state = !this.state;
        updateAnimation();
    }

    private void updateAnimation() {
        if(!this.state || !electricity)
            setAnimation(offLight);
        else
            setAnimation(onLight);
    }

    @Override
    public void setPowered(boolean electricity){
        this.electricity = electricity;
        updateAnimation();
    }

}
