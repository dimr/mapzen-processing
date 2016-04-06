package geometry.types.roads;

import toxi.geom.Vec2D;
import utils.geo.BoundingBox;

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

    public LineString(ArrayList p) {
        points = new ArrayList<Vec2D>(p.size());
        for (Object o : p) {
            ArrayList listVec = (ArrayList) (o);
            points.add(new Vec2D(((Float) ((Double) listVec.get(0)).floatValue()), ((Float) ((Double) listVec.get(1)).floatValue())));
        }

    }


    public ArrayList<Vec2D> getPoints() {
        return this.points;
    }

    /**
     * @param The    Bounding Box
     * @param the    width of the applet
     * @param height of the applet (need to pass Applet)
     * @return
     */
    public LineString toApplicationDimension(BoundingBox box, int width, int height) {
        float geoLeft = box.getLongMin();
        float geoRigth = box.getLongMax();
        float geoTop = box.getLatMin();
        float geoBottom = box.getLatMax();
        ArrayList<Vec2D> temp = new ArrayList<>(this.points.size());
        for (Vec2D v : this.points) {
            float x = width * (v.x() - geoLeft) / (geoRigth - (geoLeft));
            float y = (height - height * (v.y() - geoTop) / (geoBottom - geoTop));
            temp.add(new Vec2D(x, y));
        }
        this.points = temp;
        return this;
    }

    public int getNumberOfLineString() {
        return this.points.size();
    }

}
