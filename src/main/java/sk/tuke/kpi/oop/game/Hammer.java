package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private int useNum;
    public Hammer() {
        setAnimation(new Animation("sprites/hammer.png"));
        this.useNum = 2;
    }
    public boolean use() {
        if(this.useNum<=0)
            return false;
        if(this.useNum>0)
            this.useNum--;
        if(this.useNum<=0) {
            (getScene()).removeActor(this);
        }
        return true;
    }
}
