import layers.buildings.Building;
import layers.roads.Road;
import net.MapzenRequest;
import net.MapzenUrl;
import parsers.LayerParser;
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
    //width,height as this
    // numberOfLayer needs to be removed from here
    public Mapzen(int width, int height, MapzenUrl url, int numberOfLayers) {
        this.url = url;
        this.response = new MapzenRequest(this.url.toString());

        //Parser needs to return all layers;
        this.parser = new LayerParser(response.getContent(), numberOfLayers);
        parser.printBoundingBox();
        this.box = parser.getBoundingBox();
        for (int i=0; i<this.parser.getRoads().size(); i++){
//            parser.getRoads().get(0).getGeometry().createGeometryTypes();
            parser.getRoads().get(i).getGeometry().createGeometryTypes(box,width,height);
        }
        for (Road r:this.parser.getRoads()){
            System.out.println(r.getGeometry());
        }
    }

    public ArrayList<Road> getRoadsLayers(){
        return this.parser.getRoads();
    }

    public ArrayList<Building> getBuildingsLayer(){
        return this.parser.getBuildings();
    }


    @Override
    public String toString() {
        return "Mapzen{" +
                "roadsLayers=" + getRoadsLayers().size() +
                ", buildingsLayer=" + getBuildingsLayer().size() +
                '}';
    }
}
