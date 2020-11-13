package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Usable;


public class Use <A extends Usable<T>,T extends Actor> extends AbstractAction<T> {
    private A actor;
    public Use(A actor){
        this.actor = actor;
    }

    @Override
    public void execute(float deltaTime) {
        actor.useWith(getActor());
        setDone(true);
    }
}
