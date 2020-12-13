package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input.Key;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Push;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.beginning.actors.Firethrower;
import sk.tuke.kpi.oop.game.beginning.Pushable;
import sk.tuke.kpi.oop.game.beginning.Wearable;
import sk.tuke.kpi.oop.game.items.Usable;



public class KeeperController implements KeyboardListener {
    private final Keeper keeper;
    public KeeperController(Keeper keeper){
        this.keeper = keeper;
    }

    @Override
    public void keyPressed(@NotNull Key key) {

        switch(key){
             case ENTER:
                 new Take<>().scheduleFor(keeper);

                break;
             case BACKSPACE: new Drop<>().scheduleFor(keeper);
                break;
             case S: new Shift<>().scheduleFor(keeper);
                break;
            case U:
                    uPressed();
                break;
            case B:
                    bPressed();
                break;
            case P:
                pPressed();
                break;
            default:
                break;

        }
    }
    private void uPressed(){
        for(Actor actor : keeper.getScene().getActors())
            if(actor instanceof Usable<?>&& actor.intersects(keeper) && !(actor instanceof Wearable)) {
                new Use<>((Usable<?>) actor).scheduleForIntersectingWith(keeper);
                break;
            }
    }
    private void bPressed(){
        if(keeper.getBackpack().peek() instanceof Usable<?>)
            new Use<>((Usable<?>) keeper.getBackpack().peek()).scheduleForIntersectingWith(keeper);
    }
    private void pPressed() {
        for (Actor actor : keeper.getScene().getActors())
            if (actor instanceof Pushable && actor.intersects(keeper) ) {
                new Push<>(Direction.fromAngle(keeper.getAnimation().getRotation()), 1).scheduleFor((Pushable) actor);
                break;
            }
    }

    @Override
    public void keyReleased(@NotNull Key key) {
        if((key.equals(Key.B)||key.equals(Key.U))&&keeper.getBackpack().peek() instanceof Firethrower)
            ((Firethrower)keeper.getBackpack().peek()).stop();
    }
}
