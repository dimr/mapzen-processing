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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dimitris on 4/3/16.
 */
public class MapzenRequest {

    public static void main(String[] r) throws IOException {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        System.out.println("LAYERS: " + url.getLayer() + "\n" + url.toString());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url.toString());
        CloseableHttpResponse response = httpclient.execute(httpget);
        String entity = (EntityUtils.toString(response.getEntity()));
        System.out.println(response.getHeaders("Content-Type")[0].getValue().equals("application/json"));
        System.out.println(response.getStatusLine().getStatusCode() == 200);
        response.close();

        ObjectMapper buildingsMapper = new ObjectMapper();
        ObjectMapper roadsMapper = new ObjectMapper();
        TypeFactory typeFactory = buildingsMapper.getTypeFactory();
        buildingsMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        roadsMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode actualObj = buildingsMapper.readTree(entity.toString());
//            System.out.println(actualObj);
        JsonFactory factory = new JsonFactory();
        JsonParser propertiesParser, geometryParser = null;
        List<Building> allBuildings = null;
        List<Building> allRoads = null;
        if (url.getLayers().size() == 1) {
            //System.out.println(actualObj.get("features"));
            JsonNode features = actualObj.get("features");
            System.out.println(features);

            allBuildings = buildingsMapper.readValue(features.toString(), new TypeReference<List<Building>>() {
            });
            for (JsonNode o : features) {

            }
//            if (features.isArray()) {
//                for (JsonNode o : features) {
////                    System.out.println(o.get("properties"));
////                    System.out.println(o.get("properties"));
//                    propertiesParser=factory.createParser(o.get("properties").toString());
//                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//                    Properties p = mapper.readValue(propertiesParser,Properties.class);
//                   System.out.println(p.toString());
//                    if (o.get("geometry").get("type").textValue().equals("Polygon")) {
//                        geometryParser = factory.createParser(o.get("geometry").toString());
//                         Geometry gg=mapper.readValue(geometryParser,Geometry.class);
////                        System.out.println(gg.getCoordinates());
//                    }
////                    System.out.println(p.toString()+" "+o.get("geometry"));
//                }
//            }


        } else {
//            allRoads = new List<Building>();
            JsonNode b = actualObj.get("buildings").get("features");
            JsonNode roads = actualObj.get("roads").get("features");
            System.out.println(b);
            System.out.println(roads);
            allBuildings = buildingsMapper.readValue(b.toString(), new TypeReference<List<Building>>() {
            });
            allRoads = roadsMapper.readValue(roads.toString(), new TypeReference<List<Building>>() {
            });
//            for (JsonNode o : b) {
//
//            }
//            for (String l : url.getLayers()) {
//                System.out.println("--" + l);
//                System.out.println(actualObj.get(l).get("features").get(0).get("properties"));
//                propertiesParser = factory.createParser(actualObj.get(l).get("features").get(0).get("properties").toString());
//                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//                Properties p = mapper.readValue(propertiesParser, Properties.class);
//                System.out.println(p.toString());
//            }
        }

        //Roads
        ArrayList<Float> logitude = new ArrayList<>();
        BoundingBox box=new BoundingBox();
        for (Building b : allRoads) {
            int l = b.getGeometry().getCoordinates().size();
            if (b.getGeometry().getType().equals("LineString")){
                //System.out.println(l+" "+b.getGeometry().getType()+" "+b.getGeometry().getCoordinates());
                List f=b.getGeometry().getCoordinates();
                for (int i=0; i<f.size(); i++){
                    Object tempLong =(((ArrayList)f.get(i)).get(0));
                    Float tl=((Double)tempLong).floatValue();
                    box.addLongitudeValue(tl);
                    box.addLatitudeValue(((Double)((ArrayList)f.get(i)).get(1)).floatValue());
//                    Float tt=(Float)tempLong;
//                    logitude.add((Float)tempLong);
//                    logitude.add((Float)((ArrayList)f.get(i)).get(0));
                    System.out.println(((ArrayList)f.get(i)).get(0)+" "+((ArrayList)f.get(i)).get(1));
                }
            }else if(b.getGeometry().getType().equals("MultiLineString")){
                List mb=((ArrayList)b.getGeometry().getCoordinates().get(0));
                for (int i=0; i<mb.size(); i++){
                    System.out.println(">>"+((ArrayList)mb.get(i)).get(0)+" "+((ArrayList)mb.get(i)).get(1));
                    Float tl=((Double)((ArrayList)mb.get(i)).get(0)).floatValue();
                    box.addLongitudeValue(tl);
                    box.addLatitudeValue(((Double)((ArrayList)mb.get(i)).get(1)).floatValue());
                }
            }
        }
        System.out.println(box.latitudeSize()+" "+box.longitudeSize());
        box.sortThem();
        System.out.println(box.getLongMin()+" "+box.getLongMax());
        System.out.println(box.getLatMin()+" "+box.getLatMax());
    }
}
