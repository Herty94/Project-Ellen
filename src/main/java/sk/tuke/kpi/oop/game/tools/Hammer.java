package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor>{
    public Hammer() {
        super(1     );
        setAnimation(new Animation("sprites/hammer.png"));

    }
    public Hammer(int uses) {
        super(uses);
        setAnimation(new Animation("sprites/hammer.png"));
    }

    @Override
    public void useWith(Reactor actor) {

        if(super.remainingUses<=0||actor!=null)
            return;

        if(actor.repair())super.remainingUses--;
        else
            return;
        if(super.remainingUses<=0) {
            if(getScene()!=null)
                (getScene()).removeActor(this);
        }
    }
}
