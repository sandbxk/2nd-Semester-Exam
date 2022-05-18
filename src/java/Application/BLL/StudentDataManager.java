package Application.BLL;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDataManager {

    public StudentDataManager() {

    }

    public ObservableList<CitizenModel> getAllCitizens() {
        return FXCollections.observableArrayList(); //TODO: implement
    }

    public void updateObservation(CitizenModel selectedCitizen, CategoryEntryModel value) {
    }
}
