package Application.GUI.Models.ControllerModels;

import Application.BE.ContactInfo;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.time.LocalDate;

public class CitizenTemplateControllerModel {

    private CitizenTemplateModel selectedCitizenTemplateModel;

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
        citizenTemplates.add(new CitizenTemplateModel("John", "Jørgensen", 9, LocalDate.now(), "Active", "Single", "I am living in your walls", FXCollections.observableArrayList(new ContactInfo("Søn Tlf 12 12 12 12"), new ContactInfo("Datter Tlf 12 12 12 12"))));
        citizenTemplates.add(new CitizenTemplateModel("Mark", "Hansen", 999, LocalDate.now(), "Ikke Aktiv", "Gift", "I am living in your walls", FXCollections.observableArrayList(new ContactInfo("Mor Tlf 12 12 12 12"), new ContactInfo("Far Tlf 12 12 12 12"))));
        return citizenTemplates;
    }

    public void setSelectedCitizenTemplateModel(CitizenTemplateModel selectedCitizenTemplateModel) {
        this.selectedCitizenTemplateModel = selectedCitizenTemplateModel;
    }

    public CitizenTemplateModel getSelectedCitizenTemplateModel() {
        return selectedCitizenTemplateModel;
    }

    public TreeItem<CategoryEntryModel> getAllFuncCategories() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getAllFuncCategories());
    }

    public TreeItem<CategoryEntryModel> getAllHealthConditions() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getAllHealthConditions());
    }

    public TreeItem<CategoryEntryModel> getNewRelevantFuncCategories() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantFunctionalAbilities());
        //TODO sort the two lists (relevant and non-relevant) and make a new one
    }

    public TreeItem<CategoryEntryModel> getNewRelevantHealthCategories() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantHealthConditions());
        //TODO sort the two lists (relevant and non-relevant) and make a new one
    }

    public TreeItem<CategoryEntryModel> getRelevantFuncCategories() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantFunctionalAbilities());
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategories() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return listToTreeItem(treeItem, selectedCitizenTemplateModel.getRelevantHealthConditions());
    }

    private TreeItem<CategoryEntryModel> listToTreeItem(TreeItem<CategoryEntryModel> treeItem, ObservableList<CategoryEntryModel> list) {
        for (CategoryEntryModel categoryEntryModel : list) {
            treeItem.getChildren().add(categoryEntryModel.getAsTreeItem());
        }
        return treeItem;
    }

    public void newCitizenTemplate() {
    }

    public void deleteCitizenTemplate() {
    }

    public void copyCitizenTemplate() {
    }
}
