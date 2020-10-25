package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool extends AbstractActor{
    private int remainingUses;
    int getRemainingUses(){
        return this.remainingUses;
    }
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }
    public boolean use(){
        if(this.remainingUses<=0)
            return false;
        if(this.remainingUses>0)
            this.remainingUses--;
        if(this.remainingUses<=0) {
           (getScene()).removeActor(this);
        }
        return true;
    }
}
