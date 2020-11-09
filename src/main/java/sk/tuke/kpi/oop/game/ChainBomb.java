package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;

import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.List;

public class ChainBomb extends TimeBomb {
    private final float radius;
    private final float time;
    public ChainBomb(float time){
        super(time);
        this.time = time;
        radius = 50;
    }
    @Override
    public void activate(){
        super.activate();
        new ActionSequence<>(
            new Wait<>(time),
            new Invoke<>(this::chainExp)
        ).scheduleFor(this);
    }
    private void chainExp(){
        Ellipse2D.Float a = new Ellipse2D.Float();
        List<Actor> act = super.getScene().getActors();
        Iterator<Actor> it = act.iterator();
        ChainBomb bomb;
        Actor actor;
        a.setFrame(getPosX()+getWidth()/2-radius,getPosY()+getWidth()/2-radius,radius*2,radius*2);
        while(it.hasNext()){
            actor = it.next();
            if(actor instanceof ChainBomb && a.intersects(actor.getPosX(),actor.getPosY(),actor.getWidth(),actor.getHeight())){
                bomb =(ChainBomb) actor;
                if(!bomb.isActivated())
                    bomb.activate();
            }
        }
       


    }
}
