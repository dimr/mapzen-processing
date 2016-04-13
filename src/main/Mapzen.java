package main;

import main.layers.buildings.Building;
import main.layers.roads.Road;
import main.net.MapzenRequest;
import main.net.MapzenUrl;
import main.parsers.LayerParser;
import processing.core.PApplet;
import main.renderers.PolygonRenderer;
import main.renderers.RoadRenderer;
import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;
import main.geo.BoundingBox;

import java.util.ArrayList;

/**
 * Created by dimitris on 4/7/16.
 */
public class Mapzen {
    //will replaced with PApplet.width, PApplet.height
    private int width, height;
    private LayerParser parser;
    private MapzenRequest response;
    private MapzenUrl url;
    private BoundingBox box;
    private PApplet pa;
    private PolygonRenderer polygonRender;

    //width,height as this
    // numberOfLayer needs to be removed from here
    public Mapzen(int width, int height, MapzenUrl url) {
        this.url = url;
        this.response = new MapzenRequest(this.url.toString());
        System.out.println("----"+url.getLayers());

        //Parser needs to return all layers;
        this.parser = new LayerParser(response.getContent(), url.getLayers().size());
        parser.printBoundingBox();
        this.box = parser.getBoundingBox();
        for (int i = 0; i < this.parser.getRoads().size(); i++) {
//            parser.getRoads().get(0).getGeometry().createGeometryTypes();
            parser.getRoads().get(i).getGeometry().createGeometryTypes(box, width, height);
        }
//        for (Road r : this.parser.getRoads()) {
////            if (r.getGeometry().getType().equals("MultiLineString")) ;
//            System.out.println(r);
//        }

        System.out.println("MAP: " + parser.getBuildings().get(0).getGeometry());
        for (Building b : parser.getBuildings())
            b.getGeometry().createGeometryTypes(box, width, height);
    }


    public Mapzen(PApplet pa, MapzenUrl url) {
        this.pa = pa;
        this.url = url;
        this.response = new MapzenRequest(this.url.toString());

        //Parser needs to return all layers;
        this.parser = new LayerParser(response.getContent(), this.url.getLayers().size());
        parser.printBoundingBox();
        this.box = parser.getBoundingBox();
        for (int i = 0; i < this.parser.getRoads().size(); i++) {
//            parser.getRoads().get(0).getGeometry().createGeometryTypes();
            parser.getRoads().get(i).getGeometry().createGeometryTypes(box, pa.width, pa.height);
        }
//        for (Road r : this.parser.getRoads()) {
////            if (r.getGeometry().getType().equals("MultiLineString")) ;
//            System.out.println(r);
//        }
        for (int i = 0; i < this.parser.getBuildings().size(); i++) {
            parser.getBuildings().get(i).getGeometry().createGeometryTypes(box, pa.width, pa.height);
        }


    }


    public ArrayList<Road> getRoadsLayers() {
        return this.parser.getRoads();
    }

    public ArrayList<Building> getBuildingsLayer() {
        return this.parser.getBuildings();
    }

    public void renderRoads() {
        for (Road road : parser.getRoads())
            new RoadRenderer(this.pa, road).draw();
    }

    public void renderPolygons() {
        for (int i = 0; i < parser.getBuildings().size(); i++) {
            Polygon2D p = parser.getBuildings().get(i).getGeometry().getPolygon().ToxiPolygon();
            Vec2D center = p.getCentroid();
            assert p != null : "Null Polygons";
//            System.out.println(p.getArea());
//            System.out.println(parser.getBuildings().get(i));
//            pa.pushMatrix();
//            pa.noStroke();
//            pa.translate(center.x,center.y,10);
//            pa.ellipse(0,0,3,3);
//            pa.popMatrix();

            new PolygonRenderer(this.pa, parser.getBuildings().get(i)).draw();
        }

    }


    @Override
    public String toString() {
        return "Mapzen{" +
                "roadsLayers=" + getRoadsLayers().size() +
                ", buildingsLayer=" + getBuildingsLayer().size() +
                '}';
    }
}
