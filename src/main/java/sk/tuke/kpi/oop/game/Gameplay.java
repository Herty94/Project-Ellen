package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.tools.Hammer;

public class Gameplay extends Scenario {

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();  // vytvorenie instancie reaktora
        scene.addActor(reactor, 148, 94);  // pridanie reaktora do sceny na poziciu [x=64, y=64]
        reactor.turnOn();
        Cooler cooler = new Cooler(reactor);
        scene.addActor(cooler, 64, 64);
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);
        Hammer hammer = new Hammer();
        scene.addActor(hammer, 10, 10);
        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() ->
                hammer.useWith(reactor))
        ).scheduleFor(reactor);
        scene.addActor(new Teleport(null),124,124);
        scene.addActor(new ChainBomb(10), 150, 150);
        scene.addActor(new ChainBomb(10), 100, 150);
        scene.addActor(new ChainBomb(10), 200, 150);
        scene.addActor(new ChainBomb(10), 150, 100);
        scene.addActor(new ChainBomb(10), 150, 200);
    }
}
