package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {

    private int remainingUses;

    public FireExtinguisher() {
        super(1);
        this.remainingUses = 1;
        setAnimation(new Animation("sprites/extinguisher.png"));

    }

    @Override
    public void useWith(Reactor actor) {
        super.useWith(actor);
        actor.extinguish();
    }

}

