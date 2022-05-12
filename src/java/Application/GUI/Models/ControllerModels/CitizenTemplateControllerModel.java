package Application.GUI.Models.ControllerModels;

import Application.BE.ContactInfo;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.CitizenTemplateModel;
import com.sun.source.tree.Tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitizenTemplateControllerModel {

    private CitizenTemplateModel selectedCitizenTemplateModel;

    //Copies from before the editMode is activated
    private TreeItem<CategoryEntryModel> preEditHealthCategoryEntryModels;
    private TreeItem<CategoryEntryModel> preEditFunctionCategoryEntryModels;
    private CitizenTemplateModel preEditCitizenTemplateModel;


    public void citizenTemplateSearch() {
    }


    /**
     * Get all the citizen templates from the DB and put them in a list.
     * @return
     */
    public ObservableList<CitizenTemplateModel> getCitizenTemplates() {
        ObservableList<CitizenTemplateModel> citizenTemplates = FXCollections.observableArrayList();
        citizenTemplates.add(new CitizenTemplateModel("John", "Jørgensen", LocalDate.now(), "Active", "Single", "Gade 2", FXCollections.observableArrayList(new ContactInfo("Søn Tlf 12 12 12 12"), new ContactInfo("Datter Tlf 12 12 12 12"))));
        citizenTemplates.add(new CitizenTemplateModel("Mark", "Hansen", LocalDate.now(), "Ikke Aktiv", "Gift", "En anden gade 5", FXCollections.observableArrayList(new ContactInfo("Mor Tlf 12 12 12 12"), new ContactInfo("Far Tlf 12 12 12 12"))));
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

    /**
     * All relevant categories after editing the citizenTemplateModel.
     * @return
     */
    public TreeItem<CategoryEntryModel> getNewRelevantFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantFunctionalAbilities());
        //TODO sort the two lists (relevant and non-relevant) and make a new one.
    }

    /**
     * All relevant categories after editing the citizenTemplateModel.
     * @return
     */
    public TreeItem<CategoryEntryModel> getNewRelevantHealthCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantHealthConditions());
        //TODO sort the two lists (relevant and non-relevant) and make a new one
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
        //TODO Write to DB
        CitizenTemplateModel CitizenTemplateModel = new CitizenTemplateModel("Ny", "Skabelon", LocalDate.now(), "", "", "", FXCollections.observableArrayList());
        //DataManager.newCitizenTemplate(CitizenTemplateModel);

        return CitizenTemplateModel;
    }

    /**
     * Delete the selected citizen template.
     **/
    public void deleteCitizenTemplate() {
        //TODO delete from DB
        //DataManager.deleteCitizenTemplate(selectedCitizenTemplateModel);
    }

    /**
     * Creates a copy of the citizen template and writes it to the DB.
     */
    public CitizenTemplateModel copyCitizenTemplate() {
        try {
            return (CitizenTemplateModel) selectedCitizenTemplateModel.clone();
            //TODO Write to DB
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restore the health categories to the pre edit values.
     * @return
     */
    public TreeItem<CategoryEntryModel> getPreEditHealthCategoryEntryModels() {
        return preEditHealthCategoryEntryModels;
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
     * Sets the function categories to the pre edit values.
     * @param preEditFunctionCategoryEntryModels
     */
    public void setPreEditFunctionCategoryEntryModels(TreeItem<CategoryEntryModel> preEditFunctionCategoryEntryModels) {
        this.preEditFunctionCategoryEntryModels = preEditFunctionCategoryEntryModels;
    }

    /**
     * Save all the edits to the citizen template to the DB.
     */
    public void saveEditedCitizenTemplate() {
        //TODO save to DB
    }

    /**
     * Gets the pre edit citizen template.
     * @return
     */
    public CitizenTemplateModel getPreEditState() {
        return preEditCitizenTemplateModel;
    }


}
