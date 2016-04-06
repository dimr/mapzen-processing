package layers.roads;

import layers.roads.LineString;

import java.util.ArrayList;

/**
 * Created by dimitris on 4/6/16.
 */
public class MultiLineString {

    private ArrayList<LineString> lineStrings;
    public MultiLineString(ArrayList p){
        this.lineStrings=new ArrayList<>();
        for (Object o:p){
            this.lineStrings.add(new LineString((ArrayList)o));
        }
    }

    public int getLength(){
        return this.lineStrings.size();
    }
}
