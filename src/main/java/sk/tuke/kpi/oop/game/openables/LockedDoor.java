package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door{

    private boolean locked;
    public LockedDoor() {
        super("door",Orientation.VERTICAL);
        this.locked=true;
    }
    public void lock(){
        super.close();
        locked=true;
    }
    public void unlock(){
        super.open();
        locked=false;
    }
    public boolean isLocked(){
        return this.locked;
    }

   /* @Override
    public void open() {
    }*/

    @Override
    public void useWith(Actor actor) {

    }
}

