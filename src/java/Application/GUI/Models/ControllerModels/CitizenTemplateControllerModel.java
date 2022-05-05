package Application.GUI.Models.ControllerModels;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

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

    public ObservableList getCitizenTemplates() {
        ObservableList<CitizenTemplateModel> citizenTemplates = FXCollections.observableArrayList();
        return citizenTemplates;
    }

    public void setSelectedCitizenTemplateModel(CitizenTemplateModel selectedCitizenTemplateModel) {
        this.selectedCitizenTemplateModel = selectedCitizenTemplateModel;
    }

    public CitizenTemplateModel getSelectedCitizenTemplateModel() {
        return selectedCitizenTemplateModel;
    }

    public TreeItem<CategoryEntryModel> getAllFuncCategories() {
        return null; //TODO
    }

    public TreeItem<CategoryEntryModel> getAllHealthCategories() {
        return null; //TODO
    }

    public TreeItem<CategoryEntryModel> getNewRelevantFuncCategories() {
        return null; //TODO
    }

    public TreeItem<CategoryEntryModel> getNewRelevantHealthCategories() {
        return null; //TODO
    }

    public TreeItem<CategoryEntryModel> getRelevantFuncCategories() {
        return null; //TODO
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategories() {
        return null; //TODO
    }
}
