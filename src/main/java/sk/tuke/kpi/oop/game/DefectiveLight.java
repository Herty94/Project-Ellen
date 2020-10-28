package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


import java.util.Random;


public class DefectiveLight extends Light implements Repairable{
    private int random;
    private Disposable disposable;
    public DefectiveLight(){
        this.random = 0;
    }
    public void randomLight(){
        random = (new Random().nextInt())%10;
        if(random==1)
            super.toggle();
    }

    @Override
    public boolean repair() {
        if (disposable==null)
            return false;
        disposable.dispose();
        super.turnOn();
        disposable = new ActionSequence<>(
            new Wait<>(10),
            new Loop<>(new Invoke<>(this::randomLight))
        ).scheduleFor(this);
        return true;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        disposable = new Loop<>(new Invoke<>(this::randomLight)).scheduleFor(this);
    }
}
