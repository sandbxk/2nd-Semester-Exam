package Application.GUI.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateSchoolController implements Initializable {

    @FXML
    private TextField txtsSchoolName;
    @FXML
    private TextField txtSchoolZipCode;
    @FXML
    private Button btnCancel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createSchool(ActionEvent actionEvent) {

    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
