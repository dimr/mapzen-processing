import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.layers.buildings.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import main.net.MapzenUrl;
import main.net.MapzenUrlBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * Created by dimitris on 4/2/16.
 */
public class GsonMain {

    public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException {
        MapzenUrl url= new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(16).setLayer("buildings").setKey("vector-tiles-PADQnWp").buildUrl();
        System.out.println("LAYERS: "+url.getLayer());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url.toString());
        CloseableHttpResponse response = httpclient.execute(httpget);
        String entity = (EntityUtils.toString(response.getEntity()));
        System.out.println(response.getHeaders("Content-Type")[0].getValue().equals("application/json"));
        System.out.println(response.getStatusLine().getStatusCode()==200);

        response.close();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(entity.toString());

        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = actualObj.fields();
        while (fieldsIterator.hasNext()) {

            Map.Entry<String,JsonNode> field = fieldsIterator.next();
            System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
        }
        Iterator it = actualObj.iterator();
        JsonFactory factory = new JsonFactory();
        for (int i = 0; i < 15; i++) {

            JsonParser parser=factory.createParser(actualObj.get("features").get(i).get("properties").toString());
          //  System.out.println(actualObj.get("features").get(0).get("properties"));
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Properties p = mapper.readValue(parser,Properties.class);
            System.out.println("\n---"+p.toString());
        }

//        while (it.hasNext()) {
//        System.out.println(parser.toString());
//            JsonNode j = (JsonNode) it.next();
//            for (final JsonNode n : j) {
////                System.out.println(n);
//                JsonNode geometry = n.get("geometry");
//                JsonNode properties = n.get("properties");
//                parser=factory.createParser(properties.asText());
//                while (!parser.isClosed()){
//                    JsonToken to=parser.nextToken();
//                    System.out.println(to);
//                }
////                if (geometry.get("type").textValue().equals("Polygon"))
////                    System.out.println(geometry.get("type").textValue().equals("Polygon") + " " + geometry.get("coordinates"));
//               // Properties pr=mapper.readValue(parser,Properties.class);
//               // System.out.println(properties);
//            }
//        }
    }
}
