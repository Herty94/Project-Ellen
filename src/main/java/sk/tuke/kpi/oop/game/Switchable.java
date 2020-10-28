package sk.tuke.kpi.oop.game;

public interface Switchable {
    boolean state = false;
    void turnOn();
    void turnOff();
    default boolean isOn(){
        return this.state;
    }
}
