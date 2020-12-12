package sk.tuke.kpi.oop.game.beginning.actors;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Switchable;

public class Button extends AbstractActor implements Switchable {

    private Animation on;
    private Animation off;
    private Scene scene;
    private boolean isOn;

    public static final Topic<Button> BUTTON_ON = Topic.create("buttton on", Button.class);
    public static final Topic<Button> BUTTON_OFF = Topic.create("button off", Button.class);
    public Button(){
        on = new Animation("sprites/button_green.png");
        off = new Animation("sprites/button_red.png");
        setAnimation(off);
        this.isOn = false;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.scene= scene;
        new Loop<>(new Invoke<>(()->{
            for (Actor actor : scene.getActors()) {
                if(this.intersects(actor) && actor!=this) {
                    turnOn();
                    break;
                }
                else if(scene.getActors().toArray()[scene.getActors().size()-1]==actor)
                    turnOff();
            }
        })).scheduleFor(this);
    }

    @Override
    public void turnOn() {
        this.isOn=true;
        setAnimation(on);
        if(scene!=null)
        scene.getMessageBus().publish(BUTTON_ON,this);
    }

    @Override
    public void turnOff() {
        this.isOn=false;
        setAnimation(off);
        if(scene!=null)
        scene.getMessageBus().publish(BUTTON_OFF,this);
    }

    @Override
    public boolean isOn() {
        return isOn;
    }
}
