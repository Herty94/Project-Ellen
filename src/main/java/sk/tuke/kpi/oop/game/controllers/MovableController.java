package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input.Key;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

    public class MovableController implements KeyboardListener {
        private final Movable actor;
        private final Map<Key, Direction> keyDirectionMap = Map.ofEntries(
            Map.entry(Key.UP, Direction.NORTH),
            Map.entry(Key.DOWN, Direction.SOUTH),
            Map.entry(Key.RIGHT, Direction.EAST),
            Map.entry(Key.LEFT, Direction.WEST)
        );
        private Move<Movable> move;
        private Direction direction;
        private final Set<Direction> directionSet;

        public MovableController(Movable actor){
            this.actor = actor;
            directionSet = new HashSet<>();
            direction = Direction.NONE;
        }

        @Override
        public void keyPressed(@NotNull Key key) {

            if(keyDirectionMap.containsKey(key)) {
                this.directionSet.add(keyDirectionMap.get(key));
                this.direction=Direction.NONE;
                for (Direction direc : directionSet)
                    this.direction=this.direction.combine(direc);
                System.out.println(direction);
                if(move!=null)
                    move.stop();
                System.out.println("pressed "+directionSet);
                move = new Move<>(direction,86400);
                move.scheduleFor(actor);
            }
        }
        @Override
        public void keyReleased(@NotNull Key key) {

            if(keyDirectionMap.containsKey(key)){
                directionSet.remove(keyDirectionMap.get(key));
                this.direction=Direction.NONE;
                for (Direction direc : directionSet)
                    this.direction=this.direction.combine(direc);

                if(move!=null)
                    move.stop();
                if(directionSet.isEmpty())
                    return;
                System.out.println("release "+directionSet);
                move = new Move<>(direction,86400);
                move.scheduleFor(actor);
            }
        }


    }


