package sk.tuke.kpi.oop.game;

import com.sun.source.tree.NewArrayTree;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor{

    private boolean electricity;
    private boolean onState;
    private Animation onLight;
    private Animation offLight;
    private DefectiveLight defectiv;

    public Light(){
        this.onState = false;
        this.electricity = false;
        onLight = new Animation("sprites/light_on.png");
        offLight = new Animation("sprites/light_off.png");
        updateAnimation();
        this.defectiv=null;
    }
    public Light(int i){
        this.onState = false;
        this.electricity = false;
        onLight = new Animation("sprites/light_on.png");
        offLight = new Animation("sprites/light_off.png");
        updateAnimation();
        this.defectiv=null;
    }


    public void toggle(){
        this.onState = !this.onState;
        updateAnimation();
    }

    private void updateAnimation() {
        if(!this.onState || !electricity)
            setAnimation(offLight);
        else
            setAnimation(onLight);
    }

    public void setElectricityFlow(boolean electricity){
        this.electricity = electricity;
        updateAnimation();
    }

}
