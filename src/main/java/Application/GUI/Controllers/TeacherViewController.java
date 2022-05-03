package Application.GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class TeacherViewController {
    public ScrollPane scrollPaneDashboard;
    public AnchorPane anchorPaneStudents;
    public AnchorPane anchorPaneCitizenTemplates;
    public AnchorPane anchorPaneCitizens;
    public AnchorPane anchorPaneCases;
    public AnchorPane anchorPaneAssignments;
    public AnchorPane anchorPaneJournals;

    public ToggleButton tglBtnDashboard;
    public ToggleButton tglBtnStudents;
    public ToggleButton tglBtnCitizenTemplates;
    public ToggleButton tglButtonCitizens;
    public ToggleButton tglBtnCases;
    public ToggleButton tglBtnAssignments;
    public ToggleButton tglBtnJournals;

    public void onMenuItemClicked(ActionEvent event) {
        switch (event.getSource().toString()){
            case "ToggleButton[text=Dashboard]":
                scrollPaneDashboard.setVisible(true);
                anchorPaneStudents.setVisible(false);
                anchorPaneCitizenTemplates.setVisible(false);
                anchorPaneCitizens.setVisible(false);
                anchorPaneCases.setVisible(false);
                anchorPaneAssignments.setVisible(false);
                anchorPaneJournals.setVisible(false);
                break;
            case "ToggleButton[text=Students]":
                scrollPaneDashboard.setVisible(false);
                anchorPaneStudents.setVisible(true);
                anchorPaneCitizenTemplates.setVisible(false);
                anchorPaneCitizens.setVisible(false);
                anchorPaneCases.setVisible(false);
                anchorPaneAssignments.setVisible(false);
                anchorPaneJournals.setVisible(false);
                break;
            case "ToggleButton[text=Citizen Templates]":
                scrollPaneDashboard.setVisible(false);
                anchorPaneStudents.setVisible(false);
                anchorPaneCitizenTemplates.setVisible(true);
                anchorPaneCitizens.setVisible(false);
                anchorPaneCases.setVisible(false);
                anchorPaneAssignments.setVisible(false);
                anchorPaneJournals.setVisible(false);
                break;
            case "ToggleButton[text=Citizens]":
                scrollPaneDashboard.setVisible(false);
                anchorPaneStudents.setVisible(false);
                anchorPaneCitizenTemplates.setVisible(false);
                anchorPaneCitizens.setVisible(true);
                anchorPaneCases.setVisible(false);
                anchorPaneAssignments.setVisible(false);
                anchorPaneJournals.setVisible(false);
                break;
            case "ToggleButton[text=Cases]":
                scrollPaneDashboard.setVisible(false);
                anchorPaneStudents.setVisible(false);
                anchorPaneCitizenTemplates.setVisible(false);
                anchorPaneCitizens.setVisible(false);
                anchorPaneCases.setVisible(true);
                anchorPaneAssignments.setVisible(false);
                anchorPaneJournals.setVisible(false);
                break;
            case "ToggleButton[text=Assignments]":
                scrollPaneDashboard.setVisible(false);
                anchorPaneStudents.setVisible(false);
                anchorPaneCitizenTemplates.setVisible(false);
                anchorPaneCitizens.setVisible(false);
                anchorPaneCases.setVisible(false);
                anchorPaneAssignments.setVisible(true);
                anchorPaneJournals.setVisible(false);
                break;
            case "ToggleButton[text=Journals]":
                scrollPaneDashboard.setVisible(false);
                anchorPaneStudents.setVisible(false);
                anchorPaneCitizenTemplates.setVisible(false);
                anchorPaneCitizens.setVisible(false);
                anchorPaneCases.setVisible(false);
                anchorPaneAssignments.setVisible(false);
                anchorPaneJournals.setVisible(true);
                break;
        }



    }


}
