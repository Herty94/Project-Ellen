package sk.tuke.kpi.oop.game.beginning.patterns;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class CompleteTable implements Table{
    @Override
    public Animation getAnim() {
        return new Animation("sprites/popup_level_done.png");
    }
}
