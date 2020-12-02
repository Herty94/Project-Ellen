package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;



public class Shift<K extends Keeper> extends AbstractAction<K> {

    private Scene scene;

    public Shift(Scene scene){
        this.scene = scene;
    }

    @Override
    public void execute(float deltaTime) {
        try {
            getActor().getBackpack().shift();
        }catch(Exception ex){
            scene.getGame().getOverlay().drawText(ex.getMessage(),100,100).showFor(2);
        }
        setDone(true);
    }
}
