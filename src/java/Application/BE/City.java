package Application.BE;

public class City {
    private String CityName;
    private int zipCode;

    public City(int zipCode, String cityName)
    {
        this.zipCode = zipCode;
        this.CityName = cityName;
    }

    public City(int zipCode)
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
