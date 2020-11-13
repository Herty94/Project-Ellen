package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Input.Key;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;


public class KeeperController implements KeyboardListener {
    private Keeper keeper;

    public KeeperController(Keeper keeper){
        this.keeper = keeper;
    }
    @Override
    public void keyPressed(@NotNull Key key) {

        switch(key){
             case ENTER: new Take(keeper.getScene()).scheduleFor(keeper);
                break;
             case BACKSPACE: new Drop(keeper.getScene()).scheduleFor(keeper);
                break;
             case S: new Shift(keeper.getScene()).scheduleFor(keeper);
                break;
        }
    }
}
