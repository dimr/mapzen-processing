import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by dimitris on 4/3/16.
 */
public class MapzenRequest {

    public static void main(String[] r) throws IOException {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(16).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        System.out.println("LAYERS: " + url.getLayer());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url.toString());
        CloseableHttpResponse response = httpclient.execute(httpget);
        String entity = (EntityUtils.toString(response.getEntity()));
        System.out.println(response.getHeaders("Content-Type")[0].getValue().equals("application/json"));
        System.out.println(response.getStatusLine().getStatusCode() == 200);
        response.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(entity.toString());
//            System.out.println(actualObj);
        if (url.getLayers().size() == 1) {
            System.out.println(actualObj.get("features"));
        } else {
//            System.out.println(actualObj.get("roads"));
//            System.out.println(actualObj.get("buildings"));
            for (String l : url.getLayers()) {
                System.out.println("--"+l);
                System.out.println(actualObj.get(l).get("features").get(0).get("properties"));
            }
        }

    }
}
