package Application.GUI.Controllers;

import Application.GUI.StateMachine.State;

import javafx.application.Platform;
import Application.GUI.StateMachine.StateMachine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {
    @FXML public ScrollPane scrollPaneDashboard;
    @FXML public AnchorPane anchorPaneDashboard;
    @FXML public AnchorPane anchorPaneStudents;
    @FXML public AnchorPane anchorPaneCitizenTemplate;
    @FXML public AnchorPane anchorPaneCitizens;
    @FXML public AnchorPane anchorPaneCases;
    @FXML public AnchorPane anchorPaneAssignments;
    @FXML public AnchorPane anchorPaneJournals;

    @FXML public ToggleButton tglBtnDashboard;
    @FXML public ToggleButton tglBtnStudents;
    @FXML public ToggleButton tglBtnCitizenTemplates;
    @FXML public ToggleButton tglBtnCitizens;
    @FXML public ToggleButton tglBtnCases;
    @FXML public ToggleButton tglBtnAssignments;
    @FXML public ToggleButton tglBtnJournals;


    // Students
    public Button btnViewStudentsWork;
    public ListView listViewStudents;
    public TextField txtFieldStudentsSearch;
    public Label lblStudentsStudentName;
    public Label lblStudentEmail;
    public Button btnViewStudentCases;
    public Button btnStudentSettings;
    public ListView listViewCitizensForStudents;
    public Button btnRemoveCitizenToStudent;
    public Button btnAddCitizenToStudent;
    public Label lblCivilianStatusCitizenTemplate;


    // Citizens
    public Label lblCitizenName;
    public ListView listViewContactInfo;
    public Label lblAge;
    public Label lblBirthdateYear;
    public Label lblAddress;
    public Label lblHelpStatus;
    public Label lblCivilianStatus;
    public Button btnGeneralInfo;
    public Button btnJournal;
    public ListView listViewStudentsforCitizen;
    public Button btnRemoveStudentToCitizen;
    public Button btnAddStudentToCitizen;
    public TextField txtFieldCitizensSearch;
    public Button btnCitizensSearch;


    // Citizen Templates
    public ListView listViewCitizenTemplates;
    public TextField txtFieldCitizenTemplateSearch;
    public Button btnCitizenTemplateSearch;
    public Label lblCitizenTemplateName;
    public ListView listViewCitizenTemplateContactInfo;
    public Button btnAddCitizenTemplateContactInfo;
    public Button btnRemoveCitizenTemplateContactInfo;
    public Button btnGeneralInfoCitizenTemplate;
    public Label lblAgeCitizenTemplate;
    public Label lblBirthdateCitizenTemplate;
    public Label lblAddressCitizenTemplate;
    public Label lblHelpStatusCitizenTemplate;

            // Citizen Template - Functional Conditions
    public TreeTableColumn treeTblClmnFuncCategory;
    public TreeTableColumn treeTblClmnFuncLevel;
    public TreeTableColumn treeTblClmnFuncAssessment;
    public TreeTableColumn treeTblClmnFuncCause;
    public TreeTableColumn treeTblClmnFuncImplications;
    public TreeTableColumn treeTblClmnFuncCitizenGoals;
    public TreeTableColumn treeTblClmnFuncExpectedCondition;
    public TreeTableColumn treeTblClmnFuncNote;

            // Citizen Template - Health Conditions
    public TreeTableView treeTblViewHealth;
    public TreeTableColumn treeTblClmnHealthCategory;
    public TreeTableColumn treeTblClmnHealthLevel;
    public TreeTableColumn treeTblClmnHealthAssessment;
    public TreeTableColumn treeTblClmnHealthCause;
    public TreeTableColumn treeTblClmnHealthExpectedCondition;
    public TreeTableColumn treeTblClmnHealthNote;

            // Citizen Template - General Information
    public TextArea txtAreaGenInfoMastering;
    public TextArea txtAreaGenInfoMotivation;
    public TextArea txtAreaGenInfoRessources;
    public TextArea txtAreaGenInfoRoles;
    public TextArea txtAreaGenInfoHabits;
    public TextArea txtAreaGenInfoEduAndJob;
    public TextArea txtAreaGenInfoLifeStory;
    public TextArea txtAreaGenInfoHealthInfo;
    public TextArea txtAreaGenInfoAssistiveDevices;
    public TextArea txtAreaGenInfoHomeLayout;
    public Label txtAreaGenInfoNetwork;
    public ToggleButton tglBtnCitizenTemplateEditOn;
    public ToggleButton tglBtnCitizenTemplateEditOff;


    private ToggleGroup toggleGroup;
    private StateMachine<ToggleButton> stateMachine = new StateMachine<>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initToggleGroup();
        viewChangedListener();
        initViewStates();
        tglBtnDashboard.setSelected(true);
        Platform.runLater(this::initVisible);
    }


    private void initViewStates()
    {
        stateMachine.addState(tglBtnDashboard, new State(anchorPaneDashboard, tglBtnDashboard)); // Dashboard
        stateMachine.addState(tglBtnStudents, new State(anchorPaneStudents, tglBtnStudents)); // Students
        stateMachine.addState(tglBtnCitizenTemplates, new State(anchorPaneCitizenTemplate, tglBtnCitizenTemplates)); // Citizen Templates
        stateMachine.addState(tglBtnCitizens, new State(anchorPaneCitizens, tglBtnCitizens)); // Citizens
        stateMachine.addState(tglBtnCases, new State(anchorPaneCases, tglBtnCases)); // Cases
        stateMachine.addState(tglBtnAssignments, new State(anchorPaneAssignments, tglBtnAssignments)); // Assignments
        stateMachine.addState(tglBtnJournals, new State(anchorPaneJournals, tglBtnJournals)); // Journals
    }

    private void initToggleGroup()
    {
        toggleGroup = new ToggleGroup();
        tglBtnDashboard.setToggleGroup(toggleGroup);
        tglBtnStudents.setToggleGroup(toggleGroup);
        tglBtnCitizenTemplates.setToggleGroup(toggleGroup);
        tglBtnCitizens.setToggleGroup(toggleGroup);
        tglBtnCases.setToggleGroup(toggleGroup);
        tglBtnAssignments.setToggleGroup(toggleGroup);
        tglBtnJournals.setToggleGroup(toggleGroup);
    }

    private void viewChangedListener()
    {
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                stateMachine.change((ToggleButton) newValue);
            }
        });
    }

    private void initVisible()
    {
        anchorPaneDashboard.setVisible(false);
        anchorPaneStudents.setVisible(false);
        anchorPaneCitizenTemplate.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneCases.setVisible(false);
        anchorPaneAssignments.setVisible(false);
        anchorPaneJournals.setVisible(false);
    }


    // Students
    public void onViewStudentCases(ActionEvent event) {
    }

    public void onStudentSettings(ActionEvent event) {
    }

    public void onRemoveCitizenToStudent(ActionEvent event) {
    }

    public void onAddCitizenToStudent(ActionEvent event) {
    }

    public void onViewStudentsWork(ActionEvent event) {
    }




    // Citizens
    public void onGeneralInfo(ActionEvent event) {
    }

    public void onJournal(ActionEvent event) {
    }

    public void onRemoveStudentToCitizen(ActionEvent event) {
    }

    public void onAddStudentToCitizen(ActionEvent event) {
    }

    public void onCitizensSearch(ActionEvent event) {
    }



    // Citizen Template

    public void onCitizenTemplateSearch(ActionEvent event) {
    }

    public void onGeneralInfoCitizenTemplate(ActionEvent event) {
    }

    public void onRemoveCitizenTemplateContactInfo(ActionEvent event) {
    }

    public void onAddCitizenTemplateContactInfo(ActionEvent event) {
    }

    private void setFuncTreeTable(){
        //https://jenkov.com/tutorials/javafx/treetableview.html
    }

    public void onCitizenTemplateChangeJournal(ActionEvent event) {
    }

    public void onCitizenTemplateEditBaseData(ActionEvent event) {
    }

}
