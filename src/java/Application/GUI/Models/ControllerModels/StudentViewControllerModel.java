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

    public TreeItem<CategoryEntryModel> getAllFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizen.getAllFuncCategories());
    }

    public TreeItem<CategoryEntryModel> getAllHealthConditionsAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizen.getAllHealthConditions());
    }


    public TreeItem<CategoryEntryModel> getRelevantFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizen.getRelevantFunctionalAbilities());
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizen.getRelevantHealthConditions());
    }

    /**
     * Utility method to convert a list to a tree items children.
     * @param treeItem
     * @param list
     * @return
     */
    private TreeItem<CategoryEntryModel> listToTreeItem(TreeItem<CategoryEntryModel> treeItem, ObservableList<CategoryEntryModel> list) {
        for (CategoryEntryModel categoryEntryModel : list) {
            treeItem.getChildren().add(categoryEntryModel.getAsTreeItem());
        }
        return treeItem;
    }

}
