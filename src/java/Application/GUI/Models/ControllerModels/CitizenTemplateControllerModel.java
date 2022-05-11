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

    private TreeItem<CategoryEntryModel> preEditHealthCategoryEntryModels;
    private TreeItem<CategoryEntryModel> preEditFunctionCategoryEntryModels;

    private CitizenTemplateModel preEditCitizenTemplateModel;


    public void citizenTemplateSearch() {
    }

    public void removeCitizenTemplateContactInfo() {
    }

    public void addCitizenTemplateContactInfo() {
    }

    public void citizenTemplateChangeJournal() {
    }

    public void onCitizenTemplateEditBaseData() {
    }

    public ObservableList<CitizenTemplateModel> getCitizenTemplates() {
        ObservableList<CitizenTemplateModel> citizenTemplates = FXCollections.observableArrayList();
        citizenTemplates.add(new CitizenTemplateModel("John", "Jørgensen", LocalDate.now(), "Active", "Single", "Gade 2", FXCollections.observableArrayList(new ContactInfo("Søn Tlf 12 12 12 12"), new ContactInfo("Datter Tlf 12 12 12 12"))));
        citizenTemplates.add(new CitizenTemplateModel("Mark", "Hansen", LocalDate.now(), "Ikke Aktiv", "Gift", "En anden gade 5", FXCollections.observableArrayList(new ContactInfo("Mor Tlf 12 12 12 12"), new ContactInfo("Far Tlf 12 12 12 12"))));
        return citizenTemplates;
    }

    public void setSelectedCitizenTemplateModel(CitizenTemplateModel selectedCitizenTemplateModel) {
        this.selectedCitizenTemplateModel = selectedCitizenTemplateModel;
    }

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

    public TreeItem<CategoryEntryModel> getNewRelevantFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantFunctionalAbilities());
        //TODO sort the two lists (relevant and non-relevant) and make a new one.
    }

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

    private TreeItem<CategoryEntryModel> listToTreeItem(TreeItem<CategoryEntryModel> treeItem, ObservableList<CategoryEntryModel> list) {
        for (CategoryEntryModel categoryEntryModel : list) {
            treeItem.getChildren().add(categoryEntryModel.getAsTreeItem());
        }
        return treeItem;
    }

    public CitizenTemplateModel newCitizenTemplate() {
        //TODO Write to DB
        CitizenTemplateModel CitizenTemplateModel = new CitizenTemplateModel("Ny", "Skabelon", LocalDate.now(), "", "", "", FXCollections.observableArrayList());
        //DataManager.newCitizenTemplate(CitizenTemplateModel);

        return CitizenTemplateModel;
    }

    public void deleteCitizenTemplate() {
        //TODO delete from DB
        //DataManager.deleteCitizenTemplate(selectedCitizenTemplateModel);
    }

    public void copyCitizenTemplate() {
    }

    public TreeItem<CategoryEntryModel> getPreEditHealthCategoryEntryModels() {
        return preEditHealthCategoryEntryModels;
    }

    public void savePreEditState() {
        try {
            this.preEditCitizenTemplateModel = (CitizenTemplateModel) selectedCitizenTemplateModel.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setPreEditFunctionCategoryEntryModels(TreeItem<CategoryEntryModel> preEditFunctionCategoryEntryModels) {
        this.preEditFunctionCategoryEntryModels = preEditFunctionCategoryEntryModels;
    }

    public void saveEditedCitizenTemplate() {
        //TODO save to DB
    }

    public CitizenTemplateModel getPreEditState() {
        return preEditCitizenTemplateModel;
    }

    public List<CategoryEntryModel> getTreeItemsFromRoot(TreeItem<CategoryEntryModel> root) {
        List<CategoryEntryModel> catList = new ArrayList<>(); //List to store the categories
        Thread thread = new Thread(() -> {

            ObservableList<TreeItem<CategoryEntryModel>> treeItems = root.getChildren(); //Get the children of the root
            for (TreeItem<CategoryEntryModel> treeItem : treeItems) { //For each child
                if (treeItem.getChildren().size() > 0) { //If the child has children
                    catList.addAll(getTreeItemsFromRoot(treeItem)); //Get the children of the child
                } else {
                    CategoryEntryModel categoryEntryModel = treeItem.getValue(); //Get the value of the child
                    catList.add(categoryEntryModel); //Add the value to the list
                }

            }
        });
        thread.run();
        return catList;
    }
}
