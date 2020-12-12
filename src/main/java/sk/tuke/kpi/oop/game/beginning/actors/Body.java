package sk.tuke.kpi.oop.game.beginning.actors;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Body extends AbstractActor {

    public Body(){
        setAnimation(new Animation("sprites/body.png"));
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {

    }
    public void heal(){

    }
}
