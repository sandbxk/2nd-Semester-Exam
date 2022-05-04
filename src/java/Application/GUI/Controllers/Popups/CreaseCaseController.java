package Application.GUI.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreaseCaseController {

    @FXML public Button btnCancel;
    @FXML public Button btnSave;


    public void save(ActionEvent actionEvent)
    {


        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }


    public void cancel(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
