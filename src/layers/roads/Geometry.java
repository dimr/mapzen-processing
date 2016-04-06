
package layers.roads;

import toxi.geom.Vec2D;

import java.lang.reflect.Array;
import java.util.*;
import javax.annotation.Generated;
//import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Geometry {

    private String type;
    private List coordinates = new ArrayList<>();
    private ArrayList<Vec2D> appCoordinates = new ArrayList<Vec2D>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Geometry() {

    }

    /**
     * @param type
     * @param coordinates
     */
    public Geometry(String type, List coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The coordinates
     */
    public List getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
    /*
    all roads as LineStrings, format [ [], [] ,[]   ]
     */

    public void toVec2D() {
        if (this.type.equals("LineString")) {
            System.out.println("LineString: "+new LineString((ArrayList)this.getCoordinates()).getNumberOfLineString());
//            System.out.println(this.getCoordinates());
            for (Object tempVec : this.getCoordinates()) {
                ArrayList temp = (ArrayList) tempVec;
//                new LineString(temp);
//                System.out.println("LineString: "+new LineString((ArrayList)temp).getNumberOfLineString());
                //  System.out.println("-->"+new LineString(temp).getNumberOfLineString()+" "+temp.size());
//            float x = ((Float) ((Double) temp.get(0)).floatValue());
//            System.out.println(new Vec2D(((Float) ((Double) temp.get(0)).floatValue()), ((Float) ((Double) temp.get(1)).floatValue())));
                appCoordinates.add(new Vec2D(((Float) ((Double) temp.get(0)).floatValue()), ((Float) ((Double) temp.get(1)).floatValue())));
            }
        } else if (this.type.equals("MultiLineString")) {
            System.out.println("MultilineString:"+new MultiLineString((ArrayList)this.getCoordinates()).getLength());
            for (Object temp : this.getCoordinates()) {
                //System.out.println("MultineString: "+new MultiLineString((ArrayList)this.getCoordinates().get(0)).getLength());
                ArrayList arrTemp = (ArrayList) temp;
                for (Object t : arrTemp) {
                    ArrayList coords = (ArrayList) t;
                    appCoordinates.add(new Vec2D(((Float) ((Double) coords.get(0)).floatValue()), ((Float) ((Double) coords.get(1)).floatValue())));
                }
            }
//            System.out.println(this.type+" "+ Arrays.toString(this.getCoordinates().toArray()));
        }
//        return this.appCoordinates;

    }

    /**
     * @param coordinates The coordinates
     */
    public void setCoordinates(List coordinates) {
        this.coordinates = coordinates;
    }

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
