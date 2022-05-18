package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Citizen implements Cloneable {


    private int id;
    private String name;
    private String surname;
    private int age;
    private GeneralJournal generalInfo;
    private final List<CategoryEntry> functionalAbilities;
    private final List<CategoryEntry> healthConditions;


    public Citizen(int id, String name, String surname, int age, GeneralJournal generalInfo)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;

        this.generalInfo = new GeneralJournal();
        this.functionalAbilities = new ArrayList<>();
        this.healthConditions = new ArrayList<>();
    }

    /**
     * Constructor for blank citizen template

     */
    public Citizen()
    {
        this.id = -1;
        this.name = "Ny Borger";
        this.surname = "Skabelon";
        this.age = 0;

        this.generalInfo = new GeneralJournal();
        this.functionalAbilities = new ArrayList<>();
        this.healthConditions = new ArrayList<>();
    }

    public List<CategoryEntry> getFunctionalAbilities () {
        return functionalAbilities;
    }

    public List<CategoryEntry> getHealthConditions ()
    {
        return healthConditions;
    }

    public void setFunctionalAbilities (List < CategoryEntry > functionalAbilities) {
        this.functionalAbilities.clear();
        this.functionalAbilities.addAll(functionalAbilities);
    }

    public void setHealthConditions (List < CategoryEntry > healthConditions) {
        this.healthConditions.clear();
        this.healthConditions.addAll(healthConditions);
    }

    public void addFunctionalAbility (CategoryEntry entry)
    {
        functionalAbilities.add(entry);
    }

    public void addHealthConditions (CategoryEntry entry)
    {
        healthConditions.add(entry);
    }

    public GeneralJournal getGeneralInfo () {
        return generalInfo;
    }

    public void setGeneralInfo (GeneralJournal generalInfo){
        this.generalInfo = generalInfo;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id){
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname){
        this.surname = surname;
    }

    public int getAge () {
        return age;
    }

    public void setAge ( int age){
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


