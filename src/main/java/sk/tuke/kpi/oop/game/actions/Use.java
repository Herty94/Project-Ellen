package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.items.Usable;


public class Use <U extends Usable<T>,T extends Actor> extends AbstractAction<T> {
    private final U actor;
    public Use(U actor){
        this.actor = actor;
    }

    @Override
    public void execute(float deltaTime) {
        actor.useWith(getActor());
        setDone(true);
    }
    public Disposable scheduleForIntersectingWith(Actor mediatingActor) {
        if(actor==null)
             return null;
        Scene scene = mediatingActor.getScene();
        if (scene == null) return null;
        Class<T> usingActorClass = actor.getUsingActorClass();  // `usable` je spominana clenska premenna
        return scene.getActors().stream()  // ziskame stream aktérov na scene
            .filter(mediatingActor::intersects)  // vyfiltrujeme akterov, ktori su v kolizii so sprostredkovatelom
            .filter(usingActorClass::isInstance) // vyfiltrujeme akterov kompatibilneho typu
            .map(usingActorClass::cast)  // vykoname pretypovanie streamu akterov
            .findFirst()
            .map(this::scheduleFor)
            .orElse(null);
    }
}
