package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;

public class Drop<K extends Keeper> extends AbstractAction<K> {

    private Scene scene;

    public Drop(){

    }

    @Override
    public void execute(float deltaTime) {
        if(getActor().getScene()==null) {
            setDone(true);
            return;
        }
        if(scene==null);
            this.scene = Objects.requireNonNull(getActor().getScene());
        Keeper kep = getActor();
        Collectible col = kep.getBackpack().peek();
        if(col==null){
            setDone(true);
        }


        try {
            kep.getBackpack().remove(Objects.requireNonNull(col));
            scene.addActor(col, kep.getPosX() + 8, kep.getPosY() + 8);
        }catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 100, 100).showFor(2);
        }
        setDone(true);
    }
}

