package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move implements Action<Movable> {

    private float duration;
    private float duration_delta=0;
    private Direction direction;
    private Movable actor;
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

    @Override
    public @Nullable Movable getActor() {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable Movable actor) {
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
        actor.setPosition(actor.getPosX()+(direction.getDx()*actor.getSpeed()),actor.getPosY()+(direction.getDy()* actor.getSpeed()));
        this.duration_delta+=deltaTime;

        if((duration_delta-this.duration)>= 1e-5) {
            this.done = true;
            actor.stoppedMoving();
        }

    }

}
