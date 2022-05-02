package Application.GUI.Models;

import Application.BE.School;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class SchoolModel {
    StringProperty name;
    IntegerProperty zipCode;
    StringProperty city;

    SchoolModel(School school)
    {
        name.setValue(school.name());
        zipCode.setValue(school.zipCode());
        city.setValue(school.city());
    }

    public StringProperty getName(){
        return name;
    }

    public IntegerProperty getZipCode(){
        return zipCode;
    }

    public StringProperty getCity(){
        return city;
    }
}
