package Application.GUI.Controllers.dashboard;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class CitizenTemplate {
    public AnchorPane anchorPaneCitizenTemplateDashboard;
    public ListView listViewCitizenTemplates;
    public TextField txtFieldCitizenTemplateSearch;
    public Button btnCitizenTemplateSearch;
    public Label lblCitizenTemplateName;
    public ListView listViewCitizenTemplateContactInfo;
    public Button btnAddCitizenTemplateContactInfo;
    public Button btnRemoveCitizenTemplateContactInfo;
    public Button btnCitizenTemplateEditBaseData;
    public Label lblAgeCitizenTemplate;
    public Label lblBirthdateCitizenTemplate;
    public Label lblAddressCitizenTemplate;
    public Label lblHelpStatusCitizenTemplate;
    public Label lblCivilianStatusCitizenTemplate;
    public Button btnCitizenTemplateChangeJournal;
    public ToggleButton tglBtnCitizenTemplateEditOn;
    public ToggleButton tglBtnCitizenTemplateEditOff;
    public TextArea txtAreaGenInfoMastering;
    public TextArea txtAreaGenInfoMotivation;
    public TextArea txtAreaGenInfoResources;
    public TextArea txtAreaGenInfoRoles;
    public TextArea txtAreaGenInfoHabits;
    public TextArea txtAreaGenInfoEduAndJob;
    public TextArea txtAreaGenInfoLifeStory;
    public TextArea txtAreaGenInfoHealthInfo;
    public TextArea txtAreaGenInfoAssistiveDevices;
    public TextArea txtAreaGenInfoHomeLayout;
    public Label txtAreaGenInfoNetwork;
    
    public TreeTableView treeTblViewHealth;
    
    public TreeTableColumn treeTblColumnHealthCategory;
    public TreeTableColumn treeTblColumnHealthLevel;
    public TreeTableColumn treeTblColumnHealthAssessment;
    public TreeTableColumn treeTblColumnHealthCause;
    public TreeTableColumn treeTblColumnHealthExpectedCondition;
    public TreeTableColumn treeTblColumnHealthNote;
    
    public TreeTableColumn treeTblColumnFuncCategory;
    public TreeTableColumn treeTblColumnFuncLevel;
    public TreeTableColumn treeTblColumnFuncAssessment;
    public TreeTableColumn treeTblColumnFuncCause;
    public TreeTableColumn treeTblColumnFuncImplications;
    public TreeTableColumn treeTblColumnFuncCitizenGoals;
    public TreeTableColumn treeTblColumnFuncExpectedCondition;
    public TreeTableColumn treeTblColumnFuncNote;

    public void onCitizenTemplateSearch(ActionEvent event) {
    }

    public void onAddCitizenTemplateContactInfo(ActionEvent event) {
    }

    public void onRemoveCitizenTemplateContactInfo(ActionEvent event) {
    }

    public void onCitizenTemplateEditBaseData(ActionEvent event) {
    }

    public void onCitizenTemplateChangeJournal(ActionEvent event) {
    }

    private void setFuncTreeTable(){
        //https://jenkov.com/tutorials/javafx/treetableview.html
    }
}
