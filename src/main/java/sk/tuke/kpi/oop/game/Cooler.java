package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor {

    private boolean state;
    private Reactor reactor;
    private Animation fanAnimation;
    public Cooler(Reactor reactor){
        this.reactor = reactor;
        fanAnimation = new Animation("sprites/fan.png", 32, 32, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(fanAnimation);
        this.state = false;
        fanAnimation.pause();
    }

    public void turnOn(){
        fanAnimation.play();
        this.state = true;
    }
    public void turnOff(){
        fanAnimation.pause();
        this.state = false;
    }
    public boolean isOn(){
        return this.state;
    }
    private void coolReactor(){
        if(this.state)
            reactor.decreaseTemperature(1);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
