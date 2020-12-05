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
import sk.tuke.kpi.oop.game.characters.Ripley;

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
            if(ac instanceof Alive && !(ac instanceof Ripley))
                new When<>(()-> this.intersects(ac),
                    new Invoke<>(()->{
                        ((Alive) ac).getHealth().drain(30);
                        if(((Alive) ac).getHealth().getValue()!=0)
                        getScene().removeActor(this);
                    })).scheduleFor(this);
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
