package Application.BE;
import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "School")
public class School {

    @SQLColumn(name = "SID")
    private int schoolID;

    @SQLColumn(name = "schoolName")
    private String schoolName;

    @SQLColumn(name = "FK_Zipcode")
    private Object location;


    public School(int schoolID, String schoolName, Object location)
    {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.location = location;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }
}
