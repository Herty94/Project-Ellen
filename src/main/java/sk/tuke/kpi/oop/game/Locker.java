package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Ripley> {
    private int use;
    public Locker(){
        setAnimation(new Animation("sprites/locker.png",16,16));
        use = 1;
    }
    @Override
    public void useWith(Ripley actor) {
        if(use!=1)
            return;
        getScene().addActor(new Hammer(),getPosX()+8,getPosY()+16);
        use = 0;
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
