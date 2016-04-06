
package layers.roads;

import geometry.types.roads.LineString;
import geometry.types.roads.MultiLineString;
import toxi.geom.Vec2D;

import java.util.*;
import javax.annotation.Generated;
//import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Geometry {

    private String type;
    private List coordinates = new ArrayList<>();
    private ArrayList<LineString> lineStrings, multiLineStrings;
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
            System.out.println("LineString: "+new LineString((ArrayList)this.getCoordinates()).toString());
        } else if (this.type.equals("MultiLineString")) {
            System.out.println("MultilineString:"+new MultiLineString((ArrayList)this.getCoordinates()).toString());
        }
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
