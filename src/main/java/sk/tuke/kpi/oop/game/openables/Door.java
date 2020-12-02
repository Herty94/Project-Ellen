package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;

import sk.tuke.kpi.oop.game.items.Usable;




import static sk.tuke.kpi.gamelib.map.MapTile.Type.CLEAR;
import static sk.tuke.kpi.gamelib.map.MapTile.Type.WALL;

public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private boolean opened;
    private Animation anim;
    private MapTile upper;
    private MapTile bottom;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public Door(){
        this.anim= new Animation("sprites/vdoor.png",16,32,0.1f);
        anim.resetToFirstFrame();
        anim.pause();
        setAnimation(anim);
        this.opened = false;

    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        if(anim.getCurrentFrameIndex()!=0|| opened ==true)
            return;
        anim.setPlayMode(Animation.PlayMode.ONCE);
        anim.play();
        setWall(CLEAR);
        opened = true;
        getScene().getMessageBus().publish(DOOR_OPENED,this);
    }

    @Override
    public void close() {
        if(anim.getCurrentFrameIndex()!=3 || opened==false)
            return;
        anim.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        anim.play();
        setWall(WALL);
        opened=false;
        getScene().getMessageBus().publish(DOOR_CLOSED,this);
    }

    @Override
    public boolean isOpen() {
        return this.opened;
    }
    public void setTiles(){
        if(getScene()==null)
            return;
        System.out.println(this.getPosX()/16);
        upper = getScene().getMap().getTile(this.getPosX()/16,(this.getPosY()+16)/16);
        bottom = getScene().getMap().getTile(this.getPosX()/16,this.getPosY()/16);
        setWall(WALL);
    }
    private void setWall(MapTile.Type a){
            bottom.setType(a);
            upper.setType(a);
    }
    @Override
    public void useWith(Actor actor) {
        if(actor==null && opened==true)
            return;
        open();
    }
}
