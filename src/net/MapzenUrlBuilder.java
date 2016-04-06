package net;

public class MapzenUrlBuilder {
    private float longitude;
    private float latitude;
    private int zoom;
    private String key;
    private String layer;
    MapzenUrl url=new MapzenUrl();

    public MapzenUrlBuilder setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public MapzenUrlBuilder setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public MapzenUrlBuilder setZoom(int zoom) {
        this.zoom = zoom;
        return this;
    }

    public MapzenUrlBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public MapzenUrlBuilder setLayer(String layer) {
        this.url.setLayer(layer);
        this.layer = this.url.getLayer();
        return this;
    }

    private String constructUrl(){
        StringBuilder url=new StringBuilder();
        return "";
    }

    public MapzenUrl buildUrl() {
        //url= new MapzenUrl();
        url.setKey(this.key);
        url.setLatitude(this.latitude);
        url.setLongitude(this.longitude);
        url.setZoom(this.zoom);
        return url;
    }
    
}