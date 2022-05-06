package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.DAL.CasesDAO;
import Application.GUI.Models.AccountModel;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddToTaskViewController implements Initializable {

    // FIXME: 06/05/2022 -- DAO for testing purpose, decouple
    CasesDAO casesDAO = new CasesDAO();

    public Button btnSave;
    @FXML
    public Button btnCancel;
    @FXML
    public TableView tblAccountTable;
    @FXML
    public TableColumn<AccountModel> tblClmAttName;
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

    @FXML public TableColumn<AccountModel, String> tblClmAccountName;

    AccountModel accountDAO = new AccountModel();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initEventListeners();
    }

    public AddToTaskViewController()
    {
    }

    public void onSave(ActionEvent actionEvent)
    {
        casesDAO.assignToCase(accountDAO.getAccountList(), );

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

    public void initEventListeners()
    {
        tblClmAccountName.setCellValueFactory(param -> param.getValue().firstNameProperty());



        //Wrap ObservableList of UserInfo in a FilteredList.
        FilteredList<Account> filteredData = new FilteredList<>(accountDAO.getAccountList(), b -> true);

        //Sets the filter predict when filter changes.

        txtAccountSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(account -> {

                //If filter is empty, display all users.
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                //Compare user name with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (account.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                } else return false;
            });
        });
    }


}
