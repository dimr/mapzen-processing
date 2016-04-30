package main;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import main.Mapzen;
import main.net.MapzenUrl;
import main.net.MapzenUrlBuilder;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.data.JSONObject;
import toxi.geom.Vec3D;
import toxi.processing.ToxiclibsSupport;

import java.util.List;

/**
 * Created by dimitris on 4/7/16.
 */
public class ProcessingMain extends PApplet {
    PeasyCam cam;
    private Mapzen mapzen, mapzen2;
    ToxiclibsSupport toxi;
    Vec3D TweetCoordinate = new Vec3D(-122.41422f, 37.77696f, 0);
    Vec3D convertedTweetCoordiante;

    @Override
    public void setup() {

        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setKey("vector-tiles-PADQnWp").buildUrl();
        //New York
//        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-73.98658f).setLatitude(40.7306f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        mapzen = new Mapzen(this, url); //this PApplet size
//        System.out.println(mapzen.getBuildingsLayer().size());

        cam = new PeasyCam(this, width / 2, height / 2, 0, 1100);
        cam.setMinimumDistance(50);
        cam.setMaximumDistance(2500);
//        mapzen.renderRoads();
//        mapzen.renderPolygons();
        toxi = new ToxiclibsSupport(this);
//        JSONObject json=loadJSONObject("/home/dimitris/data/NetDownloads/san-francisco_california.imposm-geojson/marketStreet.geojson");
        smooth();
        convertedTweetCoordiante = mapzen.convert(TweetCoordinate);
    }


    @Override
    public void draw() {
        background(0);
//        mapzen.renderRoads();
        pushMatrix();
        pushStyle();
//        fill(0,255,0);
        strokeWeight(10);
        stroke(0, 255, 0);
//        noStroke();
        translate(convertedTweetCoordiante.x(), convertedTweetCoordiante.y(), 0);
//        line(mapzen.randomPoint.x,mapzen.randomPoint.x,0,mapzen.randomPoint.x,mapzen.randomPoint.x,120);
        line(0, 0, 0, 0, 0, 120);
//        sphere(10);
        popStyle();
        popMatrix();
//        fill(34);
//        for (Building b:mapzen.getBuildingsLayer()){
//            toxi.polygon2D(b.getGeometry().getPolygon().ToxiPolygon());
//        }
        mapzen.renderPolygons(190, 190, 188, 200);
        mapzen.drawGeoRoads(240, 0, 0, 1);
//        pushMatrix();
//        translate(width/2,height/2,200);
//        sphere(50);
//        popMatrix();


    }

    public void settings() {
//        size(1300,850);
        size(1900, 1050, "processing.opengl.PGraphics3D");
//        fullScreen(P3D,2);

    }

    public static void main(String[] args) {
        PApplet.main(new String[]{ProcessingMain.class.getName()});
    }
}
