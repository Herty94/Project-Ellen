package sk.tuke.kpi.oop.game.beginning.actors;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.BreakableTool;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Heal extends BreakableTool<Body> implements Collectible {


    public Heal(){
        super(1);
        setAnimation(new Animation("sprites/heal.png"));
    }

    @Override
    public void useWith(Body actor) {
        if(actor!=null)
            actor.heal();
        super.useWith(actor);
    }

    @Override
    public Class<Body> getUsingActorClass() {
        return Body.class;
    }
}
