package Application.GUI.Controllers.dashboard;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import Application.GUI.Models.ControllerModels.CitizenTemplateControllerModel;
import Application.GUI.Models.FunctionalLevels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CitizenTemplate implements Initializable {

    public AnchorPane anchorPaneCitizenTemplateDashboard;
    public ListView listViewCitizenTemplates;
    public TextField txtFieldCitizenTemplateSearch;
    public Button btnCitizenTemplateSearch;
    public Label lblCitizenTemplateName;
    public ListView listViewCitizenTemplateContactInfo;
    public Button btnAddCitizenTemplateContactInfo;
    public Button btnRemoveCitizenTemplateContactInfo;
    public Button btnCitizenTemplateEditBaseData;
    public Label lblAgeCitizenTemplate;
    public Label lblBirthdateCitizenTemplate;
    public Label lblAddressCitizenTemplate;
    public Label lblHelpStatusCitizenTemplate;
    public Label lblCivilianStatusCitizenTemplate;
    public Button btnCitizenTemplateChangeJournal;
    public Button btnCitizenTemplateEditOn;
    public Button btnCitizenTemplateEditCancel;
    public Button btnCitizenTemplateEditSave;


    // Citizen Template - Functional Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewFunc;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncImplications;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCitizenGoals;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncNote;

    // Citizen Template - Health Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewHealth;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCategory;
    public TreeTableColumn<CategoryEntryModel, Number> treeTblColumnHealthLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthNote;

    // Citizen Template - General Information
    public TextArea txtAreaGenInfoMastering;
    public TextArea txtAreaGenInfoMotivation;
    public TextArea txtAreaGenInfoRessources;
    public TextArea txtAreaGenInfoRoles;
    public TextArea txtAreaGenInfoHabits;
    public TextArea txtAreaGenInfoEduAndJob;
    public TextArea txtAreaGenInfoLifeStory;
    public TextArea txtAreaGenInfoHealthInfo;
    public TextArea txtAreaGenInfoAssistiveDevices;
    public TextArea txtAreaGenInfoHomeLayout;
    public TextArea txtAreaGenInfoNetwork;


    private CitizenTemplateControllerModel model = new CitizenTemplateControllerModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTreeTableClmns();
        setFuncTreeTable();
        initCitizenTemplatesList();
    }


    public void onCitizenTemplateSearch(ActionEvent event) {
        model.citizenTemplateSearch();
    }

    public void onRemoveCitizenTemplateContactInfo(ActionEvent event) {
        model.removeCitizenTemplateContactInfo();
    }

    public void onAddCitizenTemplateContactInfo(ActionEvent event) {
        model.addCitizenTemplateContactInfo();
    }

    private void setFuncTreeTable(){
        // Set up the table
        CitizenTemplateModel citizenTemplateModel = new CitizenTemplateModel();

        ObservableList<CategoryEntryModel> func = citizenTemplateModel.getRelevantFunctionalAbilities();
        ObservableList<CategoryEntryModel> health = citizenTemplateModel.getRelevantHealthConditions();

        ObservableList<TreeItem<CategoryEntryModel>> funcTree = FXCollections.observableArrayList();
        ObservableList<TreeItem<CategoryEntryModel>> healthTree = FXCollections.observableArrayList();

        TreeItem<CategoryEntryModel> funcRoot = new TreeItem<>(new CategoryEntryModel("Functional Abilities"));
        TreeItem<CategoryEntryModel> healthRoot = new TreeItem<>(new CategoryEntryModel("Health Conditions"));
        treeTblViewFunc.setRoot(funcRoot);
        treeTblViewHealth.setRoot(healthRoot);

        for (CategoryEntryModel categoryEntryModel : func) {
            funcTree.add(new TreeItem<>(categoryEntryModel));
        }

        for (CategoryEntryModel categoryEntryModel : health) {
            healthTree.add(new TreeItem<>(categoryEntryModel));
        }
        funcRoot.getChildren().addAll(funcTree);
        healthRoot.getChildren().addAll(healthTree);

        //https://jenkov.com/tutorials/javafx/treetableview.html
    }


    private void initTreeTableClmns(){
        treeTblColumnFuncCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnFuncLevel.setCellValueFactory(param -> param.getValue().getValue().getLevelImageComboBoxProperty());
        treeTblColumnFuncAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnFuncCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnFuncImplications.setCellValueFactory(param -> param.getValue().getValue().implicationsProperty());
        treeTblColumnFuncCitizenGoals.setCellValueFactory(param -> param.getValue().getValue().citizenGoalsProperty());
        treeTblColumnFuncExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblColumnFuncNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());

        treeTblColumnHealthCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnHealthLevel.setCellValueFactory(param -> param.getValue().getValue().levelProperty());
        treeTblColumnHealthAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnHealthCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnHealthExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblColumnHealthNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());
    }

    public void onCitizenTemplateChangeJournal(ActionEvent event) {
        model.citizenTemplateChangeJournal();
    }

    public void onCitizenTemplateEditBaseData(ActionEvent event) {
        model.onCitizenTemplateEditBaseData();
    }

    private void initCitizenTemplatesList(){
        listViewCitizenTemplates.setItems(model.getCitizenTemplates());

        listViewCitizenTemplates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.setSelectedCitizenTemplateModel((CitizenTemplateModel) newValue);
            setDataToCitizenTemplateView();
        });
    }

    private void setDataToCitizenTemplateView(){
        lblCitizenTemplateName.setText(model.getSelectedCitizenTemplateModel().getName());
        lblAgeCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getAge() + "");
        lblAddressCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getAddress());
        lblBirthdateCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        lblHelpStatusCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getHelpStatus());
        lblCivilianStatusCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getCivilianStatus());

        listViewCitizenTemplateContactInfo.setItems(model.getSelectedCitizenTemplateModel().getContactInfo());
    }


    private void setEditable(boolean editable) {
        treeTblViewFunc.setEditable(editable);
        treeTblViewHealth.setEditable(editable);
        for (TreeItem<CategoryEntryModel> cat : treeTblViewFunc.getRoot().getChildren()) {
            cat.getValue().getLevelImageComboBox().setDisable(editable);
        }
        for (TreeItem<CategoryEntryModel> cat : treeTblViewHealth.getRoot().getChildren()) {
            cat.getValue().getLevelImageComboBox().setDisable(editable);
        }
        btnCitizenTemplateEditOn.setVisible(!editable); //Only visible if not editable
        btnCitizenTemplateEditSave.setVisible(editable); //Only visible if editable
        btnCitizenTemplateEditSave.setVisible(editable); //Only visible if editable

    }

    public void onEditOn(ActionEvent event) {
        setEditable(true);
        treeTblViewFunc.setRoot(model.getAllFuncCategories());
        treeTblViewHealth.setRoot(model.getAllHealthCategories());

    }

    public void onEditDone(ActionEvent event) {
        //TODO: Save data and alert
        treeTblViewFunc.setRoot(model.getNewRelevantFuncCategories());
        treeTblViewHealth.setRoot(model.getNewRelevantHealthCategories());
        setEditable(false);
    }

    public void onEditCancel(ActionEvent event) {
        setEditable(false);
        treeTblViewFunc.setRoot(model.getRelevantFuncCategories());
        treeTblViewHealth.setRoot(model.getRelevantHealthCategories());
    }
}
