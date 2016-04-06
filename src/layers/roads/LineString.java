package layers.roads;

import toxi.geom.Vec2D;

import javax.sound.sampled.Line;
import java.util.ArrayList;

/**
 * Created by dimitris on 4/6/16.
 */
//LineString is essentially a Vec
public class LineString {
    private Vec2D vec;
    private ArrayList<Vec2D> points;
    public LineString(){
        this.vec= new Vec2D();
    }


    public LineString(float x, float y){
        this.vec=new Vec2D(x,y);
    }


    public LineString(ArrayList p){
        points=new ArrayList<Vec2D>(p.size());
        for (Object pp:p){
            points.add(new Vec2D((Float)p.get(0),(Float)p.get(1)));
        }
    }

    public void setLineString(float x,float y){
        this.vec.set(x,y);

    }

}
