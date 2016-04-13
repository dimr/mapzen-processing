
package main.layers.roads;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
//import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Properties {

    private String kind;
    private String name;
    private Integer sortKey;
    private String source;
    private String isTunnel;
    private String railway;
    private Integer id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Properties() {
    }

    /**
     * 
     * @param id
     * @param isTunnel
     * @param railway
     * @param source
     * @param name
     * @param sortKey
     * @param kind
     */
    public Properties(String kind, String name, Integer sortKey, String source, String isTunnel, String railway, Integer id) {
        this.kind = kind;
        this.name = name;
        this.sortKey = sortKey;
        this.source = source;
        this.isTunnel = isTunnel;
        this.railway = railway;
        this.id = id;
    }

    /**
     * 
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The sortKey
     */
    public Integer getSortKey() {
        return sortKey;
    }

    /**
     * 
     * @param sortKey
     *     The sort_key
     */
    public void setSortKey(Integer sortKey) {
        this.sortKey = sortKey;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The isTunnel
     */
    public String getIsTunnel() {
        return isTunnel;
    }

    /**
     * 
     * @param isTunnel
     *     The is_tunnel
     */
    public void setIsTunnel(String isTunnel) {
        this.isTunnel = isTunnel;
    }

    /**
     * 
     * @return
     *     The railway
     */
    public String getRailway() {
        return railway;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", sortKey=" + sortKey +
                ", source='" + source + '\'' +
                ", isTunnel='" + isTunnel + '\'' +
                ", railway='" + railway + '\'' +
                ", id=" + id +
                '}';
    }

    /**
     * 
     * @param railway
     *     The railway
     */
    public void setRailway(String railway) {
        this.railway = railway;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
////        return ToStringBuilder.reflectionToString(this);
////    }

//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



}
