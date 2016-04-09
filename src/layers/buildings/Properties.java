package layers.buildings;

/**
 * Created by dimitris on 4/2/16.
 */
public class Properties {
    private String kind;
    private String area;
    private String min_zoom;
    private String id;
    private String sort_key;
    private String name;
    private String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort_key() {
        return sort_key;
    }

    public void setSort_key(String sort_key) {
        this.sort_key = sort_key;
    }

    public Properties(){

    }

    public Properties(String kind, String area, String min_zoom, String id) {
        this.kind = kind;
        this.area = area;
        this.min_zoom = min_zoom;
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMin_zoom() {
        return min_zoom;
    }

    public void setMin_zoom(String min_zoom) {
        this.min_zoom = min_zoom;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "kind='" + kind + '\'' +
                ", area='" + area + '\'' +
                ", min_zoom='" + min_zoom + '\'' +
                ", id='" + id + '\'' +
                ", sort_key='" + sort_key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Properties that = (Properties) o;

        if (getKind() != null ? !getKind().equals(that.getKind()) : that.getKind() != null) return false;
        if (getArea() != null ? !getArea().equals(that.getArea()) : that.getArea() != null) return false;
        if (getMin_zoom() != null ? !getMin_zoom().equals(that.getMin_zoom()) : that.getMin_zoom() != null)
            return false;
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;

    }

    @Override
    public int hashCode() {
        int result = getKind() != null ? getKind().hashCode() : 0;
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result = 31 * result + (getMin_zoom() != null ? getMin_zoom().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }
}
