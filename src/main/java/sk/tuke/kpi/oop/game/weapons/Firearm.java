package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int ammo;
    private int maxAmmo;
    public Firearm(int ammo, int maxAmmo){
        this.ammo=ammo;
        this.maxAmmo=maxAmmo;
    }
    public Firearm(int ammo){
        this.ammo=ammo;
        this.maxAmmo=ammo;
    }

    public int getAmmo() {
        return ammo;
    }
}
