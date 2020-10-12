package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private int useNum;
    public Hammer() {
        setAnimation(new Animation("sprites/hammer.png"));
        this.useNum = 3;
    }
    public boolean use() {
        if(this.useNum>0)
            useNum--;
        if(this.useNum<=0) {
            (getScene()).removeActor(this);
            return false;
        }
        return true;
    }
}
