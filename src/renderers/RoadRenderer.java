package renderers;

import geometry.types.roads.LineString;
import layers.roads.Road;
import processing.core.PApplet;
import toxi.geom.Vec2D;
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
        toxi=new ToxiclibsSupport(this.pa);
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
            pa.pushStyle();
            pa.stroke(255,0,0);
            this.toxi.lineStrip2D(this.road.getGeometry().getLineString().getPoints());
//            this.toxi.lines2D();
            pa.popStyle();
//            for (int i=0; i<this.road.getGeometry().getLineString().getPoints().size()-1; i++){
//                Vec2D start=this.road.getGeometry().getLineString().getPoints().get(i);
//                Vec2D end=this.road.getGeometry().getLineString().getPoints().get(i+1);
//                pa.line(start.x,start.y,start.x,start.y);
//                System.out.println();
//
//            }
        }
    }
}
