package sk.tuke.kpi.oop.game.beginning.actors;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Body extends AbstractActor {


    public static final Topic<Body> BODY_HEALED = Topic.create("body healed",Body.class);
    public Body(){
        setAnimation(new Animation("sprites/body.png"));
    }

    public void heal(){
        setAnimation(new Animation("sprites/player.png",32,32));
        getScene().getMessageBus().publish(BODY_HEALED,this);
    }
}
