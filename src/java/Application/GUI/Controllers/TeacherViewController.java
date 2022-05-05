package Application.GUI.Controllers;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import Application.GUI.Models.ControllerModels.TeacherViewModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.StateMachine.State;

import javafx.application.Platform;
import Application.GUI.StateMachine.StateMachine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {
    @FXML public ScrollPane scrollPaneDashboard;
    @FXML public AnchorPane anchorPaneDashboard;
    @FXML public AnchorPane anchorPaneStudents;
    @FXML public AnchorPane anchorPaneCitizenTemplate;
    @FXML public AnchorPane anchorPaneCitizens;
    @FXML public AnchorPane anchorPaneCases;
    @FXML public AnchorPane anchorPaneAssignments;
    @FXML public AnchorPane anchorPaneJournals;

    @FXML public ToggleButton tglBtnDashboard;
    @FXML public ToggleButton tglBtnStudents;
    @FXML public ToggleButton tglBtnCitizenTemplates;
    @FXML public ToggleButton tglBtnCitizens;
    @FXML public ToggleButton tglBtnCases;
    @FXML public ToggleButton tglBtnAssignments;
    @FXML public ToggleButton tglBtnJournals;


    // Students
    public Button btnViewStudentsWork;
    public ListView listViewStudents;
    public TextField txtFieldStudentsSearch;
    public Label lblStudentsStudentName;
    public Label lblStudentEmail;
    public Button btnViewStudentCases;
    public Button btnStudentSettings;
    public ListView listViewCitizensForStudents;
    public Button btnRemoveCitizenToStudent;
    public Button btnAddCitizenToStudent;
    public Label lblCivilianStatusCitizenTemplate;


    // Citizens
    public Label lblCitizenName;
    public ListView listViewContactInfo;
    public Label lblAge;
    public Label lblBirthdateYear;
    public Label lblAddress;
    public Label lblHelpStatus;
    public Label lblCivilianStatus;
    public Button btnGeneralInfo;
    public Button btnJournal;
    public ListView listViewStudentsforCitizen;
    public Button btnRemoveStudentToCitizen;
    public Button btnAddStudentToCitizen;
    public TextField txtFieldCitizensSearch;
    public Button btnCitizensSearch;


    // Citizen Templates
    public ListView listViewCitizenTemplates;
    public TextField txtFieldCitizenTemplateSearch;
    public Button btnCitizenTemplateSearch;
    public Label lblCitizenTemplateName;
    public ListView listViewCitizenTemplateContactInfo;
    public Button btnAddCitizenTemplateContactInfo;
    public Button btnRemoveCitizenTemplateContactInfo;
    public Label lblAgeCitizenTemplate;
    public Label lblBirthdateCitizenTemplate;
    public Label lblAddressCitizenTemplate;
    public Label lblHelpStatusCitizenTemplate;

            // Citizen Template - Functional Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewFunc;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnFuncCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblClmnFuncLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnFuncAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnFuncCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnFuncImplications;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnFuncCitizenGoals;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnFuncExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnFuncNote;

            // Citizen Template - Health Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewHealth;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnHealthCategory;
    public TreeTableColumn<CategoryEntryModel, Number> treeTblClmnHealthLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnHealthAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnHealthCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnHealthExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblClmnHealthNote;

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
    public ToggleButton tglBtnCitizenTemplateEditOn;
    public ToggleButton tglBtnCitizenTemplateEditOff;

    private BooleanProperty citizenTemplateEditMode = new SimpleBooleanProperty(false);
    private ToggleGroup toggleGroupViews;
    private ToggleGroup toggleGroupEditModeCitizenTemplate;
    private StateMachine<ToggleButton> stateMachine = new StateMachine<>();
    private TeacherViewModel teacherViewModel = new TeacherViewModel();


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initToggleGroups();
        viewChangedListener();
        initViewStates();
        tglBtnDashboard.setSelected(true);
        Platform.runLater(this::initVisible);
        initTreeTableClmns();
        setFuncTreeTable();
    }


    private void initViewStates()
    {
        stateMachine.addState(tglBtnDashboard, new State(anchorPaneDashboard, tglBtnDashboard)); // Dashboard
        stateMachine.addState(tglBtnStudents, new State(anchorPaneStudents, tglBtnStudents)); // Students
        stateMachine.addState(tglBtnCitizenTemplates, new State(anchorPaneCitizenTemplate, tglBtnCitizenTemplates)); // Citizen Templates
        stateMachine.addState(tglBtnCitizens, new State(anchorPaneCitizens, tglBtnCitizens)); // Citizens
        stateMachine.addState(tglBtnCases, new State(anchorPaneCases, tglBtnCases)); // Cases
        stateMachine.addState(tglBtnAssignments, new State(anchorPaneAssignments, tglBtnAssignments)); // Assignments
        stateMachine.addState(tglBtnJournals, new State(anchorPaneJournals, tglBtnJournals)); // Journals
    }

    private void initToggleGroups()
    {
        toggleGroupViews = new ToggleGroup();
        tglBtnDashboard.setToggleGroup(toggleGroupViews);
        tglBtnStudents.setToggleGroup(toggleGroupViews);
        tglBtnCitizenTemplates.setToggleGroup(toggleGroupViews);
        tglBtnCitizens.setToggleGroup(toggleGroupViews);
        tglBtnCases.setToggleGroup(toggleGroupViews);
        tglBtnAssignments.setToggleGroup(toggleGroupViews);
        tglBtnJournals.setToggleGroup(toggleGroupViews);

        toggleGroupEditModeCitizenTemplate = new ToggleGroup();
        tglBtnCitizenTemplateEditOn.setToggleGroup(toggleGroupEditModeCitizenTemplate);
        tglBtnCitizenTemplateEditOff.setToggleGroup(toggleGroupEditModeCitizenTemplate);
    }

    private void viewChangedListener()
    {
        toggleGroupViews.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                stateMachine.change((ToggleButton) newValue);
            }
        });
    }

    private void editModeListener(){
        toggleGroupEditModeCitizenTemplate.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            ToggleButton newToggleValue = null;
            ToggleButton oldToggleValue = null;
            if(newValue != null) {
                newToggleValue = (ToggleButton) newValue;
                newToggleValue.setDisable(true);
            }

            if (oldValue != null) {
                oldToggleValue = (ToggleButton) oldValue;
                oldToggleValue.setDisable(false);
            }

            if(newToggleValue == tglBtnCitizenTemplateEditOn && newToggleValue != null)
            {

            }
            else if(newToggleValue == tglBtnCitizenTemplateEditOff && newToggleValue != null) {

            }


        });
    }

    private void editModePropertyListener() {
        citizenTemplateEditMode.addListener((observable, oldValue, newValue) -> editComboBoxes(newValue));

        //TODO: Switch table items
        }

    private void editComboBoxes(boolean editable) {
        treeTblViewFunc.setEditable(editable);
        treeTblViewHealth.setEditable(editable);
        for (TreeItem<CategoryEntryModel> cat : treeTblViewFunc.getRoot().getChildren()) {
            cat.getValue().getLevelImageComboBox().setDisable(editable);
        }
        for (TreeItem<CategoryEntryModel> cat : treeTblViewHealth.getRoot().getChildren()) {
            cat.getValue().getLevelImageComboBox().setDisable(editable);
        }

    }

    private void initVisible()
    {
        anchorPaneDashboard.setVisible(false);
        anchorPaneStudents.setVisible(false);
        anchorPaneCitizenTemplate.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneCases.setVisible(false);
        anchorPaneAssignments.setVisible(false);
        anchorPaneJournals.setVisible(false);
    }


    // Students
    public void onViewStudentCases(ActionEvent event) {
        teacherViewModel.viewStudentCases();
    }

    public void onStudentSettings(ActionEvent event) {
        teacherViewModel.studentSettings();
    }

    public void onRemoveCitizenToStudent(ActionEvent event) {
        teacherViewModel.removeCitizenToStudent();
    }

    public void onAddCitizenToStudent(ActionEvent event) {
        teacherViewModel.addStudentToCitizen();
    }

    public void onViewStudentsWork(ActionEvent event) {
        teacherViewModel.viewStudentsWork();
    }




    // Citizens
    public void onGeneralInfo(ActionEvent event) {
        teacherViewModel.generalInfo();
    }

    public void onJournal(ActionEvent event) {
        teacherViewModel.journal();
    }

    public void onRemoveStudentToCitizen(ActionEvent event) {
        teacherViewModel.removeStudentToCitizen();
    }

    public void onAddStudentToCitizen(ActionEvent event) {
        teacherViewModel.addCitizenToStudent();
    }

    public void onCitizensSearch(ActionEvent event) {
        teacherViewModel.citizensSearch();
    }



    // Citizen Template
    public void onCitizenTemplateSearch(ActionEvent event) {
        teacherViewModel.citizenTemplateSearch();
    }

    public void onRemoveCitizenTemplateContactInfo(ActionEvent event) {
        teacherViewModel.removeCitizenTemplateContactInfo();
    }

    public void onAddCitizenTemplateContactInfo(ActionEvent event) {
        teacherViewModel.addCitizenTemplateContactInfo();
    }

    private void setFuncTreeTable(){
        // Set up the table
        CitizenTemplateModel citizenTemplateModel = new CitizenTemplateModel();

        ObservableList<CategoryEntryModel> func = citizenTemplateModel.getFunctionalAbilities();
        ObservableList<CategoryEntryModel> health = citizenTemplateModel.getHealthConditions();

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
        treeTblClmnFuncCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblClmnFuncLevel.setCellValueFactory(param -> param.getValue().getValue().getLevelImageComboBoxProperty());
        treeTblClmnFuncAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblClmnFuncCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblClmnFuncImplications.setCellValueFactory(param -> param.getValue().getValue().implicationsProperty());
        treeTblClmnFuncCitizenGoals.setCellValueFactory(param -> param.getValue().getValue().citizenGoalsProperty());
        treeTblClmnFuncExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblClmnFuncNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());

        treeTblClmnHealthCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblClmnHealthLevel.setCellValueFactory(param -> param.getValue().getValue().levelProperty());
        treeTblClmnHealthAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblClmnHealthCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblClmnHealthExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblClmnHealthNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());
    }

    public void onCitizenTemplateChangeJournal(ActionEvent event) {
        teacherViewModel.citizenTemplateChangeJournal();
    }

    public void onCitizenTemplateEditBaseData(ActionEvent event) {
        teacherViewModel.onCitizenTemplateEditBaseData();
    }

    private void initCitizenTemplatesList(){
        listViewCitizenTemplates.setItems(teacherViewModel.getCitizenTemplates());
        listViewCitizenTemplates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            teacherViewModel.setSelectedCitizenTemplateModel((CitizenTemplateModel) newValue);
            setDataToCitizenTemplateView();
        });
    }

    private void setDataToCitizenTemplateView(){
        lblCitizenTemplateName.setText(teacherViewModel.getSelectedCitizenTemplateModel().getName());
        lblAgeCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getAge() + "");
        lblAddressCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getAddress());
        lblBirthdateCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        lblHelpStatusCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getHelpStatus());
        lblCivilianStatusCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getCivilianStatus());

        listViewCitizenTemplateContactInfo.setItems(teacherViewModel.getSelectedCitizenTemplateModel().getContactInfo());
    }

}
