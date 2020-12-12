package sk.tuke.kpi.oop.game.beginning.actors;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.awt.geom.Ellipse2D;

public class Ghost extends AbstractActor{
    private Ripley ripley;
    private Animation anim;
    private int radius;

    public Ghost(){
        anim = new Animation("sprites/ghost.png",32,32,0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(anim);
        anim.stop();
        radius = 150;
    }
    private void chase(){
        if(this.ripley== null)
            return;
        anim.play();
        if(this.intersects(ripley))
            ripley.getHealth().drain(2);
        Direction direction = Direction.NONE;
        int hx,hy,px,py;

        hx = this.getPosX();
        hy = this.getPosY();
        px= hx<ripley.getPosX()?1:-1;
        py= hy<ripley.getPosY()?1:-1;
        px= hx==ripley.getPosX()?0:px;
        py= hy==ripley.getPosY()?0:py;
        for (Direction value : Direction.values()) {
            if (value.getDx() == px&& value.getDy()==py) {
                direction = value;
               break;
            }
        }
        anim.setRotation(direction.getAngle());
        this.setPosition(hx+px,hy+py);

    }
    private void stay(){
        anim.stop();
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        Ellipse2D.Float a = new Ellipse2D.Float();
        scene.getActors().forEach(actor-> {
            if (actor instanceof Ripley)
                this.ripley=(Ripley) actor;
        });
        new Loop<>(new ActionSequence<>(new Invoke<>(()->{
            if(this.intersects(scene.getFirstActorByType(Ventilator.class))&&scene.getFirstActorByType(Ventilator.class).isRep())
                scene.removeActor(this);
            a.setFrame(getPosX()+getWidth()/2-radius,getPosY()+getWidth()/2-radius,radius*2,radius*2);
            if(a.intersects(ripley.getPosX(),ripley.getPosY(),ripley.getWidth(),ripley.getHeight()))
                chase();
            else
                stay();
    }),
            new Wait<>(0.05f))).scheduleFor(this);
    }
}
