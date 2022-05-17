package Application.BE;

public class City {
    String CityName;
    int zipCode;

    public City(String cityName, int zipCode)
    {
        this.CityName = cityName;
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
