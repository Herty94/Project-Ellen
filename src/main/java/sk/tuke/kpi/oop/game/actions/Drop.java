package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<K extends Keeper> extends AbstractAction<K> {

    private final Scene scene;

    public Drop(Scene scene){
        this.scene = scene;
    }

    @Override
    public void execute(float deltaTime) {
        Keeper kep = getActor();
        Collectible col = kep.getBackpack().peek();
        if(col==null){
            setDone(true);
        }


        System.out.println("hfdsafaha");
        try {
            kep.getBackpack().remove(col);
            scene.addActor(col, kep.getPosX() + 8, kep.getPosY() + 8);
        }catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 100, 100).showFor(2);
        }
        setDone(true);
    }
}

