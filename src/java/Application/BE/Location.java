package Application.BE;

public class Location {
    private String CityName;
    private int zipCode;

    public Location(int zipCode, String cityName)
    {
        this.zipCode = zipCode;
        this.CityName = cityName;
    }

    public Location(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public City()
    {

    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
