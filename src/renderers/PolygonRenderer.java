package renderers;

import geometry.types.buildings.Polygon;
import layers.buildings.Building;
import processing.core.PApplet;
import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;
import toxi.geom.Vec3D;
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
        if (this.building.getGeometry().getType().equals("Polygon")) {
//            System.out.println(this.building.getProperties().getHeight());
            pa.pushStyle();
            pa.fill(0, 0, 210);
//            pa.stroke(0,0,255);
            pa.noStroke();
//           this.toxi.polygon2D(this.building.getGeometry().getPolygon().ToxiPolygon());
            Polygon2D poly = this.building.getGeometry().getPolygon().ToxiPolygon();
            assert poly.getNumVertices() != 0 : this.building;
            this.toxi.polygon2D(this.building.getGeometry().getPolygon().ToxiPolygon());
            pa.popStyle();
            if (poly.getNumVertices() >= 4) {
                Vec3D one = new Vec3D(poly.get(0).x, poly.get(0).y, 0);
                Vec3D oneTop = new Vec3D(one.x, one.y, 20);

                Vec3D two = new Vec3D(poly.get(1).x, poly.get(1).y, 0);
                Vec3D twoTop = new Vec3D(poly.get(1).x, poly.get(1).y, 20);
                pa.pushStyle();
                pa.fill(190);
                pa.beginShape(pa.QUAD_STRIP);
                pa.vertex(one.x, one.y, one.z);
                pa.vertex(oneTop.x, oneTop.y, oneTop.z);
                pa.vertex(two.x, two.y, two.z);
                pa.vertex(two.x, twoTop.y, twoTop.z);
                pa.popStyle();
                pa.endShape();

                Vec3D three = new Vec3D(poly.get(2).x, poly.get(2).y, 0);
                Vec3D four = new Vec3D(poly.get(3).x, poly.get(3).y, 0);
//            pa.pushStyle();
//            pa.strokeWeight(2);
//            pa.stroke(255,0,0);
//            this.toxi.line(one,new Vec3D(poly.get(0).x,poly.get(0).y,30));
//            pa.popStyle();
                float height = this.building.getProperties().getHeight()==null?20:Float.parseFloat(this.building.getProperties().getHeight());
                for (int i = 0; i < poly.getNumVertices() - 1; i++) {
                    Vec3D first = new Vec3D(poly.get(i).x, poly.get(i).y, 0);
                    Vec3D firstTop = new Vec3D(first.x, first.y, height);
                    Vec3D second = new Vec3D(poly.get(i + 1).x, poly.get(i + 1).y, 0);
                    Vec3D secondTop = new Vec3D(second.x, second.y, height);
                    pa.pushStyle();
                    pa.fill(190);
//                    pa.noStroke();
                    pa.beginShape();
                    pa.vertex(first.x, first.y, first.z);
                    pa.vertex(firstTop.x, firstTop.y, firstTop.z);
                    pa.vertex(secondTop.x, secondTop.y, secondTop.z);
                    pa.vertex(second.x, second.y, second.z);
                    pa.popStyle();
                    pa.endShape(pa.CLOSE);
                }
                Polygon2D current = this.building.getGeometry().getPolygon().ToxiPolygon();
                Vec3D first = new Vec3D(current.get(0).x, current.get(0).y, 0);
                Vec3D firstTop = new Vec3D(first.x, first.y, height);
                Vec3D last = new Vec3D(current.get(current.getNumVertices() - 1).x, current.get(current.getNumVertices() - 1).y, 0);
                Vec3D lastTop = new Vec3D(last.x, last.y, height);
                pa.pushStyle();
                pa.fill(90);
                pa.noStroke();
                pa.beginShape();
                pa.vertex(first.x, first.y, first.z);
                pa.vertex(firstTop.x, firstTop.y, firstTop.z);
                pa.vertex(lastTop.x, lastTop.y, lastTop.z);
                pa.vertex(last.x, last.y, last.z);
                pa.popStyle();
                pa.endShape(pa.CLOSE);


                pa.pushStyle();
                pa.fill(90);
                pa.noStroke();
                Polygon2D topPoly = this.building.getGeometry().getPolygon().ToxiPolygon().copy();
                pa.pushMatrix();
                pa.translate(0, 0, height);
                toxi.polygon2D(topPoly);
                pa.popMatrix();
            }
        }

    }

}
