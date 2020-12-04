package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int ammo;
    private final int maxAmmo;
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
    public void reload(int newAmmo){
        ammo += newAmmo;
        if(ammo>maxAmmo){
            ammo = maxAmmo;
        }
    }
    protected abstract Fireable createBullet();
    public Fireable fire(){
        if(ammo<=0)
            return null;
        ammo-=1;
        return createBullet();
    }

}
