package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor>{
    public Hammer() {
        super(1);
        super.setAnimation(new Animation("sprites/hammer.png"));

    }
    public Hammer(int uses) {
        super(uses);
        super.setAnimation(new Animation("sprites/hammer.png"));
    }

    @Override
    public void useWith(Reactor actor) {
        super.useWith(actor);
        actor.repair();
    }
}
