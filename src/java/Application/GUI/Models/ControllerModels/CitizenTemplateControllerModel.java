package Application.GUI.Models.ControllerModels;

import Application.BE.CategoryEntry;
import Application.BLL.TeacherDataManager;
import Application.GUI.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitizenTemplateControllerModel {

    private TeacherDataManager teacherDataManager;

    private CitizenTemplateModel selectedCitizenTemplateModel;

    //Copy from before the editMode is activated
    private CitizenTemplateModel preEditCitizenTemplateModel;

    public CitizenTemplateControllerModel() {
        teacherDataManager = new TeacherDataManager();
    }

    public void citizenTemplateSearch() {
    }

    /**
     * Get all the citizen templates from the DB and put them in a list.
     * @return
     */
    public ObservableList<CitizenTemplateModel> getCitizenTemplates() {
        ObservableList<CitizenTemplateModel> citizenTemplates = FXCollections.observableArrayList();
        citizenTemplates.add(new CitizenTemplateModel("John", "Jørgensen", 53));
        citizenTemplates.add(new CitizenTemplateModel("Mark", "Hansen", 9));
        return citizenTemplates;
    }

    /**
     * Set the selected citizen template.
     **/
    public void setSelectedCitizenTemplateModel(CitizenTemplateModel selectedCitizenTemplateModel) {
        this.selectedCitizenTemplateModel = selectedCitizenTemplateModel;
    }

    /**
     * Get the selected citizen template.
     **/
    public CitizenTemplateModel getSelectedCitizenTemplateModel() {
        return selectedCitizenTemplateModel;
    }

    public TreeItem<CategoryEntryModel> getAllFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getAllFuncCategories());
    }

    public TreeItem<CategoryEntryModel> getAllHealthConditionsAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getAllHealthConditions());
    }


    public TreeItem<CategoryEntryModel> getRelevantFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantFunctionalAbilities());
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantHealthConditions());
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

    /**
     * Create a new citizen template and write it to the DB.
     * Returns the instance for it to be added to the list in the GUI.
     * @return
     */
    public CitizenTemplateModel newCitizenTemplate() {
        CitizenTemplateModel CitizenTemplateModel = new CitizenTemplateModel(teacherDataManager.newCitizenTemplate());

        return CitizenTemplateModel;
    }

    /**
     * Delete the selected citizen template.
     **/
    public void deleteCitizenTemplate() {
        teacherDataManager.deleteCitizenTemplate(selectedCitizenTemplateModel.getTemplate());
    }

    /**
     * Creates a copy of the citizen template and writes it to the DB.
     */
    public CitizenTemplateModel copyCitizenTemplate() {
        try {
            CitizenTemplateModel clone = (CitizenTemplateModel) selectedCitizenTemplateModel.clone();
            teacherDataManager.copyCitizenTemplate(clone.getTemplate());

            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Creates a copy of the citizen template and stores it in the preEditCitizenTemplateModel variable for later user.
     */
    public void savePreEditState() {
        try {
            this.preEditCitizenTemplateModel = (CitizenTemplateModel) selectedCitizenTemplateModel.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save all the edits to the citizen template to the DB.
     */
    public void saveEditedCitizenTemplate() {
        if (preEditCitizenTemplateModel != null) {
            ObservableList<CategoryEntryModel> newHealthRoot = selectedCitizenTemplateModel.getAllHealthConditions();
            ObservableList<CategoryEntryModel> newFuncRoot = selectedCitizenTemplateModel.getAllFuncCategories();
            List<CategoryEntryModel> allOldHealth = new ArrayList<>(preEditCitizenTemplateModel.getAllHealthConditions());
            List<CategoryEntryModel> allOldFunc = new ArrayList<>(preEditCitizenTemplateModel.getAllFuncCategories());


            ObservableList<CategoryEntryModel> newRelevantHealthConditions = FXCollections.observableArrayList();
            ObservableList<CategoryEntryModel> newRelevantFunctionalAbilities = FXCollections.observableArrayList();
            ObservableList<CategoryEntryModel> newNonRelevantHealthConditions = FXCollections.observableArrayList();
            ObservableList<CategoryEntryModel> newNonRelevantFunctionalAbilities = FXCollections.observableArrayList();

            //Put relevant and non-relevant categories into their respective lists.
            for (CategoryEntryModel newHealth : newHealthRoot) {
                if (newHealth.getLevel() != HealthLevels.NOT_RELEVANT.ordinal()) {
                    newRelevantHealthConditions.add(newHealth);
                }
                else {
                    newNonRelevantHealthConditions.add(newHealth);
                }
            }

            //Put relevant and non-relevant categories into their respective lists.
            for (CategoryEntryModel newFunc : newFuncRoot) {
                if (newFunc.getLevel() != FunctionalLevels.LEVEL_9.ordinal() && newFunc.getLevel() != FunctionalLevels.LEVEL_9.level) {
                    newRelevantFunctionalAbilities.add(newFunc);
                }
                else {
                    newNonRelevantFunctionalAbilities.add(newFunc);
                }
            }

            selectedCitizenTemplateModel.setRelevantHealthConditions(newRelevantHealthConditions); //Relevant health
            selectedCitizenTemplateModel.setRelevantFunctionalAbilities(newRelevantFunctionalAbilities); //Relevant Functional

            selectedCitizenTemplateModel.setNonRelevantHealthConditions(newNonRelevantHealthConditions); //Non-Relevant Health
            selectedCitizenTemplateModel.setNonRelevantFunctionalAbilities(newNonRelevantFunctionalAbilities); //Non-Relevant Functional



            List<CategoryEntryModel> dbWriteHealthConditions = new ArrayList<>();
            List<CategoryEntryModel> dbWriteFunctionalAbilities = new ArrayList<>();

            //List of changed health conditions
            dbWriteHealthConditions.addAll(newRelevantHealthConditions);
            dbWriteHealthConditions.addAll(newNonRelevantHealthConditions);
            for (CategoryEntryModel health : dbWriteHealthConditions) {
                int index = allOldHealth.indexOf(health);
                if (index != -1) {
                    int compare = health.compareTo(allOldHealth.get(index));
                    if (compare != 0) {
                        dbWriteHealthConditions.remove(health);
                    }
                }
            }


            //List of changed functional abilities
            dbWriteFunctionalAbilities.addAll(newRelevantFunctionalAbilities);
            dbWriteFunctionalAbilities.addAll(newNonRelevantFunctionalAbilities);
            for (CategoryEntryModel func : dbWriteFunctionalAbilities) {
                int index = allOldFunc.indexOf(func);
                if (index != -1) {
                    int compare = func.compareTo(allOldHealth.get(index));
                    if (compare != 0) {
                        dbWriteHealthConditions.remove(func);
                    }
                }
            }

            //Unwrap BE
            List<CategoryEntry> beHealthConditions = dbWriteHealthConditions.stream().map(categoryEntryModel -> categoryEntryModel.getCategoryEntry()).collect(Collectors.toList());
            List<CategoryEntry> beFunctionalAbilities = dbWriteFunctionalAbilities.stream().map(categoryEntryModel -> categoryEntryModel.getCategoryEntry()).collect(Collectors.toList());

            //Write changes to the database
            teacherDataManager.updateCitizenTemplate(selectedCitizenTemplateModel.getTemplate(), beHealthConditions, beFunctionalAbilities);
        }
    }



    /**
     * Gets the pre edit citizen template.
     * @return
     */
    public CitizenTemplateModel getPreEditState() {
        return preEditCitizenTemplateModel;
    }


    public void newCitizenEntity() {
        teacherDataManager.newCitizenEntity(selectedCitizenTemplateModel.getTemplate());
    }
}
