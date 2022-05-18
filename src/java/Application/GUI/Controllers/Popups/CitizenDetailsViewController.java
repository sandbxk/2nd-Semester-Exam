package Application.GUI.Controllers.Popups;

import Application.BE.GeneralJournal;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.StudentViewControllerModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class CitizenDetailsViewController implements Initializable {

    public Label lblCitizenFirstName;
    public Label lblCitizenSurname;
    public Label lblCitizenAge;

    // Functional Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewFunc;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncImplications;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCitizenGoals;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncNote;

    // Health Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewHealth;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<HealthLevels>> treeTblColumnHealthLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCause;
    public TreeTableColumn<CategoryEntryModel, ComboBox<HealthLevels>> treeTblColumnHealthExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthNote;

    // General Information
    public TextArea txtAreaGenInfoCoping;
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

    public Button btnAddObservation;
    public Button btnEditObservation;
    public Button btnBackToDashboard;

    private StudentViewControllerModel model = new StudentViewControllerModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBundle(resources);
        setDataToCitizenTemplateView();
    }


    private void initBundle(ResourceBundle bundle) {
        if (bundle.getObject("selectedCitizen") != null){
            model.setSelectedCitizen((CitizenModel) bundle.getObject("selectedCitizen"));
        }
    }

    /**
     * sets every compononent of the citizen details view to the values of the selected citizen.
     */
    private void setDataToCitizenTemplateView() {
        if (model.getSelectedCitizen() != null) {
            //set the base data of name, surname and age to that of the selected citizen template
            lblCitizenFirstName.setText(model.getSelectedCitizen().getName());
            lblCitizenSurname.setText(model.getSelectedCitizen().getSurname());
            lblCitizenAge.setText(String.valueOf(model.getSelectedCitizen().getAge()));

            //set the functional abilities TreeTableView to the values of the selected citizen template
            TreeItem<CategoryEntryModel> funcRoot = new TreeItem<>();
            funcRoot.getChildren().addAll(model.getRelevantFuncCategoriesAsTreeItem());
            treeTblViewFunc.setRoot(funcRoot);
            treeTblViewFunc.setShowRoot(false);

            //set the health categories to the health categories of the selected citizen template
            TreeItem<CategoryEntryModel> healthRoot = new TreeItem<>();
            healthRoot.getChildren().addAll(model.getRelevantHealthCategoriesAsTreeItem());
            treeTblViewHealth.setRoot(healthRoot);
            treeTblViewHealth.setShowRoot(false);

            GeneralJournal journal = model.getSelectedCitizen().getBeCitizen().getGeneralInfo();

            //set the general information section to that of the selected citizen template
            txtAreaGenInfoCoping.setText(journal.getCoping());
            txtAreaGenInfoMotivation.setText(journal.getMotivation());
            txtAreaGenInfoResources.setText(journal.getResources());
            txtAreaGenInfoRoles.setText(journal.getRoles());
            txtAreaGenInfoHabits.setText(journal.getHabits());
            txtAreaGenInfoEduAndJob.setText(journal.getEduAndJob());
            txtAreaGenInfoLifeStory.setText(journal.getLifeStory());
            txtAreaGenInfoHealthInfo.setText(journal.getHealthInfo());
            txtAreaGenInfoAssistiveDevices.setText(journal.getAssistiveDevices());
            txtAreaGenInfoHomeLayout.setText(journal.getHomeLayout());
            txtAreaGenInfoNetwork.setText(journal.getNetwork());
        }

    }

    public void onAddObservation(ActionEvent event) {

    }

    public void onEditObservation(ActionEvent event) {
        TreeItem selectedItem = treeTblViewFunc.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Vælg");
        }
    }

    public void onBackToDashboard(ActionEvent event) {
        Stage stage = (Stage) btnBackToDashboard.getScene().getWindow();
        Parent root = null;

        try {
            ResourceBundle resources = new ListResourceBundle()
            {
                @Override
                protected Object[][] getContents()
                {
                    return new Object[][]{  {"selectedCitizen", model.getSelectedCitizen()}};
                }
            };


            root = FXMLLoader.load(getClass().getResource("/Views/StudentView.fxml"), resources);
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
