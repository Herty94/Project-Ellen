package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;

public class Bullet extends AbstractActor implements Fireable{
    private final Animation anim;
    public Bullet(){
        this.anim = new Animation("sprites/bullet.png");
        setAnimation(anim);
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
