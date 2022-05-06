package Application.GUI.Models.ControllerModels;

import Application.GUI.Models.CitizenModel;
import javafx.event.ActionEvent;

public class StudentViewControllerModel {

    private CitizenModel selectedCitizen;

    public void onStudentCitizensSearch() {
    }

    public void onOpenJournal() {
    }

    public void onViewCases() {
    }

    public void onAddObservation() {
    }

    public CitizenModel getSelectedCitizen() {
        return selectedCitizen;
    }

    public void setSelectedCitizen(CitizenModel selectedCitizen) {
        this.selectedCitizen = selectedCitizen;
    }
}
