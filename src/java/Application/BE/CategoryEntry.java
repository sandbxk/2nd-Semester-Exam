package Application.BE;

public class CategoryEntry {

    private int id;
    private Category category;
    private int level;
    private String assessment;
    private String cause;
    private String implications;
    private String citizenGoals;
    private String expectedCondition;
    private String note;
    private boolean isFunctionAbility;
    private boolean isSuperCategory;

    public CategoryEntry(int id, Category category, int level, boolean isFunctionAbility, boolean isSuperCategory) {
        this.id = id;
        this.category = category;
        this.level = level;
        this.isFunctionAbility = isFunctionAbility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getImplications() {
        return implications;
    }

    public void setImplications(String implications) {
        this.implications = implications;
    }

    public String getCitizenGoals() {
        return citizenGoals;
    }

    public void setCitizenGoals(String citizenGoals) {
        this.citizenGoals = citizenGoals;
    }

    public String getExpectedCondition() {
        return expectedCondition;
    }

    public void setExpectedCondition(String expectedCondition) {
        this.expectedCondition = expectedCondition;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFunctionAbility() {
        return isFunctionAbility;
    }

    public void setFunctionAbility(boolean isFunctionAbility) {
        this.isFunctionAbility = isFunctionAbility;
    }
}
