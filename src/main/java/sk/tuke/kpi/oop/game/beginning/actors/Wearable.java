package sk.tuke.kpi.oop.game.beginning.actors;

import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public interface Wearable extends Collectible {
    default void wearTop(Keeper actor){}
    default boolean isWeared(){
        return false;
    }
}
