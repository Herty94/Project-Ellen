package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop extends AbstractAction<Keeper> {

    private Scene scene;

    public Drop(Scene scene){
        this.scene = scene;
    }

    @Override
    public void execute(float deltaTime) {
        Keeper kep = getActor();
        Collectible col = kep.getBackpack().peek();
        if(col==null)
            return;
        kep.getBackpack().remove(col);
        scene.addActor(col,kep.getPosX()+8,kep.getPosY()+8);
        setDone(true);
    }
}

