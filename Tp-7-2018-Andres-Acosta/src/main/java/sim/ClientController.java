package main.java.sim;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.java.sim.model.clientes.Client;
import main.java.sim.rows.FilaClient;

import java.util.List;


public class ClientController {



    @FXML
    private TableView<Client> tvClient;
    @FXML
    private TableColumn<Client, String> clientDate;
    @FXML
    private TableColumn<Client, String> stateClient;
    @FXML
    private TableColumn<Client, String> inTime;
    @FXML
    private TableColumn<Client, String> inServerTime;
    @FXML
    private TableColumn<Client, String> outTime;
    @FXML
    private Button btnExit;



    private ObservableList<FilaClient> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        initColumns();
    }

    private void initColumns() {
        clientDate.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getClientNumberString())));
        stateClient.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getStateString())));
        inTime.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getInTimeString())));
        inServerTime.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getServeTimeString())));
        outTime.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getOutTimeString())));
    }

    public void addClientsToTable(List<Client> clients){
        tvClient.getItems().clear();
        tvClient.getItems().addAll(clients);

    }

    @FXML
    private void exitClient(ActionEvent event)  {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }



}
