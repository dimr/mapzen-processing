package main.types.buildings;

import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;
import main.geo.BoundingBox;

import java.util.ArrayList;

/**
 * Created by dimitris on 4/7/16.
 */
public class Polygon {
    private Polygon2D polyogon;
    private ArrayList<Vec2D> vecs;


    public Polygon() {
        this.vecs = new ArrayList<>();
//        this.polyogon = new Polygon2D();
    }
//
//    public void add(Vec2D vec) {
//        this.polyogon.;
//        Polygon2D vv=new Polygon2D(vecs)
//
//    }

    public void collectPoints(Vec2D v) {
        this.vecs.add(v);
    }

    public void toApplicationDimension(BoundingBox box, int width, int height) {
        float geoLeft = box.getLongMin();
        float geoRigth = box.getLongMax();
        float geoTop = box.getLatMin();
        float geoBottom = box.getLatMax();
        ArrayList<Vec2D> temp = new ArrayList<>(this.vecs.size());
        for (int i = 0; i < this.vecs.size(); i++) {
//            System.out.println(this.vecs.get(i));
            float x = width * (this.vecs.get(i).x() - geoLeft) / (geoRigth - (geoLeft));
            float y = (height - height * (this.vecs.get(i).y() - geoTop) / (geoBottom - geoTop));

//            temp.add(new Vec2D(x, y));
            this.vecs.set(i, new Vec2D(x, y));
        }
//        this.points = temp;
//        return this;
    }

    public Polygon2D ToxiPolygon() {
        return new Polygon2D(vecs);
    }

    public ArrayList<Vec2D> polygonVecs() {
        return this.vecs;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "polyogon=" + polyogon +
                ", vecs=" + vecs +
                '}';
    }

}
