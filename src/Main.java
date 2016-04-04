import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitris on 4/4/16.
 */
public class Main {
    public static void main(String[] r) throws IOException {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(16).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        MapzenRequest response = new MapzenRequest(url.toString());
        LayerParser parser = new LayerParser(response.getContent(), 2);
        parser.printBoundingBox();

    }
}
