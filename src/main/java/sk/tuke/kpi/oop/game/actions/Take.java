package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.beginning.Wearable;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;
import java.util.Objects;


public class Take<K extends Keeper>  extends AbstractAction<K> {
    private Scene scene;

    public Take(){
    }
    @Override
    public void execute(float deltaTime) {
        K kep;
        if(getActor()==null||getActor().getScene()==null) {
            setDone(true);
            return;
        }
        if(scene==null)
            this.scene = Objects.requireNonNull(getActor().getScene());
        kep = getActor();
        Collectible col = findCol(kep);
        System.out.println("Take action");
        if(col==null) {
            setDone(true);
            return;
        }

        try {
            kep.getBackpack().add(col);

        } catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 100, 100).showFor(2);
            setDone(true);
            return;
        }
        if(col instanceof Wearable) {
            ((Wearable) col).wearTop(kep);
            System.out.println("Take weared");
            setDone(true);
        }
        else
            scene.removeActor(col);
        setDone(true);
    }
    private Collectible findCol(K kep){
        List<Actor> list = scene.getActors();
        Collectible col;
        for (Actor s : list) {
            if ((kep.intersects(s) && s instanceof Collectible)&& s!=kep.getBackpack().peek()) {
                col = (Collectible)s;
                return col;
            }
        }
        return null;
    }
}
