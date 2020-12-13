package sk.tuke.kpi.oop.game.beginning.actors;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.oop.game.beginning.Destroyable;

import java.util.ArrayList;
import java.util.List;

public class Blockade extends AbstractActor implements Destroyable {

    private List<MapTile> tiles;

    public Blockade(){
        setAnimation(new Animation("sprites/blockade.png"));
        tiles=new ArrayList<>();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        for (int i =0; i<8;i++){
            System.out.println("Blockade  count: "+(i/4));
            tiles.add(scene.getMap().getTile((this.getPosX()+(16*(i/4)))/16,(this.getPosY()+(16*(i%4)))/16));
        }
        System.out.println("Blockade :"+tiles.size());
        tiles.forEach(tile->tile.setType(MapTile.Type.WALL));
    }

    @Override
    public void destroy() {
        tiles.forEach(tile->tile.setType(MapTile.Type.CLEAR));
        getScene().removeActor(this);
    }
}
