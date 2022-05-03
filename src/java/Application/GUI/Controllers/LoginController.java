package Application.GUI.Controllers;

import Application.GUI.Models.AccountModel;
import Application.GUI.Models.UserType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    private AccountModel account;

    @FXML
    public TextField txtUsername;

    @FXML
    public TextField txtPwd;

    @FXML
    public Button btnSubmit;

    @FXML
    public ComboBox<UserType> cbUserTypeSelect;

    public LoginController()
    {
        this.txtUsername = new TextField();
        this.txtPwd = new TextField();
        this.btnSubmit = new Button();
        this.cbUserTypeSelect = new ComboBox<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbUserTypeSelect.setItems(FXCollections.observableArrayList(UserType.values()));
    }

    public void onSubmit(ActionEvent event)
    {
        String uname = this.txtUsername.getText();
        String pwd = this.txtPwd.getText();

        account = new AccountModel(uname, pwd, cbUserTypeSelect.getValue());

        if (account.authenticate())
        {
            if (account.type == UserType.STUDENT)
            {
                // switch to student dashboard
                    // pass in account info
            }
        }
        else
        {
            System.err.println("wrong credentials");
            // post error message
                // (wrong username / password)
        }
    }
}
