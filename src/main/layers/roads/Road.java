
package main.layers.roads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
//import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Road {

    private Geometry geometry;
    private String type;
    private Properties properties;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



    /**
     * No args constructor for use in serialization
     * 
     */
    public Road() {
    }

    /**
     * 
     * @param properties
     * @param type
     * @param geometry
     */
    public Road(Geometry geometry, String type, Properties properties) {
        this.geometry = geometry;
        this.type = type;
        this.properties = properties;
    }

    /**
     * 
     * @return
     *     The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * 
     * @param geometry
     *     The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * 
     * @param properties
     *     The properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
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


    @Override
    public String toString() {
        return "Road{" +
                "geometry=" + geometry +
                ", type='" + type + '\'' +
                ", properties=" + properties +
                '}';
    }


}
