package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Movable actor;
    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.LEFT, Direction.WEST)
        // dalsie zaznamy zobrazenia prekladu ...
    );
    private Move move;
    private Direction direction;
    private Set<Direction> directionSet;
    public MovableController(Movable actor){
        this.actor = actor;
        directionSet = new HashSet<Direction>();
        direction = Direction.NONE;
    }

    @Override
    public void keyPressed(Input.@NotNull Key key) {
        if(keyDirectionMap.containsKey(key)) {
            this.directionSet.add(keyDirectionMap.get(key));
            this.direction=Direction.NONE;
            for (Direction direc : directionSet)
                this.direction=this.direction.combine(direc);

            System.out.println(direction.getDx()+" "+direction.getDy());
            System.out.println(directionSet);
            if(move!=null)
                move.stop();
            move = new Move<>(direction,86400);
            move.scheduleFor(actor);
        }
    }

    @Override
    public void keyReleased(Input.@NotNull Key key) {
        if(keyDirectionMap.containsKey(key)){
            directionSet.remove(keyDirectionMap.get(key));
            this.direction=Direction.NONE;
            for (Direction direc : directionSet)
                this.direction=this.direction.combine(direc);
            if(move!=null)
                move.stop();
            if(directionSet.isEmpty())
                return;
            move = new Move<>(direction,86400);
            move.scheduleFor(actor);
        }
    }
}
