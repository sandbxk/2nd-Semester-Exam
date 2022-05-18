package Application.GUI.Models.ControllerModels;

import Application.BLL.StudentDataManager;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;

import java.util.Collection;

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

    public ObservableList<CitizenModel> getAllCitizens() {
        return studentDataManager.getAllCitizens();
    }

    public CitizenModel getSelectedCitizen() {
        return selectedCitizen;
    }

    public void setSelectedCitizen(CitizenModel selectedCitizen) {
        this.selectedCitizen = selectedCitizen;
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategoriesAsTreeItem(CitizenModel selectedCitizen) {
        return new TreeItem<>();
    }

    public TreeItem<CategoryEntryModel> getRelevantFuncCategoriesAsTreeItem(CitizenModel selectedCitizen) {
        return new TreeItem<>();
    }
}
