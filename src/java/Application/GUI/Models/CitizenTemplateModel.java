package Application.GUI.Models;

import Application.BE.CitizenBaseData;
import Application.BE.CitizenTemplate;
import Application.BE.GeneralJournal;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CitizenTemplateModel implements Cloneable {

    private CitizenTemplate template;

    private StringProperty name;
    private StringProperty surname;
    private IntegerProperty age;

    private StringProperty mastering;
    private StringProperty motivation;
    private StringProperty resources;
    private StringProperty roles;
    private StringProperty habits;
    private StringProperty eduAndJob;
    private StringProperty lifeStory;
    private StringProperty healthInfo;
    private StringProperty assistiveDevices;
    private StringProperty homeLayout;
    private StringProperty network;

    private ObservableList<CategoryEntryModel> relevantFunctionalAbilities;
    private ObservableList<CategoryEntryModel> relevantHealthConditions;
    private ObservableList<CategoryEntryModel> nonRelevantFunctionalAbilities;
    private ObservableList<CategoryEntryModel> nonRelevantHealthConditions;



    public CitizenTemplateModel(String name, String surname, int age) {
        template = new CitizenTemplate(-1, new CitizenBaseData(name, surname, age), new GeneralJournal());

        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.age = new SimpleIntegerProperty(age);

        this.mastering = new SimpleStringProperty(template.getGeneralInfo().getCoping());
        this.motivation = new SimpleStringProperty(template.getGeneralInfo().getMotivation());
        this.resources = new SimpleStringProperty(template.getGeneralInfo().getResources());
        this.roles = new SimpleStringProperty(template.getGeneralInfo().getRoles());
        this.habits = new SimpleStringProperty(template.getGeneralInfo().getHabits());
        this.eduAndJob = new SimpleStringProperty(template.getGeneralInfo().getEduAndJob());
        this.lifeStory = new SimpleStringProperty(template.getGeneralInfo().getLifeStory());
        this.healthInfo = new SimpleStringProperty(template.getGeneralInfo().getHealthInfo());
        this.assistiveDevices = new SimpleStringProperty(template.getGeneralInfo().getAssistiveDevices());
        this.homeLayout = new SimpleStringProperty(template.getGeneralInfo().getHomeLayout());
        this.network = new SimpleStringProperty(template.getGeneralInfo().getNetwork());
        infoBind();

        this.relevantFunctionalAbilities = FXCollections.observableArrayList();
        this.relevantHealthConditions = FXCollections.observableArrayList();
        this.nonRelevantFunctionalAbilities = FXCollections.observableArrayList();
        this.nonRelevantHealthConditions = FXCollections.observableArrayList();

        //initFunctionalAbilities();
        //initHealthConditions();
    }

    public CitizenTemplateModel(CitizenTemplate template) {
        this.template = template;

        this.name = new SimpleStringProperty(template.getBaseData().getName());
        this.surname = new SimpleStringProperty(template.getBaseData().getSurname());
        this.age = new SimpleIntegerProperty(template.getBaseData().getAge());

        this.mastering.set(template.getGeneralInfo().getCoping());
        this.motivation.set(template.getGeneralInfo().getMotivation());
        this.resources.set(template.getGeneralInfo().getResources());
        this.roles.set(template.getGeneralInfo().getRoles());
        this.habits.set(template.getGeneralInfo().getHabits());
        this.eduAndJob.set(template.getGeneralInfo().getEduAndJob());
        this.lifeStory.set(template.getGeneralInfo().getLifeStory());
        this.healthInfo.set(template.getGeneralInfo().getHealthInfo());
        this.assistiveDevices.set(template.getGeneralInfo().getAssistiveDevices());
        this.homeLayout.set(template.getGeneralInfo().getHomeLayout());
        this.network.set(template.getGeneralInfo().getNetwork());
        infoBind();

        this.relevantFunctionalAbilities = FXCollections.observableArrayList();
        this.relevantHealthConditions = FXCollections.observableArrayList();
        this.nonRelevantFunctionalAbilities = FXCollections.observableArrayList();
        this.nonRelevantHealthConditions = FXCollections.observableArrayList();

        //initFunctionalAbilities();
        //initHealthConditions();
    }

    public CitizenTemplateModel()
    {
        template = new CitizenTemplate();


        this.name = new SimpleStringProperty(template.getBaseData().getName());
        this.surname = new SimpleStringProperty(template.getBaseData().getSurname());
        this.age = new SimpleIntegerProperty(template.getBaseData().getAge());

        this.mastering.set(template.getGeneralInfo().getCoping());
        this.motivation.set(template.getGeneralInfo().getMotivation());
        this.resources.set(template.getGeneralInfo().getResources());
        this.roles.set(template.getGeneralInfo().getRoles());
        this.habits.set(template.getGeneralInfo().getHabits());
        this.eduAndJob.set(template.getGeneralInfo().getEduAndJob());
        this.lifeStory.set(template.getGeneralInfo().getLifeStory());
        this.healthInfo.set(template.getGeneralInfo().getHealthInfo());
        this.assistiveDevices.set(template.getGeneralInfo().getAssistiveDevices());
        this.homeLayout.set(template.getGeneralInfo().getHomeLayout());
        this.network.set(template.getGeneralInfo().getNetwork());
        infoBind();


        this.relevantFunctionalAbilities = FXCollections.observableArrayList();
        this.relevantHealthConditions = FXCollections.observableArrayList();

        this.nonRelevantFunctionalAbilities = FXCollections.observableArrayList();
        this.nonRelevantHealthConditions = FXCollections.observableArrayList();


        //initFunctionalAbilities();
        //initHealthConditions();
    }

    private void infoBind(){
        this.name.bindBidirectional(new SimpleStringProperty(template.getBaseData().getName()));
        this.surname.bindBidirectional(new SimpleStringProperty(template.getBaseData().getSurname()));
        this.age.bindBidirectional(new SimpleIntegerProperty(template.getBaseData().getAge()));

        this.mastering.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getCoping()));
        this.motivation.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getMotivation()));
        this.resources.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getResources()));
        this.roles.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getRoles()));
        this.habits.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getHabits()));
        this.eduAndJob.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getEduAndJob()));
        this.lifeStory.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getLifeStory()));
        this.healthInfo.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getHealthInfo()));
        this.assistiveDevices.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getAssistiveDevices()));
        this.homeLayout.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getHomeLayout()));
        this.network.bindBidirectional(new SimpleStringProperty(template.getGeneralInfo().getNetwork()));

}


    @Override
    public String toString() {
        return name.get() + " " + surname.get();
    }
/*
    private void initFunctionalAbilities() {
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Walking", 1, true, false)));
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Climbing", 1, true, false)));
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Swimming", 1, true, false)));
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Bathing", 4, true, false)));


        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Sleeping", 0, true, false)));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Eating", 0, true, false)));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Toileting", 0, true, false)));

        relevantFunctionalAbilities.add(new CategoryEntryModel("Other", 2, "note", true));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel("Other", 0, "note", true));
    }

    private void initHealthConditions() {
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Diabetes", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "High Blood Pressure", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Heart Disease", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Asthma", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Epilepsy", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Allergies", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Other", 1, false, false)));

        nonRelevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "None", 0, false, false)));

        relevantHealthConditions.add(new CategoryEntryModel("Other", 1, "note", false));
        nonRelevantHealthConditions.add(new CategoryEntryModel("Other", 0, "note", false));
    }

 */


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getMastering() {
        return mastering.get();
    }

    public StringProperty masteringProperty() {
        return mastering;
    }

    public void setMastering(String mastering) {
        this.mastering.set(mastering);
    }

    public String getMotivation() {
        return motivation.get();
    }

    public StringProperty motivationProperty() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation.set(motivation);
    }

    public String getResources() {
        return resources.get();
    }

    public StringProperty resourcesProperty() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources.set(resources);
    }

    public String getRoles() {
        return roles.get();
    }

    public StringProperty rolesProperty() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles.set(roles);
    }

    public String getHabits() {
        return habits.get();
    }

    public StringProperty habitsProperty() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits.set(habits);
    }

    public String getEduAndJob() {
        return eduAndJob.get();
    }

    public StringProperty eduAndJobProperty() {
        return eduAndJob;
    }

    public void setEduAndJob(String eduAndJob) {
        this.eduAndJob.set(eduAndJob);
    }

    public String getLifeStory() {
        return lifeStory.get();
    }

    public StringProperty lifeStoryProperty() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory.set(lifeStory);
    }

    public String getHealthInfo() {
        return healthInfo.get();
    }

    public StringProperty healthInfoProperty() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo.set(healthInfo);
    }

    public String getAssistiveDevices() {
        return assistiveDevices.get();
    }

    public StringProperty assistiveDevicesProperty() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevices.set(assistiveDevices);
    }

    public String getHomeLayout() {
        return homeLayout.get();
    }

    public StringProperty homeLayoutProperty() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout.set(homeLayout);
    }

    public String getNetwork() {
        return network.get();
    }

    public StringProperty networkProperty() {
        return network;
    }

    public void setNetwork(String network) {
        this.network.set(network);
    }

    public CitizenTemplate getTemplate() {
        return template;
    }



    public ObservableList<CategoryEntryModel> getNonRelevantFunctionalAbilities() {
        return nonRelevantFunctionalAbilities;
    }

    public void setNonRelevantFunctionalAbilities(ObservableList<CategoryEntryModel> nonRelevantFunctionalAbilities) {
        this.nonRelevantFunctionalAbilities = nonRelevantFunctionalAbilities;
    }

    public ObservableList<CategoryEntryModel> getNonRelevantHealthConditions() {
        return nonRelevantHealthConditions;
    }

    public void setNonRelevantHealthConditions(ObservableList<CategoryEntryModel> nonRelevantHealthConditions) {
        this.nonRelevantHealthConditions = nonRelevantHealthConditions;
    }
    public ObservableList<CategoryEntryModel> getRelevantFunctionalAbilities() {
        return relevantFunctionalAbilities;
    }

    public void setRelevantFunctionalAbilities(ObservableList<CategoryEntryModel> relevantFunctionalAbilities) {
        this.relevantFunctionalAbilities = relevantFunctionalAbilities;
    }

    public ObservableList<CategoryEntryModel> getRelevantHealthConditions() {
        return relevantHealthConditions;
    }

    public void setRelevantHealthConditions(ObservableList<CategoryEntryModel> relevantHealthConditions) {
        this.relevantHealthConditions = relevantHealthConditions;
    }

    public ObservableList<CategoryEntryModel> getAllFuncCategories() {
        ObservableList<CategoryEntryModel> allFuncConditions = FXCollections.observableArrayList();
        allFuncConditions.addAll(relevantFunctionalAbilities);
        allFuncConditions.addAll(nonRelevantFunctionalAbilities);
        return allFuncConditions;
    }

    public ObservableList<CategoryEntryModel> getAllHealthConditions() {
        ObservableList<CategoryEntryModel> allHealthConditions = FXCollections.observableArrayList();
        allHealthConditions.addAll(relevantHealthConditions);
        allHealthConditions.addAll(nonRelevantHealthConditions);
        return allHealthConditions;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
