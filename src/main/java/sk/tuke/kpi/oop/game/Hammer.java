package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    public Hammer() {
        setAnimation(new Animation("sprites/hammer.png"));

    }
    public int use() {
        return 0;
    }
}
