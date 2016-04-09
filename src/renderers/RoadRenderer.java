package renderers;

import geometry.types.roads.LineString;
import layers.roads.Road;
import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.geom.Vec3D;
import toxi.processing.ToxiclibsSupport;

import java.util.ArrayList;

/**
 * Created by dimitris on 4/6/16.
 */
public class RoadRenderer {
    PApplet pa;
    private ToxiclibsSupport toxi;
    private Road road;

    public RoadRenderer(PApplet pa, Road road) {
        this.pa = pa;
        this.road = road;
        toxi = new ToxiclibsSupport(this.pa);
    }

    public void draw() {
        if (this.road.getGeometry().getType().equals("LineString")) {
//            for (int i=0; i<this.road.getGeometry().getLineString().getPoints().size();i++){
//
//            }
//            for (Vec2D v : this.road.getGeometry().getLineString().getPoints()) {
//                pa.strokeWeight(4);
//                pa.stroke(255, 0, 0);
//                pa.point(v.x, v.y);
//            }
//            pa.pushStyle();
            pa.stroke(255, 0, 0);
//            for (int i = 0; i < this.road.getGeometry().getLineString().getNumberOfLineString() ; i++) {
//                for (int j=0; j<this.road.getGeometry().getLineString().)
//                Vec3D from = this.road.getGeometry().getLineString().getPoints().get(i);
//                Vec3D to = this.road.getGeometry().getLineString().getPoints().get(i);
//                pa.line(from.x, from.y, from.z, to.x, to.x, to.z);
//            }
//            for (Vec3D v:this.road.getGeometry().getLineString().getPoints())
//            System.out.println(this.road.getGeometry().getLineString());
            ArrayList<Vec3D> points = this.road.getGeometry().getLineString().getPoints();

            for (int i=1; i<points.size(); i++){
                Vec3D from =points.get(i-1);
                Vec3D to =points.get(i);
                pa.line(from.x,from.y,from.z,to.x,to.y,to.z);
                }
            }
//            }
//            this.toxi.lineStrip2D(this.road.getGeometry().getLineString().getPoints());
//            System.out.println(this.road.getGeometry().getLineString());
//            this.toxi.lines2D();
//            pa.popStyle();
//            for (int i=0; i<this.road.getGeometry().getLineString().getPoints().size()-1; i++){
//                Vec2D start=this.road.getGeometry().getLineString().getPoints().get(i);
//                Vec2D end=this.road.getGeometry().getLineString().getPoints().get(i+1);
//                pa.line(start.x,start.y,start.x,start.y);
//                System.out.println();
//
//            }
//        } else if (this.road.getGeometry().getType().equals("MultiLineString")) {
//            pa.pushStyle();
//            pa.stroke(0 ,250, 0);
//            for (LineString l : this.road.getGeometry().getMultiLineString().getLineStrings())
//                LIne(l.getPoints());
//            pa.popStyle();
        }
        }


