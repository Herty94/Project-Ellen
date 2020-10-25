package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;

import java.util.function.Predicate;

public class Helicopter extends AbstractActor {

    private Animation animation;
    private Player player;

    public Helicopter(){
        this.animation= new Animation("sprites/heli.png",64,64,0.1f, Animation.PlayMode.LOOP);
        setAnimation(animation);

    }


    public void searchAndDestroy(){
        this.player = (Player)getScene().getFirstActorByName("Player");
        if(this.player== null)
            return;
        new While<>(new Predicate<While<? extends Actor>>() {
            @Override
            public boolean test(While<? extends Actor> aWhile) {
                if(player.getEnergy()<=0)
                    return false;
                return true;
            }
        }, new Invoke<>(this::chasePlayer)).scheduleFor(this);

    }
    private void chasePlayer(){

        if(this.player== null)
            return;
        if(intersects(player))
            player.setEnergy(player.getEnergy()-1);
        int px,py,hx,hy;
        px = player.getPosX();
        py = player.getPosY();
        hx = this.getPosX();
        hy = this.getPosY();

        if(hx<px)
            hx++;
        else
            hx--;
        if(hy<py)
            hy++;
        else
            hy--;
        this.setPosition(hx,hy);
    }

}
