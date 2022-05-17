package Application.GUI.Controllers;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.StudentViewControllerModel;
import Application.GUI.Models.HealthLevels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {

    @FXML public ListView<CitizenModel> listViewCitizens;
    @FXML public TextField txtFieldCitizenSearch;
    @FXML public Button btnCitizenSearch;
    @FXML public Label lblCitizenName;
    @FXML public Label lblAge;
    @FXML public TableView<CategoryEntryModel> tblViewStudentDashboardHealth;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardHealthCategory;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardHealthLevel;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardHealthNote;
    @FXML public TableView<CategoryEntryModel> tblViewStudentDashboardFunc;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardFuncCategory;
    @FXML public TableColumn<CategoryEntryModel, ImageView> tblColumnStudentDashboardFuncLevel;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardFuncNote;



    private StudentViewControllerModel model = new StudentViewControllerModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableViews();
        initListViewCitizens();
        initTestData();
    }

    private void initTestData() {
        ObservableList<CitizenModel> citizens = FXCollections.observableArrayList();
        citizens.add(new CitizenModel());
        citizens.add(new CitizenModel());
        listViewCitizens.setItems(citizens);
    }

    private void initTableViews() {
        tblColumnStudentDashboardHealthCategory.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        tblColumnStudentDashboardHealthLevel.setCellValueFactory(cellData -> cellData.getValue().levelHealthProperty());
        tblColumnStudentDashboardHealthNote.setCellValueFactory(cellData -> cellData.getValue().noteProperty());
        tblColumnStudentDashboardFuncCategory.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        tblColumnStudentDashboardFuncLevel.setCellValueFactory(cellData -> cellData.getValue().levelFuncProperty());
        tblColumnStudentDashboardFuncNote.setCellValueFactory(cellData -> cellData.getValue().noteProperty());

        tblViewStudentDashboardHealth.setFixedCellSize(50);

        tblViewStudentDashboardHealth.getItems().add(new CategoryEntryModel("Health", HealthLevels.RELEVANT.ordinal(), ""));

        tblViewStudentDashboardFunc.getItems().add(new CategoryEntryModel("FUNCY", HealthLevels.RELEVANT.ordinal(), ""));
    }

    private void updateCitizenInfo(CitizenModel citizen) {
        lblCitizenName.setText(citizen.getName() + " " + citizen.getSurname());
        lblAge.setText(citizen.getAge() + "");

        tblViewStudentDashboardHealth.setItems(model.getSelectedCitizen().getAllHealthConditions());
        tblViewStudentDashboardFunc.setItems(model.getSelectedCitizen().getRelevantFunctionalAbilities());
    }

    private void initListViewCitizens(){
        listViewCitizens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                model.setSelectedCitizen(newValue);
                updateCitizenInfo(newValue);
            }
        });
        listViewCitizens.setItems(model.getAllCitizens());
    }


    public void onStudentCitizensSearch(ActionEvent event) {
        model.onStudentCitizensSearch();
    }

    public void onOpenJournal(ActionEvent event) {
        model.onOpenJournal();
    }

    public void onViewCases(ActionEvent event) {
        model.onViewCases();
    }

    public void onAddObservation(ActionEvent event) {
        model.onAddObservation();
    }
}
