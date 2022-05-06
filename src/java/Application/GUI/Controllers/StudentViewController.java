package Application.GUI.Controllers;

import eu.hansolo.fx.charts.SectorChart;
import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.series.ChartItemSeries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {
    @FXML public ListView listViewCitizens;
    @FXML public TextField txtFieldCitizenSearch;
    @FXML public Button btnCitizenSearch;
    @FXML public Label lblCitizenName;
    @FXML public ListView listViewCitizenContactInfo;
    @FXML public Label lblAge;
    @FXML public Label lblBirthdate;
    @FXML public Label lblAddress;
    @FXML public Label lblHelpStatus;
    @FXML public Label lblCivilianStatus;
    @FXML public TableView tblViewStudentDashboardHealth;
    @FXML public TableColumn tblColumnStudentDashboardHealthCategory;
    @FXML public TableColumn tblColumnStudentDashboardHealthLevel;
    @FXML public TableColumn tblColumnStudentDashboardHealthNote;
    @FXML public TableView tblViewStudentDashboardFunc;
    @FXML public TableColumn tblColumnStudentDashboardFuncCategory;
    @FXML public TableColumn tblColumnStudentDashboardFuncLevel;
    @FXML public TableColumn tblColumnStudentDashboardFuncNote;
    @FXML public AnchorPane anchorPaneChartContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChart();
    }

    private void initChart(){
        SectorChart chart = new SectorChart();;
        chart.setPrefSize(anchorPaneChartContainer.getPrefWidth(), anchorPaneChartContainer.getPrefHeight());
        anchorPaneChartContainer.getChildren().add(chart);
        chart.setAllSeries(initSeries());
    }

    private List<ChartItemSeries<ChartItem>> initSeries(){
        List<ChartItemSeries<ChartItem>> seriesList = new ArrayList<>();

        //for(int i = 0; i < 5; i++) {
            ChartItemSeries<ChartItem> series = new ChartItemSeries<>();
            series.getItems().add(new ChartItem("Category 1", 10));
            series.getItems().add(new ChartItem("Category 15", 30));
            series.getItems().add(new ChartItem("Category 11", 50));
            series.getItems().add(new ChartItem("Category 771", 20));
            seriesList.add(series);
            series.setName("Series 1");
        //}

        return seriesList;
    }

    public void onStudentCitizensSearch(ActionEvent event) {
    }

    public void onOpenJournal(ActionEvent event) {
    }

    public void onViewCases(ActionEvent event) {
    }

    public void onAddObservation(ActionEvent event) {
    }
}
