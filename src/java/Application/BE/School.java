package Application.BE;
import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "School")
public class School {

    @SQLColumn(name = "SID")
    private int schoolID;

    @SQLColumn(name = "schoolName")
    private String schoolName;

    @SQLColumn(name = "FK_Zipcode")
    private Location location;


    public School(int schoolID, String schoolName, Location location)
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


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
