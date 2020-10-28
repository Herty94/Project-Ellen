package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

    private final float time;
    Animation activatedAn;
    private boolean activated;
    Animation exp;
    Animation nonAn;
    public TimeBomb(float time){
        this.time = time;
        this.activatedAn = new Animation("sprites/bomb_activated.png", 16, 16, 0.1f, Animation.PlayMode.LOOP);
        this.exp = new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
        this.nonAn = new Animation("sprites/bomb.png");
        setAnimation(nonAn);
        this.activated = false;
    }
    public void activate(){
        this.activated = true;
        setAnimation(activatedAn);
        new ActionSequence<>(
            new Wait<>(time),
            new Invoke<>(() -> setAnimation(exp)),
            new When<>(
            () -> exp.getCurrentFrameIndex()==7,
            new Invoke<>(() ->
                (getScene()).removeActor(this)))

        ).scheduleFor(this);
    }
    public boolean isActivated(){
        return this.activated;
    }
}
