package sk.tuke.kpi.oop.game.beginning.patterns;

import sk.tuke.kpi.gamelib.graphics.Animation;

public interface Table{

    default Animation getAnim(){
        return null;
    }


}
