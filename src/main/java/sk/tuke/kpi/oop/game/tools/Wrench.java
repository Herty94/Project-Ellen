package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Wrench extends BreakableTool<Wrench> {


    public Wrench(){
        super(2);
        setAnimation(new Animation("sprites/wrench.png"));
    }

}
