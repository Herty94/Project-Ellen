package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive ,Armed{
    private final int movingSpeed;
    private int ammo;
    private final boolean dead;
    private final Animation animation;
    private final Animation died;
    private final Backpack backpack;
    private Firearm weapon;
    private final Health health;
    public Ripley(){
        super("Ellen");
        this.movingSpeed = 2;
        animation =new Animation("sprites/player.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        animation.pause();
        died = new Animation("sprites/player_die.png",32,32,0.1f, Animation.PlayMode.ONCE);
        setAnimation(animation);
        this.ammo = 0;
        this.dead=false;
        backpack = new Backpack("Ripley's backpack",1);
        health = new Health(100);
        weapon =new Gun(10,100);
        health.onExhaustion(() -> setAnimation(died));

    }

    @Override
    public void collidedWithWall() {

    }

    @Override
    public Backpack getBackpack() {
        return this.backpack;
    }

    @Override
    public Firearm getFirearm() {
        return this.weapon;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.weapon=weapon;
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
        System.out.println("Ripley rotation: "+animation.getRotation());

    }

    @Override
    public void stoppedMoving() {
        animation.stop();
        //System.out.println("stoped");
    }
    public void showRipleyState(){
        Scene scene = getScene();
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET*2;
        scene.getGame().getOverlay().drawText("Energy: "+this.health.getValue(),15,yTextPos);
        scene.getGame().getOverlay().drawText("Ammo: "+this.weapon.getAmmo(),15,yTextPos- GameApplication.STATUS_LINE_OFFSET);
        scene.getGame().pushActorContainer(this.getBackpack());
    }
    public boolean isDead() {
        return this.dead;
    }

}
