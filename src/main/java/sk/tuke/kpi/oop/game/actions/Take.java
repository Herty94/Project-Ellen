package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;


import java.util.List;


public class Take<K extends Keeper>  extends AbstractAction<K> {
    private final Scene scene;
    private Keeper kep;

    public Take(Scene scene){
        this.scene = scene;
    }
    @Override
    public void execute(float deltaTime) {
        kep = getActor();
        List<Actor> list = scene.getActors();
        Collectible col = null;
        for (Actor s : list) {
            if ((kep.intersects(s) && s instanceof Collectible)) {
                col = (Collectible)s;
            }
        }

        if(col==null)
            return;
        try {
            kep.getBackpack().add(col);
        } catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 100, 100).showFor(2);
        }
        scene.removeActor(col);
        setDone(true);
    }
}
