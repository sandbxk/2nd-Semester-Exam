package Application.BE;

public class School {

    int id;
    String name;
    int zipCode;
    String city;

    public School(int id, String name, int zipCode, String city){
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }



}
