package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;


public class Helicopter extends AbstractActor {


    private Player player;

    public Helicopter(){
        setAnimation(new Animation("sprites/heli.png",64,64,0.1f, Animation.PlayMode.LOOP));

    }


    public void searchAndDestroy(){
        this.player = (Player) Objects.requireNonNull(getScene()).getFirstActorByName("Player");
        if(this.player == null)
            return;
        new While<>(()-> {
                if(player.getEnergy()<=0)
                    return false;
                return true;
        }, new Invoke<>(this::chasePlayer)).scheduleFor(this);

        }
        private void chasePlayer(){

        if(this.player== null)
            return;
        if(this.intersects(player))
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
