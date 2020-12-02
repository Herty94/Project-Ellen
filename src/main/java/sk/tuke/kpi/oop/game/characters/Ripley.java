package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive {
    private int movingSpeed;
    private int energy;
    private int ammo;
    private boolean dead;
    private Animation animation;
    private Animation died;
    private Backpack backpack;
    private Health health;
    public Ripley(){
        super("Ellen");
        this.movingSpeed = 2;
        animation =new Animation("sprites/player.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        animation.pause();
        died = new Animation("sprites/player_die.png",32,32,0.1f, Animation.PlayMode.ONCE);
        this.energy=100;
        setAnimation(animation);
        this.ammo = 0;
        this.dead=false;
        backpack = new Backpack("Ripley's backpack",10);
        health = new Health(100);
    }

    @Override
    public Backpack getBackpack() {
        return this.backpack;
    }

    public void setEnergy(int energy) {
        if(energy>0) {
            this.energy = energy;
            if (energy > 100)
                this.energy = 100;
        }
        else if(energy==0) {
                this.energy = energy;
                this.dead=true;
                setAnimation(died);
            }
    }

    @Override
    public Health getHealth() {
        return this.health;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public int getSpeed() {
        return this.movingSpeed;
    }

    @Override
    public void startedMoving(Direction direction) {
        animation.setRotation(direction.getAngle());
        animation.play();

    }

    @Override
    public void stoppedMoving() {
        animation.pause();
    }
    public void showRipleyState(){
        Scene scene = getScene();
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET*2;
        scene.getGame().getOverlay().drawText("Energy: "+this.health,15,yTextPos);
        scene.getGame().getOverlay().drawText("Ammo: "+this.ammo,15,yTextPos- GameApplication.STATUS_LINE_OFFSET);
        scene.getGame().pushActorContainer(this.getBackpack());
    }
    public boolean isDead() {
        return this.dead;
    }
}
