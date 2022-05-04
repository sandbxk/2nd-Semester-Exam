package Application.GUI.Controllers.Popups;

import Application.BE.School;
import Application.BLL.AdminDataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateTeacherController implements Initializable
{
    @FXML public TextField txtFieldLogin;
    @FXML public TextField txtFieldPassword;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldSurname;
    @FXML public TextField txtFieldEmail;

    private AdminDataManager adminDataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminDataManager = new AdminDataManager();
    }

    public void onSaveCreateTeacher(ActionEvent actionEvent) {

        // FIXME: 03/05/2022 -- Dummy School
        School school = new School(-1, "Dummy School", 6700, "NotARealCity");

        adminDataManager.createAccount(
                txtFieldLogin.getText(),
                txtFieldPassword.getText(),
                txtFieldFirstName.getText(),
                txtFieldSurname.getText(),
                txtFieldEmail.getText(),
                school,
                1
        );

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void onCancelCreateTeacher(ActionEvent actionEvent)
    {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
