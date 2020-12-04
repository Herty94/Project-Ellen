package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;


public class Fire<A extends Armed> extends AbstractAction<A> {
    private A actor;
    public Fire(A shooter){
       this.actor = shooter;
    }
    @Override
    public void execute(float deltaTime) {
        Fireable ammo=actor.getFirearm().fire();
        if(ammo!=null && actor != null) {
            actor.getScene().addActor(ammo, actor.getPosX()+8, actor.getPosY()+8);
            new Move<>(Direction.fromAngle(actor.getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(ammo);
        }
        setDone(true);
    }
}
