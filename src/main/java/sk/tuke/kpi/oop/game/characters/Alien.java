package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Alien extends AbstractActor implements Movable {

    private final Animation anim;
    public Alien(){
        anim = new Animation("sprites/alien.png",32,32,0.1f, Animation.PlayMode.LOOP);
        setAnimation(anim);
    }
    @Override
    public int getSpeed() {
        return 1;
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
}
