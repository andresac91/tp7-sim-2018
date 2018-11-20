package main.java.sim.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StatisticsController {

    @FXML
    private Label maxQueue;
    @FXML
    private Label maxTimeInQueue;
    @FXML
    private Button btnExit;

    @FXML
    public void initialize(){
;
        initStatistics();
    }

    public void setStatistics(String maxTime , String maxQueue){
        this.maxQueue.setText(maxQueue);
        this.maxTimeInQueue.setText(maxTime);
    }

    private void initStatistics(){
        this.maxTimeInQueue.setText("0 seg");
        this.maxQueue.setText("0");
    }

    @FXML
    private void exitStatistics(ActionEvent event)  {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
