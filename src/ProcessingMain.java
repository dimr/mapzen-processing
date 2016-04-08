
import net.MapzenUrl;
import net.MapzenUrlBuilder;
import peasy.PeasyCam;
import processing.core.PApplet;

/**
 * Created by dimitris on 4/7/16.
 */
public class ProcessingMain extends PApplet {
    PeasyCam cam;
    private Mapzen mapzen;

    @Override
    public void setup() {

        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        mapzen =  new Mapzen(this, url, 2); //this PApplet size
        System.out.println(mapzen.getBuildingsLayer().size());

//        center = toApplicationDimension(longLat);
        cam = new PeasyCam(this, 0, 0, 0, 1100);
        cam.setMinimumDistance(50);
        cam.setMaximumDistance(1500);
        mapzen.renderRoads();
        mapzen.renderPolygons();
    }


    @Override
    public void draw() {
//        background(200);


    }

    public void settings() {
        size(800, 600);
//        size(800, 600,"processing.opengl.PGraphics3D");
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{ProcessingMain.class.getName()});
    }
}
