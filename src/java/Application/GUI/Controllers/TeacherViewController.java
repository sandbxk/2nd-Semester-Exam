package Application.GUI.Controllers;

import Application.GUI.StateMachine.State;
import Application.GUI.StateMachine.TeacherViewStateMachine;
import Application.GUI.StateMachine.ViewStateEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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

    public void onMenuItemClicked(ActionEvent event) {
    }

    /*public void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(tglBtnDashboard)) {
            scrollPaneDashboard.setVisible(true);
            anchorPaneStudents.setVisible(false);
            anchorPaneCitizenTemplates.setVisible(false);
            anchorPaneCitizens.setVisible(false);
            anchorPaneCases.setVisible(false);
            anchorPaneAssignments.setVisible(false);
            anchorPaneJournals.setVisible(false);
        } else if (event.getSource().equals(tglBtnStudents)) {
            scrollPaneDashboard.setVisible(false);
            anchorPaneStudents.setVisible(true);
            anchorPaneCitizenTemplates.setVisible(false);
            anchorPaneCitizens.setVisible(false);
            anchorPaneCases.setVisible(false);
            anchorPaneAssignments.setVisible(false);
            anchorPaneJournals.setVisible(false);
        } else if (event.getSource().equals(tglBtnCitizenTemplates)) {
            scrollPaneDashboard.setVisible(false);
            anchorPaneStudents.setVisible(false);
            anchorPaneCitizenTemplates.setVisible(true);
            anchorPaneCitizens.setVisible(false);
            anchorPaneCases.setVisible(false);
            anchorPaneAssignments.setVisible(false);
            anchorPaneJournals.setVisible(false);
        } else if (event.getSource().equals(tglBtnCitizens)) {
            scrollPaneDashboard.setVisible(false);
            anchorPaneStudents.setVisible(false);
            anchorPaneCitizenTemplates.setVisible(false);
            anchorPaneCitizens.setVisible(true);
            anchorPaneCases.setVisible(false);
            anchorPaneAssignments.setVisible(false);
            anchorPaneJournals.setVisible(false);
        } else if (event.getSource().equals(tglBtnCases)) {
            scrollPaneDashboard.setVisible(false);
            anchorPaneStudents.setVisible(false);
            anchorPaneCitizenTemplates.setVisible(false);
            anchorPaneCitizens.setVisible(false);
            anchorPaneCases.setVisible(true);
            anchorPaneAssignments.setVisible(false);
            anchorPaneJournals.setVisible(false);
        } else if (event.getSource().equals(tglBtnAssignments)) {
            scrollPaneDashboard.setVisible(false);
            anchorPaneStudents.setVisible(false);
            anchorPaneCitizenTemplates.setVisible(false);
            anchorPaneCitizens.setVisible(false);
            anchorPaneCases.setVisible(false);
            anchorPaneAssignments.setVisible(true);
            anchorPaneJournals.setVisible(false);
        } else if (event.getSource().equals(tglBtnJournals)) {
            scrollPaneDashboard.setVisible(false);
            anchorPaneStudents.setVisible(false);
            anchorPaneCitizenTemplates.setVisible(false);
            anchorPaneCitizens.setVisible(false);
            anchorPaneCases.setVisible(false);
            anchorPaneAssignments.setVisible(false);
            anchorPaneJournals.setVisible(true);
        }


    }

     */
}
