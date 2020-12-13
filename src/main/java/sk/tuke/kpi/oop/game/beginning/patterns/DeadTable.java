package sk.tuke.kpi.oop.game.beginning.patterns;


import sk.tuke.kpi.gamelib.graphics.Animation;

public class DeadTable implements Table{
    @Override
    public Animation getAnim() {
        return new Animation("sprites/popup_level_failed.png");
    }
}
