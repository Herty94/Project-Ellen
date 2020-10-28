package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool extends AbstractActor implements Usable{
    private int remainingUses;
    int getRemainingUses(){
        return this.remainingUses;
    }
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }

    @Override
    public void useWith(Actor actor) {
        if(this.remainingUses<=0)
            return;
        if(this.remainingUses>0)
            this.remainingUses--;
        if(this.remainingUses<=0) {
            (getScene()).removeActor(this);
        }
    }
}
