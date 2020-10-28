package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {


    public FireExtinguisher() {
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png"));

    }

    @Override
    public void useWith(Reactor actor) {
        if(super.remainingUses<=0||actor == null)
            return;
        if(actor.extinguish())super.remainingUses--;
        else
            return;
        if(super.remainingUses<=0) {
            if(getScene()!=null)
                (getScene()).removeActor(this);

        }
    }

}

