import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitris on 4/3/16.
 */
public class Geometry {
    private String type;
    private List coordinates = new ArrayList<>();


    public Geometry() {
    }

    /**
     *
     * @param type
     * @param coordinates
     */
    public Geometry(String type, List coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The coordinates
     */
    public List getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }

    /**
     *
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(List coordinates) {
        this.coordinates = coordinates;
    }


}
