package sk.tuke.kpi.oop.game.actions;


import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;


public class Move<M extends Movable> implements Action<M> {

    private final float duration;
    private float duration_delta;
    private final Direction direction;
    private M actor;
    private boolean first;
    private boolean done;


    public Move(Direction direction, float duration) {
        this.direction=direction;
        this.duration=duration;
        this.duration_delta=0;
        this.done=false;
        this.first = true;
        // implementacia konstruktora akcie
    }
    public Move(Direction direction){
        this.direction = direction;
        this.duration=0;
    }


    public Direction getDirection(){
        return this.direction;
    }

    @Override
    public @Nullable M getActor() {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable M actor) {
        this.actor= actor;
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public void reset() {
        this.done = false;
        this.first = false;
        this.duration_delta=0;
    }

    public void stop(){
        if(actor!=null){
            Objects.requireNonNull(actor).stoppedMoving();
        }
        this.done = true;
    }

    @Override
    public void execute(float deltaTime) {
        if(direction == Direction.NONE){
            stop();
            return;
        }
        actor=Objects.requireNonNull(getActor());
        if(this.first) {
            actor.startedMoving(direction);
            this.first = false;
        }
        int x,y;
        x=actor.getPosX();
        y= actor.getPosY();
        actor.setPosition(x+(direction.getDx()*actor.getSpeed()),y+(direction.getDy()* actor.getSpeed()));
        if(actor.getScene().getMap().intersectsWithWall(actor)){
            actor.setPosition(x,y);
            actor.collidedWithWall();
        }

        this.duration_delta+=deltaTime;
        if((duration_delta-this.duration) >= 1e-5) {
            stop();
            return;
        }

    }

}
