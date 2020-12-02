package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo extends AbstractActor implements Usable<Ripley> {

    public Ammo(){
        setAnimation(new Animation("sprites/ammo.png"));
    }

    @Override
    public void useWith(Ripley actor) {
        int ammo = actor.getAmmo();
        if(ammo<=500) {
            actor.setAmmo(ammo + 50);

            if (ammo > 450)
                actor.setAmmo(500);
            getScene().removeActor(this);
        }
    }
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
