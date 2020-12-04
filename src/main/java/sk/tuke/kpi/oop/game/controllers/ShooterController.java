package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

public class ShooterController implements KeyboardListener {
    private final Armed shooter;
    public ShooterController(Armed shooter){
        this.shooter=shooter;
    }
    @Override
    public void keyPressed(Input.@NotNull Key key) {
        switch(key){
            case SPACE:
                    new Fire<>(shooter).scheduleFor(shooter);
                break;
            default:
                break;
        }
    }
}
