package tests;

import main.net.MapzenRequest;
import main.net.MapzenUrl;
import main.net.MapzenUrlBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dimitris on 4/11/16.
 */
public class NetTests {

    @Test
    public void testURL(){
        int zoom=16;
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(zoom).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        assertEquals("zoom", zoom, url.getZoom());
        assertTrue("Logitude:",0!=url.getLongitude());
        assertTrue("Latitude:",0!=url.getLatitude());
        assertEquals("buildings", url.getLayers().get(0));
        assertEquals("roads",url.getLayers().get(1));
        System.out.println(url.toString());
        url.setLayer("water");
        assertEquals("water", url.getLayers().get(2));
        System.out.println("\n"+url.toString());
        url.setLayer("earth");
    }

    @Test //(expected = RuntimeException.class)
    public void testWrongUrlRequest() {
//        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        String url =String.format("http://vector.mapzen.com/osm/%s/16/1310/3166.json?api_key=vector-tiles-PADQnWp","buildings");
        MapzenRequest re=new MapzenRequest(url);
        assertEquals(200,re.getStatusCode());

//        Mapzen mapzen = new Mapzen(432, 523, url, 2);

//        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();

//        assertEquals("zoom", 13, url.getZoom());
//        assertEquals(new Float(url.getLatitude()), new Float(37.782281f));
//        assertEquals("buildings", url.getLayers().get(0));
//        url.setLayer("water");
//        assertEquals("water", url.getLayers().get(2));

    }

//    @Test
//    public void testBuildingSize() {
//        assertEquals("wrong number of buildings", 1260, mapzen.getBuildingsLayer().size());
//
//    }
}