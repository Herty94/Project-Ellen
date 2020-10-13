package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends AbstractActor {

    private int useNum;

    public FireExtinguisher(){
        this.useNum =1;
        setAnimation(new Animation("sprites/extinguisher.png"));
    }
    public boolean use(){
        if(this.useNum<=0)
            return false;
        if(this.useNum>0)
            this.useNum--;
        if(this.useNum<=0) {
            (getScene()).removeActor(this);
        }
        return true;
    }

    public int getUseNum() {
        return this.useNum;
    }
}

