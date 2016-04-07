package parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import layers.buildings.Building;
import layers.roads.Road;
import utils.geo.BoundingBox;

/**
 * Created by dimitris on 4/4/16.
 */
public class LayerParser {

    private ObjectMapper buildingMapper;
    private ObjectMapper roadMapper;
    private int numberOfLayers;
    private ArrayList<Building> buildings;
    private ArrayList<Road> roads;
    private BoundingBox box = new BoundingBox();

    public LayerParser(String content, int numberOfLayers) {
        this.numberOfLayers = numberOfLayers;
        if (this.numberOfLayers == 1) {
            buildingMapper = new ObjectMapper();
            buildingMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                JsonNode actualObj = buildingMapper.readTree(content);
                JsonNode features = actualObj.get("features");
//                System.out.println("--"+features);
                buildings = buildingMapper.readValue(features.toString(), new TypeReference<List<Building>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            buildingMapper = new ObjectMapper();
            roadMapper = new ObjectMapper();
            buildingMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            roadMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode actualObj = null;
            try {
                actualObj = roadMapper.readTree(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonNode b = actualObj.get("buildings").get("features");
            JsonNode r = actualObj.get("roads").get("features");
//            System.out.println(b);
//            System.out.println(r);
            try {
                buildings = buildingMapper.readValue(b.toString(), new TypeReference<List<Building>>() {
                });
                roads = roadMapper.readValue(r.toString(), new TypeReference<List<Road>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        buildBoundingBox();
    }//CONSTRUCTOR

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    private void buildBoundingBox() {
        for (Building b : this.buildings) {
            //System.out.println(b);
            if (b.getGeometry().getType().equals("Polygon")) {
                ArrayList tempCoordinages = (ArrayList) b.getGeometry().getCoordinates().get(0);
                for (int i = 0; i < tempCoordinages.size(); i++) {
                    Float tempLong = ((Double) ((ArrayList) tempCoordinages.get(i)).get(0)).floatValue();
                    Float tempLat = ((Double) ((ArrayList) tempCoordinages.get(i)).get(1)).floatValue();
                    box.addLongitudeValue(tempLong);
                    box.addLatitudeValue(tempLat);
                }
            }
        }
        box.sortThem();
    }

    public float geoLeft(){
        return this.box.getLongMin();
    }

    public float geoRight(){
        return this.box.getLongMax();
    }


    public float geoTop(){
        return this.box.getLatMin();
    }

    public float geoBottom(){
        return this.box.getLatMax();
    }

    public void printBoundingBox(){
        System.out.println(this.toString());
    }

    public BoundingBox getBoundingBox(){
        return this.box;
    }


    @Override
    public String toString() {
        return "LayerParser{" +
                "geoLeft=" + geoLeft() +
                ", geoRight=" + geoRight() +
                ", geoTop=" + geoTop() +
                ", geoBottom=" + geoBottom() +
                '}';
    }
}
