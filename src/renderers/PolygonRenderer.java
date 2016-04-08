package renderers;

import geometry.types.buildings.Polygon;
import layers.buildings.Building;
import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.processing.ToxiclibsSupport;

/**
 * Created by dimitris on 4/8/16.
 */
public class PolygonRenderer {

    private ToxiclibsSupport toxi;
    private PApplet pa;
    private Building building;
    private Polygon polygon;

    public PolygonRenderer(PApplet pa, Building building) {
        this.pa = pa;
        this.toxi = new ToxiclibsSupport(this.pa);
        this.building = building;
        this.polygon = polygon;
    }


    public void draw() {
//        this.building.getGeometry().getPolygon().ToxiPolygon()
//        System.out.println("Rendering  Polygons");
//        if (this.building.getGeometry().getType().equals("Polygon")) {
            pa.fill(100);
//            pa.stroke(0,0,255);
        pa.noStroke();
//           this.toxi.polygon2D(this.building.getGeometry().getPolygon().ToxiPolygon());
        this.toxi.polygon2D(this.building.getGeometry().getPolygon().ToxiPolygon());
//            System.out.println(this.building.getGeometry().getPolygon());
//        }
//        pa.beginShape();
//        for(Vec2D v:this.building.getGeometry().getPolygon().polygonVecs()){
//            pa.vertex(v.x,v.y);
////            pa.pushMatrix();
////           pa.translate(0,0,100);
////            pa.vertex(v.x,v.y,0);
////            pa.popMatrix();
//        }
//        pa.endShape(pa.CLOSE);

        pa.fill(0,0,255);
        pa.beginShape();
        for(Vec2D v:this.building.getGeometry().getPolygon().polygonVecs()){
            pa.vertex(v.x,v.y,0);
        }
        pa.endShape(pa.CLOSE);

    }

}
