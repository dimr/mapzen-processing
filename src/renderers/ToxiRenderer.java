package renderers;

import processing.core.PApplet;
import toxi.processing.ToxiclibsSupport;

/**
 * Created by dimitris on 4/8/16.
 */
public class ToxiRenderer  {

    private PApplet pa;
    private ToxiclibsSupport toxi;

    protected  ToxiRenderer(PApplet pa,ToxiclibsSupport toxi){
        this.pa=pa;
        this.toxi=new ToxiclibsSupport(this.pa);

    }
}
