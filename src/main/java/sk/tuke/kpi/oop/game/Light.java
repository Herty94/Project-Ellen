package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor{

    private Reactor reactor;
    private boolean electricity;
    private boolean onState;
    private Animation onLight;
    private Animation offLight;

    public Light(Reactor reactor){
        this.reactor = reactor;
        this.onState = false;
        this.electricity = false;
        onLight = new Animation("sprites/light_on.png");
        offLight = new Animation("sprites/light_off.png");
        updateAnimation();
    }

    public void toggle(){
        this.onState = !this.onState;
        updateAnimation();
    }

    private void updateAnimation() {
        if(!this.onState || !electricity || !reactor.isRunning())
            setAnimation(offLight);
        else
            setAnimation(onLight);
    }

    public void setElectricityFlow(boolean electricity){
        this.electricity = electricity;
        updateAnimation();
    }
}
