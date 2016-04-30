package main;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import main.layers.buildings.Building;
import main.layers.roads.Road;
import main.net.MapzenRequest;
import main.net.MapzenUrl;
import main.parsers.LayerParser;
import main.types.roads.LineString;
import main.types.roads.MultiLineString;
import processing.core.PApplet;
import main.renderers.PolygonRenderer;
import main.renderers.RoadRenderer;
import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;
import main.geo.BoundingBox;
import toxi.geom.Vec3D;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    //    Vec3D randomPoint = new Vec3D(-122.39197075f, 37.78287945f, 0);
    Vec3D randomPoint = new Vec3D(-122.41422f, 37.77696f, 0);
    ArrayList<LineString> geoRoads;

    //    37.77696, -122.41422
    //width,height as this
    // numberOfLayer needs to be removed from here
    public Mapzen(int width, int height, MapzenUrl url) {
        this.url = url;
        this.response = new MapzenRequest(this.url.toString());
//        System.out.println("----" + url.getLayers());

        //Parser needs to return all layers;
        this.parser = new LayerParser(response.getContent(), this.url.getLayers());
        parser.printBoundingBox();
        this.box = parser.getBoundingBox();
//        for (int i = 0; i < this.parser.getRoads().size(); i++) {
////            parser.getRoads().get(0).getGeometry().createGeometryTypes();
//            parser.getRoads().get(i).getGeometry().createGeometryTypes(box, width, height);
//        }
//        for (Road r : this.parser.getRoads()) {
////            if (r.getGeometry().getType().equals("MultiLineString")) ;
//            System.out.println(r);
//        }

        System.out.println("MAP: " + parser.getBuildings().get(0).getGeometry());
        for (Building b : parser.getBuildings())
            b.getGeometry().createGeometryTypes(box, width, height);

//        for (Road r : parser.getRoads())
//            System.out.println(r);

//        for (Building b:parser.getBuildings())
//            System.out.println(b);
    }


    public Mapzen(PApplet pa, MapzenUrl url) {
        this.pa = pa;
        this.url = url;
        this.response = new MapzenRequest(this.url.toString());

        //Parser needs to return all layers;
        this.parser = new LayerParser(response.getContent(), this.url.getLayers());
        parser.printBoundingBox();
        this.box = parser.getBoundingBox();
//        for (int i = 0; i < this.parser.getRoads().size(); i++) {
////            parser.getRoads().get(0).getGeometry().createGeometryTypes();
//            parser.getRoads().get(i).getGeometry().createGeometryTypes(box, pa.width, pa.height);
//        }
//        for (Road r : this.parser.getRoads()) {
////            if (r.getGeometry().getType().equals("MultiLineString")) ;
//            System.out.println(r);
//        }
        for (int i = 0; i < this.parser.getBuildings().size(); i++) {
            parser.getBuildings().get(i).getGeometry().createGeometryTypes(box, pa.width, pa.height);
        }
//        System.out.println(randomPoint);
//        convert();
        System.out.println(this.box);
//        System.out.println(randomPoint);
//        System.out.println(parser.getBuildings().get(0).getGeometry().getPolygon());
//        List<Feature> features = GeoJSONReader.loadData(this.pa, "/home/dimitris/data/NetDownloads/san-francisco_california.imposm-geojson/marketStreet.geojson");
        List<Feature> features = GeoJSONReader.loadData(this.pa, "data/marketStreet.geojson");
//        System.out.println(((ShapeFeature) features.get(4)).getLocations());
        geoRoads = new ArrayList<>();
        for (Feature f : features) {
            ShapeFeature line = (ShapeFeature) f;
            List<Location> location = (line.getLocations());
            List<Vec3D> lineString = new ArrayList<>();
            for (Location l : location) {
                lineString.add(new Vec3D(l.getLon(), l.getLat(), 0));
            }
            LineString lineS = new LineString();
            lineS.setPoints((ArrayList) lineString);
            geoRoads.add(lineS.toApplicationDimension(this.box, this.pa.width, this.pa.height));
        }

//        System.out.println(geoRoads.get(34).getPoints());

    }


    public ArrayList<Road> getRoadsLayers() {
        return this.parser.getRoads();
    }

    public ArrayList<Building> getBuildingsLayer() {
        return this.parser.getBuildings();
    }

//    public void renderRoads() {
//        for (Road road : parser.getRoads())
//            new RoadRenderer(this.pa, road).draw();
//    }

    public void drawGeoRoads(int r, int g, int b, int strokeWeight) {
        this.pa.pushStyle();
        pa.stroke(r, g, b);
        pa.strokeWeight(strokeWeight);
        for (LineString line : this.geoRoads) {
            for (int i = 1; i < line.getPoints().size(); i++) {
                Vec3D start = line.getPoints().get(i - 1);
                Vec3D stop = line.getPoints().get(i);
                this.pa.line(start.x, start.y, 0, stop.x, stop.y, 0);
            }
        }
        this.pa.popStyle();
    }

    public void renderPolygons(int r, int g, int b, int alpha) {
        for (int i = 0; i < parser.getBuildings().size(); i++) {
            Polygon2D p = parser.getBuildings().get(i).getGeometry().getPolygon().ToxiPolygon();
            assert p != null : "Null Polygons";
//            System.out.println(p.getArea());
//            System.out.println(parser.getBuildings().get(i));
//            pa.pushMatrix();
//            pa.noStroke();
//            pa.translate(center.x,center.y,10);
//            pa.ellipse(0,0,3,3);
//            pa.popMatrix();

            new PolygonRenderer(this.pa, parser.getBuildings().get(i)).draw(r, g, b, alpha);
        }

    }

    public void convert() {
        float geoLeft = this.box.getLongMin();
        float geoRigth = this.box.getLongMax();
        float geoTop = this.box.getLatMin();
        float geoBottom = this.box.getLatMax();
//        System.out.println(randomPoint + ">>>> " + box);
        float x = this.pa.width * (randomPoint.x() - geoLeft) / (geoRigth - (geoLeft));
        float y = (this.pa.height - this.pa.height * (randomPoint.y() - geoTop) / (geoBottom - geoTop));
        assert (x != 0) : "zero x";
        assert (y != 0) : "zero y";
//        System.out.println(x + " " + y);
        randomPoint.set(x, y, 0);
//        this.points = temp;
//        return this;
    }


    public Vec3D convert(Vec3D vec) {
        float geoLeft = this.box.getLongMin();
        float geoRigth = this.box.getLongMax();
        float geoTop = this.box.getLatMin();
        float geoBottom = this.box.getLatMax();
//        System.out.println(randomPoint + ">>>> " + box);
        float x = this.pa.width * (vec.x() - geoLeft) / (geoRigth - (geoLeft));
        float y = (this.pa.height - this.pa.height * (vec.y() - geoTop) / (geoBottom - geoTop));
        assert (x != 0) : "zero x";
        assert (y != 0) : "zero y";
//        System.out.println(x + " " + y);
        return new Vec3D(x, y, 0);
//        this.points = temp;
//        return this;
    }




    @Override
    public String toString() {
        return "Mapzen{" +
//                "roadsLayers=" + getRoadsLayers().size() +
                ", buildingsLayer=" + getBuildingsLayer().size() +
                '}';
    }
}
