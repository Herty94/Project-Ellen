package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends BreakableTool{
    public Hammer() {
        super(1     );
        setAnimation(new Animation("sprites/hammer.png"));

    }
    public Hammer(int uses) {
        super(uses);
        setAnimation(new Animation("sprites/hammer.png"));
    }
}
