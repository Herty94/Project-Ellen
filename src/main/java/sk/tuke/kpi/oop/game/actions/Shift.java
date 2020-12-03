package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

import java.util.Objects;


public class Shift<K extends Keeper> extends AbstractAction<K> {

    private Scene scene;

    public Shift(){

    }

    @Override
    public void execute(float deltaTime) {
        if(getActor().getScene()==null) {
            setDone(true);
            return;
        }
        if(scene==null)
            this.scene = Objects.requireNonNull(getActor().getScene());
        try {
            getActor().getBackpack().shift();
        }catch(Exception ex){
            scene.getGame().getOverlay().drawText(ex.getMessage(),100,100).showFor(2);
            setDone(true);
        }
        setDone(true);
    }
}
