package sk.tuke.kpi.oop.game.beginning.actors;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.oop.game.beginning.Pushable;

public class Barrel extends AbstractActor implements Pushable {

    private MapTile tile;
    private Scene scene;
    public Barrel(){
        setAnimation(new Animation("sprites/barrel.png"));
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.scene=scene;
        setTiles();
    }

    @Override
    public void setTiles() {
        if(scene==null)
            return;
        tile = scene.getMap().getTile(this.getPosX()/16,this.getPosY()/16);
        tile.setType(MapTile.Type.WALL);
    }

    @Override
    public void removeTile() {
        tile.setType(MapTile.Type.CLEAR);
    }
}
