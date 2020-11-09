package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class Ripley extends AbstractActor implements Movable {
    private int movingSpeed;
    private Animation animation;
    public Ripley(){
        super("Ellen");
        this.movingSpeed = 2;
        animation =new Animation("sprites/player.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        animation.pause();
        setAnimation(animation);
    }

    @Override
    public int getSpeed() {
        return this.movingSpeed;
    }

    @Override
    public void startedMoving(Direction direction) {
        animation.setRotation(direction.getAngle());
        animation.play();

    }

    @Override
    public void stoppedMoving() {
        animation.pause();
    }
}
