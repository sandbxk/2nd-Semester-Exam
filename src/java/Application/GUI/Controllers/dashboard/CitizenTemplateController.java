package Application.GUI.Controllers.dashboard;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import Application.GUI.Models.ControllerModels.CitizenTemplateControllerModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class CitizenTemplateController implements Initializable {

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
    private ContextMenu actionsMenu = new ContextMenu();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTreeTableClmns();
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
        double onScreenX = btnActions.getScene().getWindow().getX() + btnActions.getHeight() + btnActions.localToScene(btnActions.getBoundsInLocal()).getMinX();
        double onScreenY = btnActions.getScene().getWindow().getY() + btnActions.getWidth() + btnActions.localToScene(btnActions.getBoundsInLocal()).getMinY();

        double offsetX = btnActions.getWidth() * 2;
        double offsetY = btnActions.getHeight() * 1.5;

        //ContextMenu showed at the location of the button, with offsets applied
        //actionsMenu.show(btnActions, onScreenX - offsetX, onScreenY + offsetY);
        actionsMenu.show(btnActions, onScreenX, onScreenY);
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

    private void onNewCitizenTemplate(){
        model.newCitizenTemplate();
    }

    private void onDeleteCitizenTemplate(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Er du sikker på at du vil slette denne borger skabelonen?");
        alert.setContentText("Dette kan ikke fortrydes.");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("Styles/MainStylesheet.css")).toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            model.deleteCitizenTemplate();
        }
    }

    private void onCopyCitizenTemplate(){
        model.copyCitizenTemplate();
    }



    private void setFuncTreeTable(){
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

        //https://jenkov.com/tutorials/javafx/treetableview.html
    }

    /**
     * Initializes the TreeTableColumns for the Function and Health TreeTableViews.
     * Where not custom cellFatory is used, the TextFieldTreeTableCell applied.
     */
    private void initTreeTableClmns(){
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



        treeTblColumnFuncCategory.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnFuncAssessment.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnFuncCause.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnFuncImplications.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnFuncCitizenGoals.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnFuncExpectedCondition.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnFuncNote.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

        treeTblColumnHealthCategory.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnHealthAssessment.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnHealthCause.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnHealthExpectedCondition.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTblColumnHealthNote.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
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
        lblCitizenTemplateName.setText(model.getSelectedCitizenTemplateModel().getName() + " " + model.getSelectedCitizenTemplateModel().getSurname());
        lblAgeCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getAge() + "");
        lblAddressCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getAddress());
        lblBirthdateCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        lblHelpStatusCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getHelpStatus());
        lblCivilianStatusCitizenTemplate.setText(model.getSelectedCitizenTemplateModel().getCivilianStatus());

        listViewCitizenTemplateContactInfo.setItems(model.getSelectedCitizenTemplateModel().getContactInfo());
    }

    /**
     * Sets the tables and relevant columns to editable or not. The same applies to the combo boxes within the level columns.
     * Also changes the visible buttons deciding whether to start, save or abandon the edit.
     * @param editable
     */
    private void setEditable(boolean editable) {
        treeTblViewFunc.setEditable(editable);
        treeTblViewHealth.setEditable(editable);

        treeTblColumnFuncLevel.setEditable(editable);
        treeTblColumnFuncAssessment.setEditable(editable);
        treeTblColumnFuncCause.setEditable(editable);
        treeTblColumnFuncImplications.setEditable(editable);
        treeTblColumnFuncCitizenGoals.setEditable(editable);
        treeTblColumnFuncExpectedCondition.setEditable(editable);
        treeTblColumnFuncNote.setEditable(editable);

        treeTblColumnHealthLevel.setEditable(editable);
        treeTblColumnHealthAssessment.setEditable(editable);
        treeTblColumnHealthCause.setEditable(editable);
        treeTblColumnHealthExpectedCondition.setEditable(editable);
        treeTblColumnHealthNote.setEditable(editable);


        for (TreeItem<CategoryEntryModel> cat : treeTblViewFunc.getRoot().getChildren()) {
            cat.getValue().getFuncLevelComboBox().setDisable(!editable);
        }
        for (TreeItem<CategoryEntryModel> cat : treeTblViewHealth.getRoot().getChildren()) {
            cat.getValue().getHealthLevelComboBox().setDisable(!editable);
        }

        btnCitizenTemplateEditOn.setVisible(!editable); //Only visible if not editable
        btnCitizenTemplateEditSave.setVisible(editable); //Only visible if editable
        btnCitizenTemplateEditCancel.setVisible(editable); //Only visible if editable

    }

    public void onEditOn(ActionEvent event) {
        setEditable(true);
        //TODO: Save current state in model
        treeTblViewFunc.setRoot(model.getAllFuncCategories());
        treeTblViewHealth.setRoot(model.getAllHealthConditions());

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

    //https://www.youtube.com/watch?v=BNvVSU9nHDY
    public void onTreeTblColumnStartEdit(TreeTableColumn.CellEditEvent<CategoryEntryModel, String> editEvent) {
        //TODO individual methods for each column
    }

    public void onTreeTblColumnCommitEdit(TreeTableColumn.CellEditEvent<CategoryEntryModel, String> editEvent) {
        //TODO
    }

    public void onTreeTblColumnCancelEdit(TreeTableColumn.CellEditEvent<CategoryEntryModel, String> editEvent) {
        //TODO
    }
}
