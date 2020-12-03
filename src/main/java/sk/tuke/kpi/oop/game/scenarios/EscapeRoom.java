package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class EscapeRoom implements SceneListener {


    private Ripley ripley;


    @Override
    public void sceneCreated(@NotNull Scene scene) {

        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC,actor -> {
            if(actor instanceof Alien)
                randomMove((Movable)actor);
        });
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = scene.getFirstActorByType(Ripley.class);
         scene.getInput().registerListener(new KeeperController(ripley));
         scene.getInput().registerListener(new MovableController(ripley));

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        ripley.showRipleyState();
        scene.follow(ripley);
    }

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
                case "ammo":
                    return new Ammo();
                case "alien":
                    return new Alien();
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
    private Disposable randomMove(Movable actor){

        Move<Movable> move  =  new Move<>(Direction.EAST,10);

        return move.scheduleFor((Alien)actor);
    }
}
