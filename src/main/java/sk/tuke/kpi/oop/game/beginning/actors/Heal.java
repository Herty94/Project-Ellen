package sk.tuke.kpi.oop.game.beginning.actors;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Usable;

public class Heal extends AbstractActor implements Usable<Body> {


    public Heal(){
        setAnimation(new Animation("sprites/heal.png"));
    }

    @Override
    public void useWith(Body actor) {
        if(actor!=null)
            actor.heal();
    }

    @Override
    public Class<Body> getUsingActorClass() {
        return Body.class;
    }
}
