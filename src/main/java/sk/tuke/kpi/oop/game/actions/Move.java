package sk.tuke.kpi.oop.game.actions;


import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;



public class Move<M extends Movable> implements Action<M> {

    private final float duration;
    private float duration_delta=0;
    private final Direction direction;
    private int x,y;
    private M actor;
    private boolean first =false;
    private boolean done =false;

    public Move(Direction direction, float duration) {
        this.direction=direction;
        this.duration=duration;
        this.actor = getActor();
        // implementacia konstruktora akcie
    }
    public Move(Direction direction){
        this.direction = direction;
        this.duration=0;
        this.actor = getActor();
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
        this.duration_delta=this.duration+1f;
    }

    @Override
    public void execute(float deltaTime) {
        if(!this.first) {
            actor.startedMoving(direction);
            this.first = false;
        }
        x=actor.getPosX();
        y= actor.getPosY();
        actor.setPosition(x+(direction.getDx()*actor.getSpeed()),y+(direction.getDy()* actor.getSpeed()));
        if(actor.getScene().getMap().intersectsWithWall(actor))
            actor.setPosition(x,y);
        this.duration_delta+=deltaTime;
        if((duration_delta-this.duration) >= 1e-5) {
            this.done = true;
            actor.stoppedMoving();
        }

    }

}
