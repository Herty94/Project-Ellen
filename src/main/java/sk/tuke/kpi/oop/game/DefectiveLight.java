package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;


public class DefectiveLight extends Light{
    private int random;
    public DefectiveLight(){
        super(1);
        this.random = 0;
    }
    public void randomLight(){
        random = (int)(new Random().nextInt())%20;
        if(random==1)
            super.toggle();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::randomLight)).scheduleFor(this);
    }
}
