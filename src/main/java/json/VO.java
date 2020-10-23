package json;

/**
 * @author wanghongmiao
 */
public class VO {
    private int id;
    private String name;
    private String city;
    private String area;
    private String province;
    private String coordinates;

    public VO(int id, String name,String area,String city, String province, String coordinates) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.city = city;
        this.province = province;
        this.coordinates = coordinates;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea(){
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "VO [id=" + id + ", name=" + name + "area: " + area + ", city=" + city + ", province=" + province + ", coordinates=" + coordinates + "]";
    }
}
