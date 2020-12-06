package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;


public class Bullet extends AbstractActor implements Fireable{
    private final Animation anim;
    public Bullet(){
        this.anim = new Animation("sprites/bullet.png");
        setAnimation(anim);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        for(Actor ac : scene.getActors())
            if(ac instanceof Alive ) {
                new When<>(()-> this.intersects(ac),
                    new Invoke<>(()->{
                        getScene().removeActor(this);
                        ((Alive) ac).getHealth().drain(30);

                    })).scheduleFor(this);
            }
    }

    @Override
    public int getSpeed() {
        return 4;
    }

    @Override
    public void startedMoving(Direction direction) {
        anim.setRotation(direction.getAngle());
    }

    @Override
    public void collidedWithWall() {
        if(getScene()!=null)
            getScene().removeActor(this);
    }
}
