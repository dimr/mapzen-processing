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

    public static void main(String[] r) throws IOException {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        MapzenRequest response = new MapzenRequest(url.toString());
        LayerParser parser = new LayerParser(response.getContent(), 2);
        parser.printBoundingBox();



//        System.out.println(parser.getRoads().get(0));
//        System.out.println(parser.getBuildings().get(0));
//        System.out.println(parser.getRoads().get(1));
        for (Road rr : parser.getRoads())
            rr.getGeometry().createGeometryTypes();

    }
}
