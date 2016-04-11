package tests;

import main.Mapzen;
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
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        assertEquals("zoom", 13, url.getZoom());
        assertEquals("buildings", url.getLayers().get(0));
        assertEquals("roads",url.getLayers().get(1));
        System.out.println(url.toString());
        url.setLayer("water");
        assertEquals("water", url.getLayers().get(2));
        System.out.println("\n"+url.toString());
        url.setLayer("earth");
        System.out.println("\n"+url.toString());
    }

    @Test
    public void testRequest() {
//        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        String url ="http://vector.mapzen.com/osm/buildings,roads/13/1310/3166.json?api_key=vector-tiles-PADQnWp";
        MapzenRequest re=new MapzenRequest(url.toString());
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