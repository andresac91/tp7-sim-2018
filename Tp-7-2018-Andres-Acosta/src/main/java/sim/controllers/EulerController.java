package main.java.sim.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.sim.Euler.Euler;
import main.java.sim.rows.FilaEuler;

import java.util.List;

public class EulerController {

    @FXML
    private TableView<FilaEuler> tvEuler;

    @FXML
    private TableColumn step;
    @FXML
    private TableColumn time;
    @FXML
    private TableColumn meters;
    @FXML
    private TableColumn  fuction;
    @FXML
    private TableColumn timePlusOne;
    @FXML
    private TableColumn meterPlusOne;

    @FXML
    private Button btnExit;

    @FXML
    public void initialize(){
        initColumns();
    }

    private void initColumns() {
        step.setCellValueFactory(new PropertyValueFactory<>("step"));
        time.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        meters.setCellValueFactory(new PropertyValueFactory<>("metros"));
        fuction.setCellValueFactory(new PropertyValueFactory<>("dMdT"));
        timePlusOne.setCellValueFactory(new PropertyValueFactory<>("tiempoM1"));
        meterPlusOne.setCellValueFactory(new PropertyValueFactory<>("metrosM1"));
    }

    public void loadTable(List<FilaEuler> listEuler){
        Euler euler = new Euler();
        euler.resultado();
        tvEuler.getItems().addAll(listEuler);
    }

    @FXML
    private void exitEuler(ActionEvent e){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
