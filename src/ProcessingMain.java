
import net.MapzenUrl;
import net.MapzenUrlBuilder;
import processing.core.PApplet;

/**
 * Created by dimitris on 4/7/16.
 */
public class ProcessingMain extends PApplet {

    @Override
    public void setup() {
        MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setLayer("roads").setKey("vector-tiles-PADQnWp").buildUrl();
        Mapzen mapzen = new Mapzen(this, url, 2); //this PApplet size
        System.out.println(mapzen.getBuildingsLayer().size());


    }


    @Override
    public void draw() {

    }

    public void settings() {
        size(800, 600);
    }
    public static void main(String[] args) {
        PApplet.main(new String[]{ProcessingMain.class.getName()});
    }
}
