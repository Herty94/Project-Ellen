package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;

import sk.tuke.kpi.oop.game.items.Usable;




import static sk.tuke.kpi.gamelib.map.MapTile.Type.CLEAR;
import static sk.tuke.kpi.gamelib.map.MapTile.Type.WALL;

public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private boolean opened;
    private final Animation anim;
    private MapTile upper;
    private Orientation or;
    private MapTile bottom;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public Door(String name,Orientation or){
        super(name);
        this.or=or;
        if(or.equals(Orientation.HORIZONTAL))
            this.anim= new Animation("sprites/hdoor.png",32,16,0.1f);
        else if(or.equals(Orientation.VERTICAL))
            this.anim= new Animation("sprites/vdoor.png",16,32,0.1f);
        else
            this.anim =new Animation("sprites/vdoor.png",16,32,0.1f);
        anim.resetToFirstFrame();
        anim.pause();
        setAnimation(anim);
        this.opened = false;

    }
    public enum Orientation{
        HORIZONTAL,
        VERTICAL
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        System.out.println(this.getPosX()/16);
        if(or.equals(Orientation.VERTICAL)) {
            upper = scene.getMap().getTile(this.getPosX() / 16, (this.getPosY() + 16) / 16);
            bottom = scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16);
        }
        else {
            upper = scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16);
            bottom = scene.getMap().getTile((this.getPosX() + 16) / 16,this.getPosY() / 16 );
        }
        setWall(WALL);
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        if(anim.getCurrentFrameIndex()!=0|| opened)
            return;
        anim.setPlayMode(Animation.PlayMode.ONCE);
        anim.play();
        setWall(CLEAR);
        opened = true;
        getScene().getMessageBus().publish(DOOR_OPENED,this);
    }

    @Override
    public void close() {
        if(anim.getCurrentFrameIndex()!=3 || !opened)
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
    private void setWall(MapTile.Type a){
            bottom.setType(a);
            upper.setType(a);
    }
    @Override
    public void useWith(Actor actor) {
        if(actor==null)
            return;
        if(!opened) {
            open();
            opened=!opened;
        }
        else
            close();
            opened=!opened;
    }
}
