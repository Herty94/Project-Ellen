package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

public class FirstSteps implements SceneListener {

    private Ripley ripley;

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Energy ene = new Energy();
        Ammo ammo = new Ammo();
        scene.addActor(ammo,100, 40);
        //Reactor reac = new Reactor();
      //  reac.turnOn();
       // reac.increaseTemperature(6000);
        this.ripley = new Ripley();
        scene.addActor(ene,150,150);
        //scene.addActor(reac,100,100);
        Wrench wr = new Wrench();
        Hammer ham = new Hammer();
        FireExtinguisher fire = new FireExtinguisher();
        scene.addActor(ripley,0,0);
        ripley.getBackpack().add(ham);
        ripley.getBackpack().add(wr);
        scene.addActor(fire,50,50);
        scene.getInput().registerListener(new KeeperController(ripley));
        scene.getInput().registerListener(new MovableController(ripley));
        // ripley.getBackpack().shift();
        //new Use<>(ham).scheduleFor(reac);
        new When<>(
            () -> ammo.intersects(ripley),
            new Invoke<>(() ->
                new Use<>(ammo).scheduleFor(ripley))).scheduleFor(ripley);
        new When<>(
            () -> ene.intersects(ripley),
            new Invoke<>(() ->
        new Use<>(ene).scheduleFor(ripley))).scheduleFor(ripley);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET*2;
        scene.getGame().getOverlay().drawText("Energy: "+ripley.getHealth().getValue(),15,yTextPos);
        scene.getGame().getOverlay().drawText("Ammo: "+ripley.getAmmo(),15,yTextPos- GameApplication.STATUS_LINE_OFFSET);
        scene.getGame().pushActorContainer(ripley.getBackpack());
    }

}
