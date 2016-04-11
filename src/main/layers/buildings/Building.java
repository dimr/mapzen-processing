package main.layers.buildings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dimitris on 4/3/16.
 */
public class Building {
    private Geometry geometry;
    private String type;
    private Properties properties;

    @Override
    public String toString() {
        return "Building{" +
                "geometry=" + geometry +
                ", type='" + type + '\'' +
                ", properties=" + properties +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Building() {
    }

    /**
     *
     * @param properties
     * @param type
     * @param geometry
     */
    public Building(Geometry geometry, String type, Properties properties) {
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

}
