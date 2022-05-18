package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SQLTable(name = "citizen")
public class Citizen
{
    @SQLColumn(name = "citizenId")
    private int id;



    private CitizenBaseData citizenBaseData;
    private GeneralJournal generalJournal;
    private final List<CategoryEntry> functionalAbilities;
    private final List<CategoryEntry> healthConditions;

    public Citizen(int id, CitizenBaseData baseData, GeneralJournal generalJournal) {
        this.id = id;
        this.citizenBaseData = baseData;
        this.generalJournal = generalJournal;

        this.functionalAbilities = new ArrayList<>();
        this.healthConditions = new ArrayList<>();
    }

    public Citizen(CitizenTemplate template) {
        this.id = -1;
        this.citizenBaseData = template.getBaseData();
        this.generalJournal = template.getGeneralInfo();
        this.functionalAbilities = new ArrayList<>(template.getFunctionalAbilities());
        this.healthConditions = new ArrayList<>(template.getHealthConditions());
    }



    @SQLGetter(name = "cAge")
    public int getAge() {
        return this.citizenBaseData.getAge();
    }

    @SQLSetter(name = "cAge")
    public void setAge(int age) {
        this.citizenBaseData.setAge(age);
    }

    @SQLGetter(name = "cFirstName")
    public String getFirstname() {
        return citizenBaseData.getName();
    }

    @SQLSetter(name = "cFirstName")
    public void setFirstname(String firstname) {
        this.citizenBaseData.setName(firstname);
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
        return this.citizenBaseData.getSurname();
    }

    @SQLSetter(name = "cSurname")
    public void setLastname(String lastname) {
        this.citizenBaseData.setSurname(lastname);
    }

    public CitizenBaseData getCitizenBaseData() {
        return citizenBaseData;
    }

    public void setCitizenBaseData(CitizenBaseData citizenBaseData) {
        this.citizenBaseData = citizenBaseData;
    }

    public GeneralJournal getGeneralInfo() {
        return generalJournal;
    }

    public void setGeneralInfo(GeneralJournal generalJournal) {
        this.generalJournal = generalJournal;
    }

    public List<CategoryEntry> getFunctionalAbilities() {
        return functionalAbilities;
    }

    public List<CategoryEntry> getHealthConditions() {
        return healthConditions;
    }
}
