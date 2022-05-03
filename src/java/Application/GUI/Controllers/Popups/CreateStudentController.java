package Application.GUI.Controllers.Popups;

import Application.BLL.AdminDataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateStudentController implements Initializable {

    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;
    @FXML public TextField txtFieldEmail;
    @FXML public TextField txtFieldUsername;
    @FXML public PasswordField passwordField;

    private AdminDataManager adminDataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminDataManager = new AdminDataManager();
    }


    public void onSaveStudent(ActionEvent event) {
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText();
        String username = txtFieldUsername.getText();
        String password = passwordField.getText();

        adminDataManager.createStudent(firstName, lastName, email, username, password, -1, -1);
        //TODO: add getSchool() and implement salt for hashing

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void onCancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }


}
