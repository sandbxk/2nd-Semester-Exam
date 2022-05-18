package Application.BE;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class CitizenTemplate {

    int id;
    private CitizenBaseData baseData;
    private GeneralJournal generalInfo;
    private final List<CategoryEntry> functionalAbilities;
    private final List<CategoryEntry> healthConditions;

    public CitizenTemplate()
    {
        this.baseData = new CitizenBaseData();
        this.generalInfo = new GeneralJournal();
        this.id = -1;
        this.functionalAbilities = new ArrayList<>();
        this.healthConditions = new ArrayList<>();
    }

    public CitizenTemplate(int id, CitizenBaseData baseData, GeneralJournal generalInfo) {
        this();
        this.id = id;
        this.generalInfo = generalInfo;
        this.baseData = baseData;
    }

    public List<CategoryEntry> getFunctionalAbilities(){
        return functionalAbilities;
    }

    public List<CategoryEntry> getHealthConditions()
    {
        return healthConditions;
    }

    public void setFunctionalAbilities(List<CategoryEntry> functionalAbilities) {
        this.functionalAbilities.clear();
        this.functionalAbilities.addAll(functionalAbilities);
    }

    public void setHealthConditions(List<CategoryEntry> healthConditions) {
        this.healthConditions.clear();
        this.healthConditions.addAll(healthConditions);
    }

    public void addFunctionalAbility(CategoryEntry entry)
    {
        functionalAbilities.add(entry);
    }

    public void addHealthConditions(CategoryEntry entry)
    {
        healthConditions.add(entry);
    }

    public GeneralJournal getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralJournal generalInfo) {
        this.generalInfo = generalInfo;
    }

    public CitizenBaseData getBaseData() {
        return baseData;
    }

    public void setBaseData(CitizenBaseData baseData) {
        this.baseData = baseData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
