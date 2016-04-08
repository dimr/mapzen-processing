import layers.roads.Road;
import net.MapzenRequest;
import net.MapzenUrl;
import net.MapzenUrlBuilder;
import parsers.LayerParser;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by dimitris on 4/4/16.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] r) {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        Mapzen mapzen = new Mapzen(800, 600, url, 2); //this PApplet size
        System.out.println("--------------\n"+mapzen);
    }
}
