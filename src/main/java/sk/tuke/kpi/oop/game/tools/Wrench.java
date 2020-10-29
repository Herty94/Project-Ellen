package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;


public class Wrench extends BreakableTool<DefectiveLight>{

    private int remainingUses;

    public Wrench(){
        super(2);
        this.remainingUses=2;
        setAnimation(new Animation("sprites/wrench.png"));
    }

    @Override
    public void useWith(DefectiveLight actor) {
        super.useWith(actor);
        actor.repair();
    }
}
