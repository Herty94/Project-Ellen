package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;

import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;

import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;



public class MissionImpossible implements SceneListener {
    private Ripley ripley;
    private long time = 0;
    private boolean contamine=false;
    private Disposable move;
    private Disposable keys;
    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            switch(name){
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "door":
                    return new LockedDoor();
                case "access card":
                    return new AccessCard();
                case "ventilator":
                    return new Ventilator();
                case "locker":
                    return new Locker();
                default:
                    return null;
            }

        }
    }
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = scene.getFirstActorByType(Ripley.class);
        keys = scene.getInput().registerListener(new KeeperController(ripley));
        move = scene.getInput().registerListener(new MovableController(ripley));



    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        scene.getMessageBus().subscribe(Door.DOOR_OPENED, door -> {
            contamine = true;
        } );
        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, vent -> {
                contamine = false;
        } );
        if(contamine&&time%10==0)
            ripley.getHealth().drain(1);
        time++;
        if(ripley.isDead()){
            keys.dispose();
            move.dispose();
        }

        ripley.showRipleyState();
        scene.follow(ripley);
        if(time>=Long.MAX_VALUE-100)
            time = 0;
    }

}
