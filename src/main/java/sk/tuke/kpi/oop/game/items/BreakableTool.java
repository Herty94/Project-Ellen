package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Color;


public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A>{
    private int remainingUses;
    public int getRemainingUses(){
        return this.remainingUses;
    }
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }

    @Override
    public void useWith(A actor) {
            if(this.remainingUses<=0||actor == null)
                return;
            this.remainingUses--;
            if(this.remainingUses<=0&&getScene()!=null) {
                    (getScene()).removeActor(this);
                    this.getAnimation().setTint(Color.GRAY);
            }
    }

}
