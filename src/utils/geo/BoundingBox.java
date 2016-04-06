package utils.geo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dimitris on 4/4/16.
 */
public class BoundingBox {



    private ArrayList<Float> longitudes=new ArrayList<>();
    private ArrayList<Float> latitudes=new ArrayList<>();
    private float min=Float.MAX_VALUE;
    private float max=Float.MIN_VALUE;

    public BoundingBox(ArrayList<Float> longitudes, ArrayList<Float> latitudes) {
        this.longitudes = longitudes;
        this.latitudes = latitudes;
    }

    public BoundingBox() {
    }

    public void addLongitudeValue(float longitude){
        this.longitudes.add(longitude);
    }

    public void addLatitudeValue(float latitude){
        this.latitudes.add(latitude);
    }

    public ArrayList getLongitudes(){
        return this.longitudes;
    }
    public ArrayList getLatitudes(){
        return this.latitudes;
    }

    public int longitudeSize(){
        return this.longitudes.size();
    }

    public int latitudeSize(){
        return this.latitudes.size();
    }

    public void sortThem(){
        Collections.sort(this.latitudes);
        Collections.sort(this.longitudes);
    }
    public float getLongMin() {
        return this.longitudes.get(0);
    }



    public float getLongMax() {
        return this.longitudes.get(this.longitudeSize()-1);
    }



    public float getLatMin() {
        return this.latitudes.get(0);
    }



    public float getLatMax() {
        return this.latitudes.get(this.latitudeSize()-1);
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "longMin=" + getLongMin() +
                ", longMax=" + getLongMax() +
                ", latMin=" + getLatMin() +
                ", latMax=" + getLatMax() +
                '}';
    }
}
