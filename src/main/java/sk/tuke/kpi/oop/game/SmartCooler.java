package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler{

    private Reactor reactor;
    private int temperature;

    public SmartCooler(Reactor reactor){
        super(reactor);
        this.reactor = reactor;
    }

    private void smartSwitch(){
        this.temperature = reactor.getTemperature();
        if(temperature>2500){
            super.turnOn();
        }
        if(temperature<1500){
            super.turnOff();
        }
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::smartSwitch)).scheduleFor(this);
    }

}
