package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Energy;

public class Ripley extends AbstractActor implements Movable, Keeper {
    private int movingSpeed;
    private int energy;
    private Animation animation;
    private Backpack backpack;
    public Ripley(){
        super("Ellen");
        this.movingSpeed = 2;
        animation =new Animation("sprites/player.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        animation.pause();
        this.energy=50;
        setAnimation(animation);
        backpack = new Backpack("Ripley's backpack",10);
    }

    @Override
    public Backpack getBackpack() {
        return this.backpack;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
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
}
