package Application.BE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class CitizenBaseData
{
    private String name;
    private String surname;
    private int age;

    public CitizenBaseData()
    {
        this.name = "Ny Borger";
        this.surname = "Skabelon";
        this.age = 0;
    }

    public CitizenBaseData(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
