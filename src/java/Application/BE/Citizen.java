package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;

import java.util.HashMap;
import java.util.List;

public class Citizen
{
    private int id;
    private GeneralJournal journal;
    private School school;
    private String firstname;
    private String lastname;
    private int age;
    private int zipCode;

    private GeneralJournal generalJournal;
    private HashMap<ContentEntry, ContentEntry> healthCategoryEntries;
    private HashMap<ContentEntry, ContentEntry> funcCategoryEntries;

    public Citizen(int id)
    {
        this.id = id;
    }

    public Citizen(int id, GeneralJournal journal, School school, String firstname, String lastname, int age)
    {
        this.id = id;
        this.journal = journal;
        this.school = school;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }


    @SQLGetter(name = "cZipCode")
    public int getZipCode() {
        return zipCode;
    }

    @SQLSetter(name = "cZipCode")
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @SQLGetter(name = "cAge")
    public int getAge() {
        return age;
    }

    @SQLGetter(name = "cFirstName")
    public String getFirstname() {
        return firstname;
    }

    @SQLSetter(name = "cFirstName")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @SQLGetter(name = "citizenId")
    public int getId() {
        return id;
    }

    @SQLSetter(name = "citizenId")
    public void setId(int id) {
        this.id = id;
    }

    @SQLGetter(name = "cSurname")
    public String getLastname() {
        return lastname;
    }

    public GeneralJournal getJournal()
    {
        return journal;
    }

    public void setJournal(GeneralJournal journal)
    {
        this.journal = journal;
    }

    public School getSchool()
    {
        return school;
    }

    public void setSchool(School school)
    {
        this.school = school;
    }

    public void setContent(List<ContentEntry> content)
    {
        // populate lists (healthCategoryEntries, funcCategoryEntries)
    }
}
