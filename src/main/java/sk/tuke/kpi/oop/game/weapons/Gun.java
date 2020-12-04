package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int ammo, int maxAmmo){
        super(ammo,maxAmmo);
    }
    public Gun(int ammo){
        super(ammo);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
