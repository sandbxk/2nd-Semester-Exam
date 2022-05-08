package Application.GUI.Controllers.dashboard;

import Application.BE.ContactInfo;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import Application.GUI.Models.ControllerModels.CitizenTemplateControllerModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CitizenTemplateController implements Initializable {

    public AnchorPane anchorPaneCitizenTemplateDashboard;
    public ListView<CitizenTemplateModel> listViewCitizenTemplates;
    public TextField txtFieldCitizenTemplateSearch;
    public Button btnCitizenTemplateSearch;
    public Label lblCitizenTemplateName;
    public ListView<ContactInfo> listViewCitizenTemplateContactInfo;
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
    public Button btnActions;


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
    public TreeTableColumn<CategoryEntryModel, ComboBox<HealthLevels>> treeTblColumnHealthLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthNote;

    // Citizen Template - General Information
    public TextArea txtAreaGenInfoMastering;
    public TextArea txtAreaGenInfoMotivation;
    public TextArea txtAreaGenInfoResources;
    public TextArea txtAreaGenInfoRoles;
    public TextArea txtAreaGenInfoHabits;
    public TextArea txtAreaGenInfoEduAndJob;
    public TextArea txtAreaGenInfoLifeStory;
    public TextArea txtAreaGenInfoHealthInfo;
    public TextArea txtAreaGenInfoAssistiveDevices;
    public TextArea txtAreaGenInfoHomeLayout;
    public TextArea txtAreaGenInfoNetwork;

    private CitizenTemplateControllerModel model = new CitizenTemplateControllerModel();
    private ContextMenu actionsMenu = new ContextMenu();
    private List<TreeTableColumn<CategoryEntryModel, String>> editableTreeTableColumns = new ArrayList<>();
    private List<TextArea> editableTextAreas = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnList();
        initTextAreaList();
        initTreeTableClmns();
        initTreeTblColumnEdit();
        setFuncTreeTable();
        initCitizenTemplatesList();
        initActionsMenu();
    }


    public void onCitizenTemplateSearch(ActionEvent event) {
        model.citizenTemplateSearch();
        //TODO: Implement search
    }

    public void onRemoveCitizenTemplateContactInfo(ActionEvent event) {
        model.removeCitizenTemplateContactInfo();
    }

    public void onAddCitizenTemplateContactInfo(ActionEvent event) {
        model.addCitizenTemplateContactInfo();
    }

    public void onActions(ActionEvent event) {
        double offsetX = -15;
        double offsetY = btnActions.getHeight() / 2.2;
        actionsMenu.show(btnActions, Side.TOP, offsetX, -offsetY);
    }

    private void initActionsMenu() {
        MenuItem newCitizenTemplate = new MenuItem("Ny Borger Skabelon");
        newCitizenTemplate.setOnAction(event -> onNewCitizenTemplate());
        MenuItem copyCitizenTemplate = new MenuItem("Kopier Borger Skabelon");
        copyCitizenTemplate.setOnAction(event -> onCopyCitizenTemplate());
        MenuItem deleteCitizenTemplate = new MenuItem("Slet Borger Skabelon");
        deleteCitizenTemplate.setOnAction(event -> onDeleteCitizenTemplate());

        actionsMenu = new ContextMenu(newCitizenTemplate, copyCitizenTemplate, deleteCitizenTemplate);
        actionsMenu.setAutoHide(true);
    }

    private void onNewCitizenTemplate() {
        listViewCitizenTemplates.getItems().add(model.newCitizenTemplate());
    }

    private void onDeleteCitizenTemplate() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Er du sikker på at du vil slette denne borger skabelonen?");
        alert.setContentText("Dette kan ikke fortrydes.");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            listViewCitizenTemplates.getItems().remove(listViewCitizenTemplates.getSelectionModel().getSelectedItem());
            model.deleteCitizenTemplate();
        }
    }

    private void onCopyCitizenTemplate() {
        model.copyCitizenTemplate();
    }


    private void setFuncTreeTable() {
        //TODO: Proper table population
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

    }

    private void initTextAreaList(){
        editableTextAreas.add(txtAreaGenInfoMastering);
        editableTextAreas.add(txtAreaGenInfoMotivation);
        editableTextAreas.add(txtAreaGenInfoResources);
        editableTextAreas.add(txtAreaGenInfoRoles);
        editableTextAreas.add(txtAreaGenInfoHabits);
        editableTextAreas.add(txtAreaGenInfoEduAndJob);
        editableTextAreas.add(txtAreaGenInfoLifeStory);
        editableTextAreas.add(txtAreaGenInfoHealthInfo);
        editableTextAreas.add(txtAreaGenInfoAssistiveDevices);
        editableTextAreas.add(txtAreaGenInfoHomeLayout);
        editableTextAreas.add(txtAreaGenInfoNetwork);
    }

    private void initColumnList() {
        editableTreeTableColumns.add(treeTblColumnFuncAssessment);
        editableTreeTableColumns.add(treeTblColumnFuncCause);
        editableTreeTableColumns.add(treeTblColumnFuncImplications);
        editableTreeTableColumns.add(treeTblColumnFuncCitizenGoals);
        editableTreeTableColumns.add(treeTblColumnFuncExpectedCondition);
        editableTreeTableColumns.add(treeTblColumnFuncNote);

        editableTreeTableColumns.add(treeTblColumnHealthAssessment);
        editableTreeTableColumns.add(treeTblColumnHealthCause);
        editableTreeTableColumns.add(treeTblColumnHealthExpectedCondition);
        editableTreeTableColumns.add(treeTblColumnHealthNote);
    }

    /**
     * Initializes the TreeTableColumns for the Function and Health TreeTableViews.
     * Where not custom cellFatory is used, the TextFieldTreeTableCell applied.
     */
    private void initTreeTableClmns() {
        treeTblColumnFuncCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnFuncLevel.setCellValueFactory(param -> param.getValue().getValue().getFuncComboBoxProperty());
        treeTblColumnFuncAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnFuncCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnFuncImplications.setCellValueFactory(param -> param.getValue().getValue().implicationsProperty());
        treeTblColumnFuncCitizenGoals.setCellValueFactory(param -> param.getValue().getValue().citizenGoalsProperty());
        treeTblColumnFuncExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblColumnFuncNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());

        treeTblColumnHealthCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnHealthLevel.setCellValueFactory(param -> param.getValue().getValue().getHealthComboBoxProperty());
        treeTblColumnHealthAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnHealthCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnHealthExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblColumnHealthNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());

        //Use TextFieldTreeTableCell for the editable columns
        editableTreeTableColumns.forEach(col -> col.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn()));

    }

    public void onCitizenTemplateChangeJournal(ActionEvent event) {
        model.citizenTemplateChangeJournal();
    }

    public void onCitizenTemplateEditBaseData(ActionEvent event) {
        model.onCitizenTemplateEditBaseData();
    }

    private void initCitizenTemplatesList() {
        listViewCitizenTemplates.setItems(model.getCitizenTemplates());

        listViewCitizenTemplates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.setSelectedCitizenTemplateModel((CitizenTemplateModel) newValue);
            setDataToCitizenTemplateView();
        });

        listViewCitizenTemplates.getSelectionModel().select(0);
    }


    private void setDataToCitizenTemplateView() {
        lblCitizenTemplateName.setText(model.getSelectedCitizenTemplateModel().getName() + " " + model.getSelectedCitizenTemplateModel().getSurname());
        lblAgeCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getAge() + "");
        lblAddressCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getAddress());
        lblBirthdateCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        lblHelpStatusCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getHelpStatus());
        lblCivilianStatusCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getCivilianStatus());

        listViewCitizenTemplateContactInfo.setItems(model.getSelectedCitizenTemplateModel().getContactInfo());

        TreeItem<CategoryEntryModel> funcRoot = new TreeItem<>();
        funcRoot.getChildren().addAll(model.getRelevantFuncCategoriesAsTreeItem());
        treeTblViewFunc.setRoot(funcRoot);


        TreeItem<CategoryEntryModel> healthRoot = new TreeItem<>();
        healthRoot.getChildren().addAll(model.getRelevantHealthCategoriesAsTreeItem());
        treeTblViewFunc.setRoot(healthRoot);

        txtAreaGenInfoMastering.setText(model.getSelectedCitizenTemplateModel().getMastering());
        txtAreaGenInfoMotivation.setText(model.getSelectedCitizenTemplateModel().getMotivation());
        txtAreaGenInfoResources.setText(model.getSelectedCitizenTemplateModel().getResources());
        txtAreaGenInfoRoles.setText(model.getSelectedCitizenTemplateModel().getRoles());
        txtAreaGenInfoHabits.setText(model.getSelectedCitizenTemplateModel().getHabits());
        txtAreaGenInfoEduAndJob.setText(model.getSelectedCitizenTemplateModel().getEduAndJob());
        txtAreaGenInfoLifeStory.setText(model.getSelectedCitizenTemplateModel().getLifeStory());
        txtAreaGenInfoHealthInfo.setText(model.getSelectedCitizenTemplateModel().getHealthInfo());
        txtAreaGenInfoAssistiveDevices.setText(model.getSelectedCitizenTemplateModel().getAssistiveDevices());
        txtAreaGenInfoHomeLayout.setText(model.getSelectedCitizenTemplateModel().getHomeLayout());
        txtAreaGenInfoNetwork.setText(model.getSelectedCitizenTemplateModel().getNetwork());

    }

    /**
     * Sets the tables and relevant columns to editable or not. The same applies to the combo boxes within the level columns.
     * Also changes the visible buttons deciding whether to start, save or abandon the edit.
     *
     * @param editable
     */
    private void setEditable(boolean editable) {
        treeTblViewFunc.setEditable(editable);
        treeTblViewHealth.setEditable(editable);

        treeTblColumnFuncCategory.setEditable(false);
        treeTblColumnFuncLevel.setEditable(editable);

        treeTblColumnHealthCategory.setEditable(false);
        treeTblColumnHealthLevel.setEditable(editable);

        //Set all standard columns to editable, except the category column
        editableTreeTableColumns.forEach(col -> col.setEditable(editable));


        //Set all TextAreas to editable
        editableTextAreas.forEach(ta -> ta.setEditable(editable));

        for (TreeItem<CategoryEntryModel> cat : treeTblViewFunc.getRoot().getChildren()) {
            cat.getValue().getFuncLevelComboBox().setDisable(!editable);
        }
        for (TreeItem<CategoryEntryModel> cat : treeTblViewHealth.getRoot().getChildren()) {
            cat.getValue().getHealthLevelComboBox().setDisable(!editable);
        }

        listViewCitizenTemplates.setDisable(editable);

        btnCitizenTemplateEditOn.setVisible(!editable); //Only visible if not editable
        btnCitizenTemplateEditSave.setVisible(editable); //Only visible if editable
        btnCitizenTemplateEditCancel.setVisible(editable); //Only visible if editable
    }

    public void onEditOn(ActionEvent event) {
        setEditable(true);
        model.savePreEditState();
        treeTblViewFunc.setRoot(model.getAllFuncCategoriesAsTreeItem());
        treeTblViewHealth.setRoot(model.getAllHealthConditionsAsTreeItem());

    }

    public void onEditDone(ActionEvent event) {
        //TODO: Save data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Er du sikker på at du gemme ændringerne på denne borger skabelonen?");
        alert.setContentText("Dette kan ikke fortrydes.");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            model.getSelectedCitizenTemplateModel().setMastering(txtAreaGenInfoMastering.getText());
            model.getSelectedCitizenTemplateModel().setMotivation(txtAreaGenInfoMotivation.getText());
            model.getSelectedCitizenTemplateModel().setResources(txtAreaGenInfoResources.getText());
            model.getSelectedCitizenTemplateModel().setRoles(txtAreaGenInfoRoles.getText());
            model.getSelectedCitizenTemplateModel().setHabits(txtAreaGenInfoHabits.getText());
            model.getSelectedCitizenTemplateModel().setEduAndJob(txtAreaGenInfoEduAndJob.getText());
            model.getSelectedCitizenTemplateModel().setLifeStory(txtAreaGenInfoLifeStory.getText());
            model.getSelectedCitizenTemplateModel().setHealthInfo(txtAreaGenInfoHealthInfo.getText());
            model.getSelectedCitizenTemplateModel().setAssistiveDevices(txtAreaGenInfoAssistiveDevices.getText());
            model.getSelectedCitizenTemplateModel().setHomeLayout(txtAreaGenInfoHomeLayout.getText());
            model.getSelectedCitizenTemplateModel().setNetwork(txtAreaGenInfoNetwork.getText());


            model.saveEditedCitizenTemplate();
            treeTblViewFunc.setRoot(model.getNewRelevantFuncCategoriesAsTreeItem());
            treeTblViewHealth.setRoot(model.getNewRelevantHealthCategoriesAsTreeItem());
            setEditable(false);
        }
    }

    public void onEditCancel(ActionEvent event) {
        setEditable(false);
        ObservableList<CitizenTemplateModel> templateModelObservableList = listViewCitizenTemplates.getItems();
        int index = templateModelObservableList.indexOf(model.getSelectedCitizenTemplateModel());
        listViewCitizenTemplates.getItems().set(index, model.getPreEditState());
        listViewCitizenTemplates.getSelectionModel().select(index);
    }

    //https://www.youtube.com/watch?v=BNvVSU9nHDY
    private void initTreeTblColumnEdit() {
            //Commit Edit
        treeTblColumnFuncAssessment.setOnEditCommit(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnFuncCause.setOnEditCommit(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnFuncImplications.setOnEditCommit(event -> getItemFromEditEvent(event).setImplications(event.getOldValue()));
        treeTblColumnFuncCitizenGoals.setOnEditCommit(event -> getItemFromEditEvent(event).setCitizenGoals(event.getOldValue()));
        treeTblColumnFuncExpectedCondition.setOnEditCommit(event -> getItemFromEditEvent(event).setExpectedCondition(event.getOldValue()));
        treeTblColumnFuncNote.setOnEditCommit(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));

        treeTblColumnHealthAssessment.setOnEditCommit(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnHealthCause.setOnEditCommit(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnHealthExpectedCondition.setOnEditCommit(event -> getItemFromEditEvent(event).setExpectedCondition(event.getOldValue()));
        treeTblColumnHealthNote.setOnEditCommit(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));

            //Cancel Edit
        treeTblColumnFuncAssessment.setOnEditCancel(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnFuncCause.setOnEditCancel(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnFuncImplications.setOnEditCancel(event -> getItemFromEditEvent(event).setImplications(event.getOldValue()));
        treeTblColumnFuncCitizenGoals.setOnEditCancel(event -> getItemFromEditEvent(event).setCitizenGoals(event.getOldValue()));
        treeTblColumnFuncExpectedCondition.setOnEditCancel(event -> getItemFromEditEvent(event).setExpectedCondition(event.getOldValue()));
        treeTblColumnFuncNote.setOnEditCancel(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));

        treeTblColumnHealthAssessment.setOnEditCancel(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnHealthCause.setOnEditCancel(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnHealthExpectedCondition.setOnEditCancel(event -> getItemFromEditEvent(event).setExpectedCondition(event.getOldValue()));
        treeTblColumnHealthNote.setOnEditCancel(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));
    }

    private CategoryEntryModel getItemFromEditEvent(TreeTableColumn.CellEditEvent<CategoryEntryModel, String> editEvent) {
        TreeItem<CategoryEntryModel> treeItem = editEvent.getTreeTablePosition().getTreeItem();
        return treeItem.getValue();
    }

}

