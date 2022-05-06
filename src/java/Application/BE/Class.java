package Application.BE;

import Application.DAL.Annotations.SQLColumn;
import Application.DAL.Annotations.SQLTable;

@SQLTable(name = "classes")
public class Class {

    @SQLColumn(name = "classId")
    private int id;

    @SQLColumn(name = "className")
    private String className;

    @SQLColumn(name = "teacherID")
    private int teacherID;

    @SQLColumn(name = "school")
    private int schoolID;

    public Class(int id, String className)
    {
        this.className = className;
        this.id = id;
    }

    public Class(int id, String className, int teacherID, int schoolID)
    {
        this.className = className;
        this.id = id;
        this.teacherID = teacherID;
        this.schoolID = schoolID;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return className;
    }

    public void setName(String name)
    {
        this.className = name;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
}
