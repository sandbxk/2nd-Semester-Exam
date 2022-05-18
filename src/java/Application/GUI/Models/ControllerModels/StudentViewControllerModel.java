package Application.GUI.Models.ControllerModels;

import Application.BLL.StudentDataManager;
import Application.GUI.Models.CitizenModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class StudentViewControllerModel {

    private CitizenModel selectedCitizen;
    private StudentDataManager studentDataManager;

    public StudentViewControllerModel() {
        studentDataManager = new StudentDataManager();
    }

    public void onStudentCitizensSearch() {
        //TODO
    }

    public void onOpenJournal() {
        //TODO
    }

    public void onViewCases() {
        //TODO
    }

    public void onAddObservation() {
        //TODO
    }

    public ObservableList<CitizenModel> getAllCitizens()
    {
      //  return studentDataManager.getAllCitizens();
        return null;
    }

    public CitizenModel getSelectedCitizen() {
        return selectedCitizen;
    }

    public void setSelectedCitizen(CitizenModel selectedCitizen) {
        this.selectedCitizen = selectedCitizen;
    }
}
