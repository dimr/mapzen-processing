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



    public static void main(String [] a){
        MapzenUrl url= new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(16).setLayer("buildings").setKey("vector-tiles-PADQnWp").setLayer("water").buildUrl();
        System.out.println(url.getLayer());
        System.out.println(url.toString());
//        MapzenUrl url= new MapzenUrl(43.54f, 432.2342f, 15, "SADAS", "water");
//        url.setLayer("building");
//        url.setLayer("earth");
//        System.out.println(url.getLayer());
    }
}