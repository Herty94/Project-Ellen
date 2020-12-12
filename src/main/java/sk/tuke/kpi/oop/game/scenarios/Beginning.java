package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.beginning.actors.*;
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
    private boolean ghost;
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ghost = true;

        ripley = scene.getFirstActorByType(Ripley.class);
        use = scene.getInput().registerListener(new KeeperController(ripley));
        move =scene.getInput().registerListener(new MovableController(ripley));
        shoot = scene.getInput().registerListener(new ShooterController(ripley));
        scene.getMessageBus().subscribe(Firethrower.FLAME_PICKET, fire -> {
            if(ghost)
                 scene.addActor(new Ghost(), 365,439);
            ghost=false;
        });
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
                case "vent":
                    return new Ventilator();
                case "blockade":
                    return new Blockade();
                case "back door":
                    return new Door("back door", Door.Orientation.HORIZONTAL);
                case "exit door":
                    return new Door("exit door", Door.Orientation.VERTICAL);
                case "ammo":
                    return new Ammo();
                case "flamethrower":
                    return new Firethrower();
                case "body":
                    return new Body();
                case "alien":
                    return new Alien(100, new RandomlyMoving());
                case "electricity":
                    return new Electricity();
                case "standbutton":
                    return new Button();
                case "barrel":
                    return new Barrel();
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
