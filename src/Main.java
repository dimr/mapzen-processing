import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import layers.roads.Road;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by dimitris on 4/4/16.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] r) throws IOException {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(14).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        MapzenRequest response = new MapzenRequest(url.toString());
        LayerParser parser = new LayerParser(response.getContent(), 2);
        parser.printBoundingBox();



        System.out.println(parser.getRoads().get(0));
        System.out.println(parser.getBuildings().get(0));
        System.out.println(parser.getRoads().get(1));
        for (Road rr : parser.getRoads())
            System.out.println(rr.getGeometry().getCoordinates());


    }
}
