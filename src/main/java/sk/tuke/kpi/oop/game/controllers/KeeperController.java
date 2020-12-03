package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input.Key;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;



public class KeeperController implements KeyboardListener {
    private final Keeper keeper;
    public KeeperController(Keeper keeper){
        this.keeper = keeper;
    }
    @Override
    public void keyPressed(@NotNull Key key) {

        switch(key){
             case ENTER: new Take<>(keeper.getScene()).scheduleFor(keeper);
                break;
             case BACKSPACE: new Drop<>(keeper.getScene()).scheduleFor(keeper);
                break;
             case S: new Shift<>(keeper.getScene()).scheduleFor(keeper);
                break;
            case U:
                for(Actor actor : keeper.getScene().getActors())
                    if(actor instanceof Usable<?>&& actor.intersects(keeper))
                        new Use<>((Usable<?>)actor).scheduleForIntersectingWith(keeper);
                break;
            case B:
                        new Use<>((Usable<?>) keeper.getBackpack().peek()).scheduleForIntersectingWith(keeper);
                break;

        }
    }
}
