package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Random;

public class RandomlyMoving implements Behaviour<Actor> {
    private Move<Movable> move;
    private int random;
    @Override
    public void setUp(Actor actor) {
        new Loop<>(new Invoke<>(()->{
            if(actor!=null){
                random = (Math.abs(new Random().nextInt()));
                System.out.println("RandomMoving: "+random);
                if(random%50==1) {
                    if (move != null && !move.isDone()) move.stop();
                    move = new Move<>(Direction.values()[random % Direction.values().length], Float.MAX_VALUE);
                    move.scheduleFor((Movable) actor);
                }
            }
        })).scheduleFor(actor);
    }
}
