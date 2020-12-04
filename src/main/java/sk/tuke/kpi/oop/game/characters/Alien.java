package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Alien extends AbstractActor implements Movable,Enemy,Alive {
    private final Health health;
    private final Animation anim;
    public Alien(){
        anim = new Animation("sprites/alien.png",32,32,0.1f, Animation.PlayMode.LOOP);
        setAnimation(anim);
        this.health = new Health(100);
        health.onExhaustion(()->getScene().removeActor(this));
    }
    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::hurt)).scheduleFor(this);
    }
    private void hurt(){
        for(Actor act : getScene().getActors()){
            if(act instanceof Alive && !(act instanceof Enemy) &&this.intersects(act))
                ((Alive)act).getHealth().drain(1);
        }
    }
    @Override
    public void stoppedMoving() {
        anim.pause();
    }

    @Override
    public void startedMoving(Direction direction) {
        anim.setRotation(direction.getAngle());
        anim.play();
    }

    @Override
    public Health getHealth() {
        return health;
    }
}
