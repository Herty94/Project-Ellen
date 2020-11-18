package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.items.Usable;


public class Use <T extends Actor> extends AbstractAction<T> {
    private final Usable<T> actor;
    public Use(Usable<T> actor){
        this.actor = actor;
    }

    @Override
    public void execute(float deltaTime) {
        actor.useWith(getActor());
        setDone(true);
    }
}
