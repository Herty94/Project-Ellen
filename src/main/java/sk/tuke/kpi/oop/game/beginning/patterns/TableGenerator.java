package sk.tuke.kpi.oop.game.beginning.patterns;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public final class TableGenerator extends  AbstractActor {
    public TableGenerator(Table table, Scene scene){
        setAnimation(table.getAnim());
        scene.addActor(this, scene.getGame().getWindowSetup().getWidth() / 2, scene.getGame().getWindowSetup().getHeight() / 2);
    }
}
