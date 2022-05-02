package Application.GUI.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateStudentController implements Initializable {

    public TextField txtFieldFirstName;
    public TextField txtFieldLastName;
    public TextField txtFieldEmail;
    public TextField txtFieldUsername;
    public PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onSaveStudent(ActionEvent event) {
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText();
        String username = txtFieldUsername.getText();
        String password = passwordField.getText();

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void onCancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }


}
