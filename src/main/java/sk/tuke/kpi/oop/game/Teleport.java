package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class Teleport extends AbstractActor {
    private Teleport destinationTeleport;
    private Player player;
    private boolean something;
    private Disposable disposable;
    public Teleport(Teleport teleport){
        this.destinationTeleport = teleport;
        setAnimation(new Animation("sprites/lift.png"));
        this.something=false;
    }
    public void setBool(Boolean bool){
        this.something=bool;
    }
    public Teleport getDestination(){
        return this.destinationTeleport;
    }

    public void setDestination(Teleport destinationTeleport){
        if(this!=destinationTeleport)
        this.destinationTeleport = destinationTeleport;
        if(disposable!=null)
            disposable.dispose();
        telepot();
    }
    public void teleportPlayer(Player player){

        //System.out.println("player <"+(player.getPosX()+player.getWidth()/2)+" width:"+player.getWidth()+" height:"+player.getHeight()+" "+(player.getPosY()+player.getWidth()/2)+">           lifg:<"+(destinationTeleport.getPosX()+this.getWidth()/2)+" "+(destinationTeleport.getPosY()+this.getWidth()/2));
        player.setPosition(destinationTeleport.getPosX()+(this.getWidth()/2)-(player.getWidth()/2), destinationTeleport.getPosY()+(this.getWidth()/2)-(player.getWidth()/2));
        destinationTeleport.setBool(false);
        disposable.dispose();
        telepot();

    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        telepot();
    }
    private boolean playerIntersection(){

        int px = player.getPosX()+player.getWidth()/2;
        int py = player.getPosY()+ player.getWidth()/2;

            if(px<this.getPosX()|| px>(this.getPosX()+this.getWidth())||py<this.getPosY()|| py>(this.getPosY()+this.getWidth())) {
                something = true;
            }
        return px >= this.getPosX() && px <= (this.getPosX() + this.getWidth()) && py >= this.getPosY() && py <= (this.getPosY() + this.getWidth());
    }
    private void telepot(){
        this.player = (Player) Objects.requireNonNull(getScene()).getFirstActorByName("Player");
        if(destinationTeleport !=null)
            disposable = (new When<>(
                () -> (player!=null&&playerIntersection()&&something),
                new Invoke<>(this::teleportPlayer)
            )).scheduleFor(player);
    }
}
