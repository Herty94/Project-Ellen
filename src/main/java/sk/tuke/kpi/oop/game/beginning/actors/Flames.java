package sk.tuke.kpi.oop.game.beginning.actors;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Flames extends AbstractActor {

        private Animation close;
        private Animation further;
        private Animation full;

    public Flames(){
        full= new Animation("sprites/flamestr.png",32,112,0.15f, Animation.PlayMode.LOOP_PINGPONG);
        further= new Animation ("sprites/flamestr_midd",32,112-32);
        close= new Animation ("sprites/flamestr_small",32,112-32*2);
        setAnimation(full);
    }
    public void setAnim(int distance){
        switch (distance){
            case 4:
                setAnimation(full);
                break;
            case 3:
                setAnimation(further);
                break;
            case 2:
                setAnimation(close);
                break;
            case 1:
            case 0:
                setAnimation(full);
                break;
            default: setAnimation(full);
            break;
        }
    }
}
