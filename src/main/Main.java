package main;

import main.net.MapzenUrl;
import main.net.MapzenUrlBuilder;
import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.*;
import java.util.logging.Logger;

/**
 * Created by dimitris on 4/4/16.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] r) {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).
                setLatitude(37.782281f).setZoom(13).setLayer("buildings").setKey("vector-tiles-PADQnWp").buildUrl();
        System.out.println(url);
        Mapzen mapzen = new Mapzen(800, 600, url); //this PApplet size
//        System.out.println("--------------\n"+mapzen);
        System.out.println("down here");

//        InputStream is = null;
//        try {
//            is = new FileInputStream("/home/dimitris/data/NetDownloads/san-francisco_california.imposm-geojson/marketStreet.geojson");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
//
//        String line = null;
//        try {
//            line = buf.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        StringBuilder sb = new StringBuilder();
//
//        while(line != null){
//            sb.append(line).append("\n");
//            try {
//                line = buf.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        String fileAsString = sb.toString();
//        System.out.println(fileAsString);
    }
}
