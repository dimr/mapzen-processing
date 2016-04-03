/**
 * Created by dimitris on 4/3/16.
 */
public class CoordinateConverter {

    public static int long2Tile(float lon, int zoom) {
        return (int) (Math.floor((lon + 180) / 360 * Math.pow(2, zoom)));
    }

    public static int lat2tile(float lat, int zoom) {
        return (int) (Math.floor((1 - Math.log(Math.tan(lat * Math.PI / 180) + 1 / Math.cos(lat * Math.PI / 180)) / Math.PI) / 2 * Math.pow(2, zoom)));
    }
}
