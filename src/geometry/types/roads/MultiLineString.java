package geometry.types.roads;

import jdk.nashorn.internal.runtime.linker.BoundCallable;
import utils.geo.BoundingBox;

import java.util.ArrayList;

/**
 * Created by dimitris on 4/6/16.
 */
public class MultiLineString {

    private ArrayList<LineString> lineStrings = new ArrayList<>();

    public MultiLineString(ArrayList p, BoundingBox box,int width, int height) {
        for (Object o : p) {
            this.lineStrings.add(new LineString((ArrayList) o,box,width,height));
        }
    }

    public MultiLineString(LineString lineString){
        this.lineStrings=new ArrayList<>();
        this.lineStrings.add(lineString);

//        System.out.println("-"+lineString);
    }





    public int getLength() {
        return this.lineStrings.size();
    }

    public ArrayList<LineString> getLineStrings() {
        return this.lineStrings;
    }



    @Override
    public String toString() {
        return "MultiLineString{" +
                "lineStrings=" + lineStrings +
                ", length=" + getLength() +
                '}';
    }
}
