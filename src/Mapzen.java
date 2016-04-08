import layers.buildings.Building;
import layers.roads.Road;
import net.MapzenRequest;
import net.MapzenUrl;
import parsers.LayerParser;
import processing.core.PApplet;
import renderers.PolygonRenderer;
import renderers.RoadRenderer;
import utils.geo.BoundingBox;

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

    //width,height as this
    // numberOfLayer needs to be removed from here
    public Mapzen(int width, int height, MapzenUrl url, int numberOfLayers) {
        this.url = url;
        this.response = new MapzenRequest(this.url.toString());

        //Parser needs to return all layers;
        this.parser = new LayerParser(response.getContent(), numberOfLayers);
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
            b.getGeometry().createGeometryTypes(box,width,height);
    }


    public Mapzen(PApplet pa, MapzenUrl url, int numberOfLayers) {
        this.pa = pa;
        this.url = url;
        this.response = new MapzenRequest(this.url.toString());

        //Parser needs to return all layers;
        this.parser = new LayerParser(response.getContent(), numberOfLayers);
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
        for (int i=0; i<this.parser.getBuildings().size();  i++){
            parser.getBuildings().get(i).getGeometry().createGeometryTypes(box,pa.width,pa.height);
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

    public void renderPolygons(){
        for (Building building:parser.getBuildings())
            new PolygonRenderer(this.pa,building).draw();
    }

    @Override
    public String toString() {
        return "Mapzen{" +
                "roadsLayers=" + getRoadsLayers().size() +
                ", buildingsLayer=" + getBuildingsLayer().size() +
                '}';
    }
}
