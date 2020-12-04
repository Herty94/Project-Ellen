package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;


public class Ammo extends AbstractActor implements Usable<Armed> {

    public Ammo(){
        setAnimation(new Animation("sprites/ammo.png"));
    }

    @Override
    public void useWith(Armed actor) {
        int ammo = actor.getFirearm().getAmmo();
        actor.getFirearm().reload(50);
        getScene().removeActor(this);

    }
    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
