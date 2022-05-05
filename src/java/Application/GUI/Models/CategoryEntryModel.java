package Application.GUI.Models;

import Application.BE.CategoryEntry;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CategoryEntryModel {


    private int id;
    private StringProperty categoryName;
    private StringProperty superCategory;
    private IntegerProperty level;
    private ObjectProperty<ImageView> levelImage;
    private StringProperty assessment;
    private StringProperty cause;
    private StringProperty implications;
    private StringProperty citizenGoals;
    private StringProperty expectedCondition;
    private StringProperty note;
    private boolean isFunctionAbility;
    private CategoryEntry categoryEntry;

    public CategoryEntryModel(CategoryEntry categoryEntry) {
        categoryName = new SimpleStringProperty(categoryEntry.getCategoryName());
        superCategory = new SimpleStringProperty(categoryEntry.getSuperCategory());
        level = new SimpleIntegerProperty(categoryEntry.getLevel());
        assessment = new SimpleStringProperty(categoryEntry.getAssessment());
        cause = new SimpleStringProperty(categoryEntry.getCause());
        implications = new SimpleStringProperty(categoryEntry.getImplications());
        citizenGoals = new SimpleStringProperty(categoryEntry.getCitizenGoals());
        expectedCondition = new SimpleStringProperty(categoryEntry.getExpectedCondition());
        note = new SimpleStringProperty(categoryEntry.getNote());
        isFunctionAbility = categoryEntry.isFunctionAbility();
        levelImage = new SimpleObjectProperty<>(new ImageView());


        levelImage.get().setFitHeight(50);
        levelImage.get().setFitWidth(60);

        this.categoryEntry = categoryEntry;
        this.id = categoryEntry.getId();
        setLevelImage(level.get());
    }

    public CategoryEntryModel(String categoryName){
        this.categoryName = new SimpleStringProperty(categoryName);
    }

    private void setLevelImage(int level){
        Image image;

        switch (level){
            case 0: image = new Image(getClass().getResource("/img/func0.png").toExternalForm());
                break;
            case 1: image = new Image(getClass().getResource("/img/func1.png").toExternalForm());
                break;
            case 2: image = new Image(getClass().getResource("/img/func2.png").toExternalForm());
                break;
            case 3: image = new Image(getClass().getResource("/img/func3.png").toExternalForm());
                break;
            case 4: image = new Image(getClass().getResource("/img/func4.png").toExternalForm());
                break;
            default: image = new Image(getClass().getResource("/img/func0.png").toExternalForm());
        }

        levelImage.get().setImage(image);
    }

    public ImageView getLevelImage(){
        return levelImage.get();
    }

    public ObjectProperty<ImageView> getLevelImageProperty(){
        return levelImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public StringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getSuperCategory() {
        return superCategory.get();
    }

    public StringProperty superCategoryProperty() {
        return superCategory;
    }

    public void setSuperCategory(String superCategory) {
        this.superCategory.set(superCategory);
    }

    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public String getAssessment() {
        return assessment.get();
    }

    public StringProperty assessmentProperty() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment.set(assessment);
    }

    public String getCause() {
        return cause.get();
    }

    public StringProperty causeProperty() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause.set(cause);
    }

    public String getImplications() {
        return implications.get();
    }

    public StringProperty implicationsProperty() {
        return implications;
    }

    public void setImplications(String implications) {
        this.implications.set(implications);
    }

    public String getCitizenGoals() {
        return citizenGoals.get();
    }

    public StringProperty citizenGoalsProperty() {
        return citizenGoals;
    }

    public void setCitizenGoals(String citizenGoals) {
        this.citizenGoals.set(citizenGoals);
    }

    public String getExpectedCondition() {
        return expectedCondition.get();
    }

    public StringProperty expectedConditionProperty() {
        return expectedCondition;
    }

    public void setExpectedCondition(String expectedCondition) {
        this.expectedCondition.set(expectedCondition);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public boolean isFunctionAbility() {
        return isFunctionAbility;
    }

    public void setFunctionAbility(boolean functionAbility) {
        isFunctionAbility = functionAbility;
    }

    public CategoryEntry getCategoryEntry() {
        return categoryEntry;
    }

    public void setCategoryEntry(CategoryEntry categoryEntry) {
        this.categoryEntry = categoryEntry;
    }
}
