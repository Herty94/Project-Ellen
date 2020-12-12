package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.beginning.actors.Pushable;

public class Push<P extends Pushable> extends AbstractAction<P> {

    private P actor;
    private Direction direction;
    private int tileNumbler;

    public Push(Direction direction,int tileNumber){
        this.direction=direction;
        this.tileNumbler=tileNumber;
    }

    @Override
    public void execute(float deltaTime) {
        if(getActor()==null||tileNumbler<=0) {
            setDone(true);
            return;
        }
        actor=getActor();
        int x = actor.getPosX();
        int y = actor.getPosY();

        actor.removeTile();
        actor.setPosition(actor.getPosX()+(16*direction.getDx()),actor.getPosY()+(16*direction.getDy()));
        if(actor.getScene().getMap().intersectsWithWall(actor))
            actor.setPosition(x,y);
        actor.setTiles();
        this.tileNumbler--;
    }

}
