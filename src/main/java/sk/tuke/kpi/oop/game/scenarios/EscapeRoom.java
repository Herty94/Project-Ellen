package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.*;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class EscapeRoom implements SceneListener {


    private Ripley ripley;
    private Disposable shoot;
    private Disposable move;
    private Disposable use;

    @Override
    public void sceneCreated(@NotNull Scene scene) {
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);
        scene.addActor(new Hammer(),100,100);

        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC,actor -> {
           // if(actor instanceof Alien)
                //randomMove((Movable)actor);
        });
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
         ripley = scene.getFirstActorByType(Ripley.class);
         use = scene.getInput().registerListener(new KeeperController(ripley));
         move =scene.getInput().registerListener(new MovableController(ripley));
         shoot = scene.getInput().registerListener(new ShooterController(ripley));
         System.out.println("bla : "+Direction.NORTHWEST.combine(Direction.WEST));
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        ripley.showRipleyState();
        if(ripley.getHealth().getValue()==0) {
            scene.cancelActions(ripley);
            use.dispose();
            move.dispose();
            shoot.dispose();
        }
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
                case "front door":
                    return new Door("front door", Door.Orientation.VERTICAL);
                case "back door":
                    return new Door("back door", Door.Orientation.HORIZONTAL);
                case "exit door":
                    return new Door("exit door", Door.Orientation.VERTICAL);
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
                case "alien mother":
                    return new AlienMother();
                default:
                    return null;

            }

        }

    }
    /*private Disposable randomMove(Movable actor){

        Move<Movable> move  =  new Move<>(Direction.EAST,10);

        return move.scheduleFor(actor);
    }*/
}
