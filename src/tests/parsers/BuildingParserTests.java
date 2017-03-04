package tests.parsers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.geo.BoundingBox;
import main.layers.buildings.Building;
import main.layers.roads.Road;
import main.parsers.LayerParser;
import org.junit.Assert;
import org.junit.Test;
import processing.data.JSONArray;
import processing.data.JSONObject;
import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static processing.core.PApplet.loadJSONObject;


/**
 * Created by dimitris on 4/22/16.
 */
public class BuildingParserTests {
    //    String building1 = "{\"type\":\"FeatureCollection\",\"features\":[{\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-122.40783714,37.78396490],[-122.40709972,37.78454962],[-122.40648823,37.78502621],[-122.40635016,37.78491631],[-122.40552767,37.78425506],[-122.40540523,37.78435126],[-122.40503153,37.78405528],[-122.40608103,37.78322511],[-122.40617275,37.78329938],[-122.40618308,37.78329178],[-122.40646766,37.78351549],[-122.40643110,37.78354517],[-122.40672117,37.78377051],[-122.40673141,37.78377860],[-122.40678064,37.78373949],[-122.40694611,37.78360736],[-122.40717158,37.78342760],[-122.40749363,37.78368780],[-122.40783714,37.78396490]]]},\"type\":\"Feature\",\"properties\":{\"kind\":\"retail\",\"area\":38946,\"sort_key\":475,\"addr_street\":\"Market Street\",\"height\":62.00000000,\"volume\":2414652,\"source\":\"openstreetmap.org\",\"min_zoom\":16,\"addr_housenumber\":\"845\",\"id\":332627424}}]}";
    String building1 = "{\"type\":\"FeatureCollection\",\"features\":[{\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-122.40783714,37.78396490],[-122.40709972,37.78454962],[-122.40648823,37.78502621],[-122.40635016,37.78491631],[-122.40552767,37.78425506],[-122.40540523,37.78435126],[-122.40503153,37.78405528],[-122.40608103,37.78322511],[-122.40617275,37.78329938],[-122.40618308,37.78329178],[-122.40783714,37.78396490]]]},\"type\":\"Feature\",\"properties\":{\"kind\":\"retail\",\"area\":38946,\"sort_key\":475,\"addr_street\":\"Market Street\",\"height\":62.00000000,\"volume\":2414652,\"source\":\"openstreetmap.org\",\"min_zoom\":16,\"addr_housenumber\":\"845\",\"id\":332627424}}]}";
    String customBuilding = "{\"type\":\"FeatureCollection\",\"features\":[{\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[ [32.4324,43.5436],[6.5345,3.5436],[6.5345,3.5436],[5.6433,54.63463] ,[5.6433,54.63463]]]},\"type\":\"Feature\",\"properties\":{\"kind\":\"retail\",\"area\":38946,\"sort_key\":475,\"addr_street\":\"Market Street\",\"height\":62.00000000,\"volume\":2414652,\"source\":\"openstreetmap.org\",\"min_zoom\":16,\"addr_housenumber\":\"845\",\"id\":332627424}}]}";
    String building2 = "{\"type\":\"FeatureCollection\",\"features\":[{\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-122.40513492,37.78291777],[-122.40384719,37.78392237],[-122.40332706,37.78363001],[-122.40305353,37.78344350],[-122.40301598,37.78339302],[-122.40301059,37.78331627],[-122.40304823,37.78324890],[-122.40309646,37.78318060],[-122.40317686,37.78310861],[-122.40322519,37.78305770],[-122.40326813,37.78308738],[-122.40343441,37.78291351],[-122.40348274,37.78295170],[-122.40365441,37.78273971],[-122.40371343,37.78278209],[-122.40386893,37.78256590],[-122.40391717,37.78260396],[-122.40409422,37.78237506],[-122.40413716,37.78240900],[-122.40434638,37.78224791],[-122.40513492,37.78291777]]]},\"type\":\"Feature\",\"properties\":{\"kind\":\"building\",\"name\":\"Moscone Convention Center West\",\"area\":27749,\"sort_key\":475,\"height\":50.00000000,\"volume\":1387450,\"source\":\"openstreetmap.org\",\"min_zoom\":16,\"id\":24306412}}]}";

    ArrayList<String> layers = new ArrayList<>();

    @Test
    public void testBuildingParser() {
        layers.add("buildings");
        int expectedSize = 4;
        LayerParser parser = new LayerParser(customBuilding, layers);
        assertEquals(1, parser.getBuildings().size());
        Building b = parser.getBuildings().get(0);
        BoundingBox box = parser.getBoundingBox();
        assertEquals("Polygon", b.getGeometry().getType());
        assertEquals("retail", b.getProperties().getKind());
        parser.getBuildings().get(0).getGeometry().createGeometryTypes(box, 1500, 500);
//        assertEquals("Polygon size", expectedSize, b.getGeometry().getPolygon().polygonVecs().size());
        Polygon2D poly = b.getGeometry().getPolygon().ToxiPolygon();
        //Polygon2D removes last coordinate, since first and last coordinate must be equal to close the polygon
//        assertThat(b.getGeometry().getPolygon().ToxiPolygon(), instanceOf(Polygon2D.class));
        Assert.assertEquals("Polygon2D vertices\n" + b.getGeometry().getPolygon().polygonVecs() + "\n" + poly.toString(), expectedSize, poly.getNumVertices());


        //Building2
        expectedSize = 21;
        parser = new LayerParser(building2, layers);
        assertEquals(1, parser.getBuildings().size());
        b = parser.getBuildings().get(0);
        box = parser.getBoundingBox();
        assertEquals("Polygon", b.getGeometry().getType());
        assertEquals("building", b.getProperties().getKind());
        parser.getBuildings().get(0).getGeometry().createGeometryTypes(box, 500, 500);
        assertEquals("Polygon size", expectedSize, b.getGeometry().getPolygon().polygonVecs().size());
        poly = b.getGeometry().getPolygon().ToxiPolygon();
        assertThat(b.getGeometry().getPolygon().ToxiPolygon(), instanceOf(Polygon2D.class));
//        Assert.assertEquals("Polygon2D vertices", expectedSize-1, poly.getNumVertices());
    }

    @Test
    public void testPolygon2DRemovesDuplicates() {
        ArrayList<Vec2D> points = new ArrayList<>();
        points.add(new Vec2D(0, 0));
        points.add(new Vec2D(3, 4));
        //add some duplicates
        points.add(new Vec2D(10, 10));
        points.add(new Vec2D(10, 10));
        points.add(new Vec2D(54, 45));
        points.add(new Vec2D(54, 45));
        points.add(new Vec2D(54, 45));
        points.add(new Vec2D(54, 45));
        points.add(new Vec2D(54, 45));
        points.add(new Vec2D(5434, 4));
        //remove duplicates
        Set<Vec2D> filtered = new HashSet<>(points);
        Polygon2D poly = new Polygon2D(points);
        Assert.assertEquals(poly.toString(), filtered.size(), poly.getNumVertices());
    }

    @Test
    public void testLocalFile() {
        System.out.println(new File(""));
        String content = readFiles("./testFiles/roads-16.json");
//        System.out.println(roads.getJSONObject(0));
        layers.add("roads");
        LayerParser parser = new LayerParser(content, layers);
        Road r = parser.getRoads().get(0);

    }

    @Test
    public void testLocalBuilding() {
        System.out.println(new File(""));
//        JSONObject json = loadJSONObject(new File("./testFiles/buildings-16.jsson"));
        String content = readFiles("./testFiles/buildings-16.json");

        layers.add("buildings");
        int expectedSize = 4;
        LayerParser parser = new LayerParser(content, layers);
//        assertEquals(1, parser.getBuildings().size());
        System.out.println(parser.getBuildings().size());
        Assert.assertEquals(245, parser.getBuildings().size());
        Building b = parser.getBuildings().get(0);
        System.out.println(b.getGeometry().getType());
//        Assert.assertEquals("retail",b.getProperties().getKind());
        System.out.println(b.getProperties().getHeight());
    }

    public String readFiles(String path){
        try{
            return new String(Files.readAllBytes(Paths.get(path)));
        }catch (IOException e){
            throw new RuntimeException("file not found "+path);
//            System.out.println(e);
        }finally{
            System.out.println("reading file"+" "+ path);
        }
//        return "";
    }
}

