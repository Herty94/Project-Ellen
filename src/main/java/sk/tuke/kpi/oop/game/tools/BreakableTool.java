package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A>{
    protected int remainingUses;
    public int getRemainingUses(){
        return this.remainingUses;
    }
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }

}
