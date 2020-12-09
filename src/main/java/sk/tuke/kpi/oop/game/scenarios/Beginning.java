package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;

public class Beginning implements SceneListener {

    private Ripley ripley;
    private Disposable shoot;
    private Disposable move;
    private Disposable use;
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = scene.getFirstActorByType(Ripley.class);
        use = scene.getInput().registerListener(new KeeperController(ripley));
        move =scene.getInput().registerListener(new MovableController(ripley));
        shoot = scene.getInput().registerListener(new ShooterController(ripley));
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
                    return makeDoor(type);
                case "back door":
                    return new Door("back door", Door.Orientation.HORIZONTAL);
                case "exit door":
                    return new Door("exit door", Door.Orientation.VERTICAL);
                case "ammo":
                    return new Ammo();
                case "alien":
                    return alienCreate(type);
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
        private Door makeDoor(String type){
            System.out.println("ta so ? "+type);
            if(type.contentEquals("vertical"))
                return new Door("back", Door.Orientation.VERTICAL);
            else
                return new Door("front door", Door.Orientation.HORIZONTAL);
        }
        private Alien alienCreate(String type){
            if(type.endsWith("running")) {
                return new Alien(100, new RandomlyMoving());
            }
            else if(type.endsWith("waiting1")) return new Alien(100,new Observing<>(Door.DOOR_OPENED, actor->actor.getName().endsWith("front door")
                ,new RandomlyMoving()));
            else if(type.endsWith("waiting2")) return new Alien(100,new Observing<>(Door.DOOR_OPENED,actor->actor.getName().endsWith("back door")
                ,new RandomlyMoving()));
            else return new Alien();
        }


    }
    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        if(ripley==null)
            return;
        ripley.showRipleyState();
        if(ripley.getHealth().getValue()==0) {
            scene.cancelActions(ripley);
            use.dispose();
            move.dispose();
            shoot.dispose();
        }
        scene.follow(ripley);
    }
}
