package Application.BE;

public class ContentEntry {

    private Category category;

    private int id;

    private String assessment = "";
    private String cause = "";
    private String implications = "";
    private int currentStatus;
    private int expectedStatus;

    private String citizenGoals = "";
    private String note = "";

    private boolean relevant;


    public ContentEntry(int id, Category category, int level) {
        this.id = id;
        this.category = category;
        this.currentStatus = level;
        initRelevance();
    }

    public ContentEntry(int id, Category category) {
        this.id = id;
        this.category = category;
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

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
        initRelevance();
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

    public int getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    public boolean getRelevant() {
        return relevant;
    }

    private void initRelevance() {
        switch (getCategory().getType()){
            case FUNCTIONAL_ABILITY -> {
                if (getCurrentStatus() != 9) {
                    setRelevant(true);
                }
                else {
                    setRelevant(false);
                }
            }
            case HEALTH_CONDITION -> {
                if (getCurrentStatus() != 0) {
                    setRelevant(true);
                }
                else {
                    setRelevant(false);
                }
            }
            case GENERAL_INFORMATION -> setRelevant(true);
            }
    }



}
