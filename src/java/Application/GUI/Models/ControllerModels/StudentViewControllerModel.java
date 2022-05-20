package Application.GUI.Models.ControllerModels;

import Application.BE.Category;
import Application.BLL.StudentDataManager;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.HashMap;

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

    public TreeItem<CategoryEntryModel> getAllFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("Alle Funktionsevene tilstande"));
        return listToTreeItem(treeItem, selectedCitizen.getAllFuncCategories());
    }

    public TreeItem<CategoryEntryModel> getAllHealthConditionsAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("Alle Helbredstilstande"));
        return listToTreeItem(treeItem, selectedCitizen.getAllHealthConditions());
    }


    public TreeItem<CategoryEntryModel> getRelevantFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("Alle relevante Helbredstilstande"));
        return listToTreeItem(treeItem, selectedCitizen.getRelevantFunctionalAbilities());
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("Alle relevante Funktionsevene tilstande"));
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

    public void updateObservation(CategoryEntryModel value) {
        studentDataManager.updateObservation(selectedCitizen.getBeCitizen(), value.getContentEntry());
    }

    public void recalculateRelevantCategories() {
        HashMap<Category, CategoryEntryModel> functionalAbilities = new HashMap<>(selectedCitizen.getAllFuncCategories());
        HashMap<Category, CategoryEntryModel> healthConditions = new HashMap<>(selectedCitizen.getAllHealthConditions());

        HashMap<Category, CategoryEntryModel> relevantFunctionalAbilities = new HashMap<>();
        HashMap<Category, CategoryEntryModel> relevantHealthConditions = new HashMap<>();
        HashMap<Category, CategoryEntryModel> nonRelevantFunctionalAbilities = new HashMap<>();
        HashMap<Category, CategoryEntryModel> nonRelevantHealthConditions = new HashMap<>();

        Thread thread = new Thread(() -> {
            for (CategoryEntryModel categoryEntryModel : functionalAbilities.values()) {
                    if (categoryEntryModel.getContentEntry().getRelevant()) {
                        relevantFunctionalAbilities.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                    }
                    else {
                        nonRelevantFunctionalAbilities.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                    }
            }
            for (CategoryEntryModel categoryEntryModel : healthConditions.values()) {
                if (categoryEntryModel.getContentEntry().getRelevant()) {
                    relevantHealthConditions.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                }
                else {
                    nonRelevantHealthConditions.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                }
            }
        });
        thread.start();
        while (thread.isAlive()) {
            //wait for the thread to finish
        }

        selectedCitizen.setRelevantFunctionalAbilities(relevantFunctionalAbilities);
        selectedCitizen.setRelevantHealthConditions(relevantHealthConditions);
        selectedCitizen.setNonRelevantFunctionalAbilities(nonRelevantFunctionalAbilities);
        selectedCitizen.setNonRelevantHealthConditions(nonRelevantHealthConditions);

    }
    
}
