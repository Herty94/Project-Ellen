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
        if(actor == null)
            return;
        if(super.remainingUses<=0)
            return;

            actor.extinguish();
            super.remainingUses--;

        if(super.remainingUses<=0) {
            if(getScene()!=null)
                (getScene()).removeActor(this);

        }
    }

}

