package sk.tuke.kpi.oop.game.beginning.actors;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Electricity extends AbstractActor {

    private Animation ele;
    public Electricity(){
        ele = new Animation("sprites/electricity.png",16,48,0.1f, Animation.PlayMode.LOOP);
        setAnimation(ele);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        subscribe(scene);
    }
    private void subscribe(Scene scene){
        scene.getMessageBus().subscribe(Button.BUTTON_OFF, butt ->{
          draining(scene);
          ele.play();

        });
        scene.getMessageBus().subscribe(Button.BUTTON_ON, butt ->{
            ele.resetToFirstFrame();
            ele.stop();
            });
    }
    private void draining(Scene scene){
        for (Actor actor : scene.getActors()) {
            if(actor.intersects(this) &&actor instanceof Alive) {
                ((Alive) actor).getHealth().drain(30);
            }

        }
    }
}

