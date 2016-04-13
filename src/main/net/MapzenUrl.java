package main.net;

import main.geo.CoordinateConverter;
import remixlab.proscene.InteractiveFrame;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by dimitris on 4/3/16.
 */
public class MapzenUrl {

    private final String url = "http://vector.mapzen.com/osm/";
    private float longitude, latitude;
    private int zoom;
    private String key;
    private String layer;
    private int layerCounter = 0;
    private int maxLayers = 3;
    private ArrayList<String> layers = new ArrayList<String>();
    private StringJoiner joiner = new StringJoiner(",");
    private int mapzenLongitude,mapzenLatitude;

    public MapzenUrl() {

    }


//    public MapzenUrl(float longitude, float latitude, int zoom, String key, String layer) {
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.zoom = zoom;
//        this.key = key;
//        joiner = new StringJoiner(",");
//
//        setLayer(layer);
//    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = CoordinateConverter.long2Tile(longitude,this.zoom);
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = CoordinateConverter.lat2tile(latitude,this.zoom);
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
//        assert(zoom>13 && zoom<=16):"Not allowed zoom value";
        this.zoom = zoom;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLayer() {
        return String.join(",", this.layers);
    }

    public void setLayer(String layer) {
        this.layer = layer;
        System.out.println("Adding Layer: "+this.layer);
        this.layers.add(this.layer);
    }

    public ArrayList <String> getLayers(){
        return this.layers;
    }



    //need to remove conversion from here
    public String toString () {
        //private final String url = "http://vector.mapzen.com/osm/buildings/16/10484/25329.json?api_key=vector-tiles-9DKnALT";
        StringBuilder finalURL = new StringBuilder();
        finalURL.append(this.url);
        finalURL.append(this.getLayer()+"/");
        finalURL.append(this.zoom+"/");
        finalURL.append((int)this.longitude+"/");
        finalURL.append((int)this.latitude);
        finalURL.append(".json?api_key=");
        finalURL.append(this.getKey());
        return finalURL.toString();
    }

}
