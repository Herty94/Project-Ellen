package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;


public class Ventilator extends AbstractActor implements Repairable{
    private boolean rep;
    private final Animation anim;

    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);


    public Ventilator(){
        rep =false;
        anim = new Animation("sprites/ventilator.png",32,32,0.1f, Animation.PlayMode.LOOP);
        anim.pause();
        setAnimation(anim);

    }
    @Override
    public boolean repair() {
        if(rep)
            return false;
        System.out.println("repaird");
        anim.play();
        rep = true;
        getScene().getMessageBus().publish(VENTILATOR_REPAIRED,this);
        return true;
    }

    public boolean isRep() {
        return rep;
    }
}
