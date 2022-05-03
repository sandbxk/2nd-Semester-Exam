package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AdminView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 830);
            stage.setTitle("Popkernel Time");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
