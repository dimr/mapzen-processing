import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Entity;
import processing.data.JSONObject;


/**
 * Created by dimitris on 4/2/16.
 */
public class GsonMain {

    public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException {
        String url = "http://vector.mapzen.com/osm/buildings/16/10484/25329.json?api_key=vector-tiles-9DKnALT";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpget);
        String entity = (EntityUtils.toString(response.getEntity()));
        response.close();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(entity.toString());
        Iterator it = actualObj.iterator();
        JsonFactory factory = new JsonFactory();
        JsonParser parser=null;
        while (it.hasNext()) {
            JsonNode j = (JsonNode) it.next();
            for (final JsonNode n : j) {
//                System.out.println(n);
                JsonNode geometry = n.get("geometry");
                JsonNode properties = n.get("properties");
                parser=factory.createParser(properties.asText());
//                if (geometry.get("type").textValue().equals("Polygon"))
//                    System.out.println(geometry.get("type").textValue().equals("Polygon") + " " + geometry.get("coordinates"));
               // Properties pr=mapper.readValue(parser,Properties.class);
                System.out.println(properties);
            }
        }
    }
}
