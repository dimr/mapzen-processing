
package layers.roads;

import geometry.types.roads.LineString;
import geometry.types.roads.MultiLineString;
import toxi.geom.Vec2D;
import utils.geo.BoundingBox;

import java.util.*;
import javax.annotation.Generated;
//import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Geometry {

    private String type;
    private List coordinates = new ArrayList<>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private LineString lineString;
    private MultiLineString multiLineString;

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

    public void createGeometryTypes() {
        if (this.type.equals("LineString")) {
            lineString = new LineString((ArrayList) this.getCoordinates());
        } else if (this.type.equals("MultiLineString")) {
            multiLineString = new MultiLineString((ArrayList) this.getCoordinates());
        }
    }

//    public void toApplicationDimesions(BoundingBox box){
//        float geoLeft = box.getLongMin();
//        float geoRigth = box.getLongMax();
//        float geoTop = box.getLatMin();
//        float geoBottom = box.getLatMax();
//        for (Vec2D l: this.lineString.getPoints()){
//        }
//
//    }

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
