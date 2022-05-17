package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

import java.util.HashMap;

@SQLTable(name = "citizen")
public class Citizen
{
    @SQLColumn(name = "citizenId")
    private int id;

    @SQLColumn(name = "cFirstName")
    private String firstname;

    @SQLColumn(name = "cSurname")
    private String lastname;

    @SQLColumn(name = "cAge")
    private int age;

    @SQLColumn(name = "cStreet")
    private int streetID;

    @SQLColumn(name = "cStreetNumber")
    private int streetNumber;

    @SQLColumn(name = "cZipCode")
    private int zipCode;

    @SQLColumn(name = "cSchool")
    private int schoolID;

    private CitizenBaseData citizenBaseData;
    private GeneralJournal generalJournal;
    private HashMap<CategoryEntry, CategoryEntry> healthCategoryEntries;
    private HashMap<CategoryEntry, CategoryEntry> funcCategoryEntries;

    public Citizen(int id, String firstname, String lastname, int age, int streetID, int streetNumber, int zipCode, int schoolID) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.streetID = streetID;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.schoolID = schoolID;
    }

    public Citizen(CitizenTemplate template) {
        this.id = -1;
        this.citizenBaseData = template.getBaseData();
        this.generalJournal = template.getGeneralInfo();
        this.healthCategoryEntries = new HashMap<>();
        this.funcCategoryEntries = new HashMap<>();

    }


    @SQLGetter(name = "cStreetNumber")
    public int getStreetNumber() {
        return streetNumber;
    }

    @SQLSetter(name = "cStreetNumber")
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    @SQLGetter(name = "cZipCode")
    public int getZipCode() {
        return zipCode;
    }

    @SQLSetter(name = "cZipCode")
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @SQLGetter(name = "cSchool")
    public int getSchoolID() {
        return schoolID;
    }

    @SQLSetter(name = "cSchool")
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    @SQLGetter(name = "cStreet")
    public int getStreetID() {
        return streetID;
    }

    @SQLSetter(name = "cStreet")
    public void setStreetID(int streetID) {
        this.streetID = streetID;
    }

    @SQLGetter(name = "cAge")
    public int getAge() {
        return age;
    }

    @SQLSetter(name = "cAge")
    public void setAge(int age) {
        this.age = age;
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

    @SQLSetter(name = "cSurname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
