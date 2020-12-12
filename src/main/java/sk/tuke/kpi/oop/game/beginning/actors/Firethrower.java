package sk.tuke.kpi.oop.game.beginning.actors;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Usable;

public class Firethrower extends AbstractActor implements Wearable, Usable<Ripley> {

    private int fuel;
    private Animation anim;
    private Flames flames;
    private Animation animMoving;
    private Disposable dis;
    private boolean weared;
    public static final Topic<Firethrower> FLAME_PICKET = Topic.create("picket",Firethrower.class);
    public Firethrower(){
        this.anim=new Animation("sprites/flamethrowers.png");
        this.animMoving = new Animation("sprites/flamethrower.png",32,32);
        animMoving.stop();
        setAnimation(anim);
        this.fuel = 500;
        flames = new Flames();
        this.weared = false;
    }

    @Override
    public void wearTop(Keeper actor) {
        System.out.println("wearTop started");
        getScene().getMessageBus().publish(FLAME_PICKET,this);
        this.weared=true;
        setAnimation(animMoving);
        if(actor.getBackpack().peek()==this)
            actor.getScene().addActor(this,actor.getPosX(),actor.getPosY());
        new ActionSequence<>(
        new While<>(()->actor.getBackpack().peek()==this,
        new Invoke<>(() -> {
            animMoving.setRotation(actor.getAnimation().getRotation());
            this.setPosition(actor.getPosX(),actor.getPosY());
            actor.getScene().getGame().getOverlay().drawText("Fuel: "+this.fuel,15,  actor.getScene().getGame().getWindowSetup().getHeight()- GameApplication.STATUS_LINE_OFFSET*4);
        })),new When<>(()->actor.getBackpack().peek()!=this,new Invoke<>(()->{actor.getScene().removeActor(this);
            System.out.println("wearTop actor removed");
        }))).scheduleFor(actor);
    }

    @Override
    public void useWith(Ripley actor) {
        dis = new Loop<>(new Invoke<>(()->{
            if(fuel>0) {
                this.fuel--;
                flames.getAnimation().setRotation(actor.getAnimation().getRotation());
                actor.getScene().addActor(flames,actor.getPosX()+(Direction.fromAngle(actor.getAnimation().getRotation()).getDx()*72),actor.getPosY()+(Direction.fromAngle(actor.getAnimation().getRotation()).getDy()*72)-80/2);
                flames.getAnimation().play();

                    for (Actor actor1 : actor.getScene().getActors()) {
                        if(actor1 instanceof Alive && actor1.intersects(flames)&&!actor.getScene().getMap().intersectsWithWall(flames))
                            ((Alive) actor1).getHealth().drain(5);
                        if(actor1 instanceof Destroyable&& actor1.intersects(flames))
                            ((Destroyable)actor1).destroy();
                }
            }
        })).scheduleFor(actor);
    }
    public void stop(){
        if(dis!=null)
        dis.dispose();
        getScene().removeActor(flames);
    }

    @Override
    public boolean isWeared() {
        return this.weared;
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
