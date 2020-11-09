package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

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
    public MovableController(Movable actor){
        this.actor = actor;
    }

    @Override
    public void keyPressed(Input.@NotNull Key key) {
        if(keyDirectionMap.containsKey(key)) {
            if (move != null && !move.isDone())
                move.stop();
            move = new Move(keyDirectionMap.get(key), 86400);
            move.scheduleFor(actor);
        }
    }

    @Override
    public void keyReleased(Input.@NotNull Key key) {
        if(keyDirectionMap.containsKey(key)){
            if(move!=null&&!move.isDone())
            move.stop();
        }
    }
}
