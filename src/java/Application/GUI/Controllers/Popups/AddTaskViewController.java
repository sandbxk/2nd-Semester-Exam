package Application.GUI.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTaskViewController {
    public Button btnSave;
    @FXML
    public Button btnCancel;
    @FXML
    public TableView tblAccountTable;
    @FXML
    public TableColumn tblClmAttName;
    @FXML
    public TableColumn tblClmAttName1;
    @FXML
    public Button btnAddAccount;
    @FXML
    public Button btnRemoveAccount;
    @FXML
    public TableView tblAddedAccounts;
    @FXML
    public TableColumn clmAddedUserName;
    @FXML
    public TextField txtAccountSearch;
    @FXML
    public Button btnSearch;
    @FXML
    public ComboBox comboBoxViewType;

    public void onSave(ActionEvent actionEvent)
    {


        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent)
    {
        

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void addAccountToList(ActionEvent actionEvent) {
    }

    public void removeAccountFromList(ActionEvent actionEvent) {
    }
}
