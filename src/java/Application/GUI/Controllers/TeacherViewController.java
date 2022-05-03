package Application.GUI.Controllers;

import Application.GUI.StateMachine.State;
import Application.GUI.StateMachine.TeacherViewStateMachine;
import Application.GUI.StateMachine.ViewStateEnum;
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
    @FXML public AnchorPane anchorPaneCitizenTemplates;
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


    // Students
    public Button btnViewStudentsWork;
    public ListView listViewStudents;
    public TextField txtFieldStudentsSearch;
    public Label lblStudentsStudentName;
    public Label lblStudentEmail;
    public ListView listViewContactInfo1;
    public Button btnViewStudentCases;
    public Button btnStudentSettings;
    public ListView listViewCitizensForStudents;
    public Button btnRemoveCitizenToStudent;
    public Button btnAddCitizenToStudent;

    
    

    private ToggleGroup toggleGroup;
    private HashMap<ToggleButton, TeacherViewStateMachine> buttonMap;
    private TeacherViewStateMachine viewState;
    private HashMap<ToggleButton, State> viewStatesMap;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initToggleGroup();
        viewChangedListener();
        initVisible();
        initViewStates();
        tglBtnDashboard.setSelected(true);
    }

    private void initViewStates(){
        viewStatesMap = new HashMap<>();
        viewStatesMap.put(tglBtnDashboard, new State(anchorPaneDashboard, tglBtnDashboard)); // Dashboard
        viewStatesMap.put(tglBtnStudents, new State(anchorPaneStudents, tglBtnStudents)); // Students
        viewStatesMap.put(tglBtnCitizenTemplates, new State(anchorPaneCitizenTemplates, tglBtnCitizenTemplates)); // Citizen Templates
        viewStatesMap.put(tglBtnCitizens, new State(anchorPaneCitizens, tglBtnCitizens)); // Citizens
        viewStatesMap.put(tglBtnCases, new State(anchorPaneCases, tglBtnCases)); // Cases
        viewStatesMap.put(tglBtnAssignments, new State(anchorPaneAssignments, tglBtnAssignments)); // Assignments
        viewStatesMap.put(tglBtnJournals, new State(anchorPaneJournals, tglBtnJournals)); // Journals

    }

    private void initToggleGroup(){
        toggleGroup = new ToggleGroup();
        tglBtnDashboard.setToggleGroup(toggleGroup);
        tglBtnStudents.setToggleGroup(toggleGroup);
        tglBtnCitizenTemplates.setToggleGroup(toggleGroup);
        tglBtnCitizens.setToggleGroup(toggleGroup);
        tglBtnCases.setToggleGroup(toggleGroup);
        tglBtnAssignments.setToggleGroup(toggleGroup);
        tglBtnJournals.setToggleGroup(toggleGroup);
    }

    private void viewChangedListener(){
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                viewState.changeState(viewStatesMap.get(newValue));
            }
        });
    }

    private void initVisible(){
        anchorPaneDashboard.setVisible(false);
        anchorPaneStudents.setVisible(false);
        anchorPaneCitizenTemplates.setVisible(false);
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

}
