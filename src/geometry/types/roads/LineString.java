package geometry.types.roads;

import toxi.geom.Vec3D;
import utils.geo.BoundingBox;

import javax.sound.sampled.Line;
import java.util.ArrayList;

/**
 * Created by dimitris on 4/6/16.
 */
//LineString is essentially a Vec
public class LineString {

    private ArrayList<Vec3D> points;


    @Override
    public String toString() {
        return "LineString{" +
                "points=" + points +
                ", numberOfLineString=" + getNumberOfLineString() +
                '}';
    }

    public LineString(ArrayList p) {
        this.points = new ArrayList<Vec3D>(p.size());
        for (Object o : p) {
            ArrayList listVec = (ArrayList) (o);
            this.points.add(new Vec3D(((Float) ((Double) listVec.get(0)).floatValue()), ((Float) ((Double) listVec.get(1)).floatValue()), 0));
        }

    }

    public LineString(ArrayList p, BoundingBox box, int width, int height) {
        this.points = new ArrayList<Vec3D>(p.size());
        for (Object o : p) {
            ArrayList listVec = (ArrayList) (o);
            this.points.add(new Vec3D(((Float) ((Double) listVec.get(0)).floatValue()), ((Float) ((Double) listVec.get(1)).floatValue()), 0));
        }
        float geoLeft = box.getLongMin();
        float geoRigth = box.getLongMax();
        float geoTop = box.getLatMin();
        float geoBottom = box.getLatMax();
        ArrayList<Vec3D> temp = new ArrayList<>(this.points.size());
        for (int i = 0; i < this.points.size(); i++) {
            float x = width * (this.points.get(i).x() - geoLeft) / (geoRigth - (geoLeft));
            float y = (height - height * (this.points.get(i).y() - geoTop) / (geoBottom - geoTop));

//            temp.add(new Vec3D(x, y));
            this.points.set(i, new Vec3D(x, y, 0));
//            System.out.println(x + " " + y);
        }

    }


    public ArrayList<Vec3D> getPoints() {
        return this.points;
    }


//    public void toAppletDimension(BoundingBox box,int width,int height) {
//        float geoLeft = box.getLongMin();
//        float geoRigth = box.getLongMax();
//        float geoTop = box.getLatMin();
//        float geoBottom = box.getLatMax();
//        ArrayList<Vec3D> temp = new ArrayList<>(this.points.size());
//        for (int i = 0; i < this.points.size(); i++) {
//            float x = width * (this.points.get(i).x() - geoLeft) / (geoRigth - (geoLeft));
//            float y = (height - height * (this.points.get(i).y() - geoTop) / (geoBottom - geoTop));
//
////            temp.add(new Vec3D(x, y));
//            this.points.set(i, new Vec3D(x, y));
////            System.out.println(x + " " + y);
//        }
//    }

    public LineString toApplicationDimension(BoundingBox box, int width, int height) {
        float geoLeft = box.getLongMin();
        float geoRigth = box.getLongMax();
        float geoTop = box.getLatMin();
        float geoBottom = box.getLatMax();
        ArrayList<Vec3D> temp = new ArrayList<>(this.points.size());
        for (int i = 0; i < this.points.size(); i++) {
            float x = width * (this.points.get(i).x() - geoLeft) / (geoRigth - (geoLeft));
            float y = (height - height * (this.points.get(i).y() - geoTop) / (geoBottom - geoTop));

//            temp.add(new Vec3D(x, y));
            this.points.set(i, new Vec3D(x, y, 0));
//            System.out.println(x + " " + y);
        }
//        this.points = temp;
        return this;
    }

    public int getNumberOfLineString() {
        return this.points.size();
    }

}
