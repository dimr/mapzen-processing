package layers.buildings;

import geometry.types.buildings.Polygon;
import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;
import utils.geo.BoundingBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitris on 4/3/16.
 */
public class Geometry {
    private String type;
    private List coordinates = new ArrayList<>();
    private Polygon polygon;


    public Geometry() {
    }

    /**
     * @param type
     * @param coordinates
     */
    public Geometry(String type, List coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The coordinates
     */
    public List getCoordinates() {
        return coordinates;
    }


    public void createGeometryTypes(BoundingBox box, int width, int height) {
        polygon = new Polygon();
        if (this.type.equals("Polygon")) {
            for (Object o : (ArrayList) this.getCoordinates().get(0)) {
                ArrayList temp = (ArrayList) o;
                Vec2D tempVec = null;
//                System.out.println(temp);
                for (int i = 0; i < temp.size(); i++) {
                    double x = ((Double) temp.get(0)).doubleValue();
                    double y = ((Double) temp.get(1)).doubleValue();
                    tempVec = new Vec2D((float) x, (float) y);
                }
                polygon.collectPoints(tempVec);
//                Polygon p=this.polygon.toApplicationDimension(box,width,height);
//                polygon=p;
//                polygon.add(tempVec);
            }
            polygon.toApplicationDimension(box, width, height);
        }
//        System.out.println("Polygon " + this.polygon);
    }

    public Polygon getPolygon(){
        return this.polygon;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }

    /**
     * @param coordinates The coordinates
     */
    public void setCoordinates(List coordinates) {
        this.coordinates = coordinates;
    }


}
