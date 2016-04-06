package geometry.types.roads;

import toxi.geom.Vec2D;

import javax.sound.sampled.Line;
import java.util.ArrayList;

/**
 * Created by dimitris on 4/6/16.
 */
//LineString is essentially a Vec
public class LineString {

    private ArrayList<Vec2D> points;


    @Override
    public String toString() {
        return "LineString{" +
                "points=" + points +
                ", numberOfLineString=" + getNumberOfLineString() +
                '}';
    }

    public LineString(ArrayList p){
        points=new ArrayList<Vec2D>(p.size());
        for (Object o:p){
            ArrayList listVec=(ArrayList)(o);
            points.add(new Vec2D(((Float) ((Double) listVec.get(0)).floatValue()), ((Float) ((Double) listVec.get(1)).floatValue())));
        }

    }

    public LineString(){

    }



    public int getNumberOfLineString(){
        return this.points.size();
    }

}
