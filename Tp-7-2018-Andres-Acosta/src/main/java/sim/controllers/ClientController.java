package main.java.sim.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.sim.model.clientes.Client;
import main.java.sim.rows.FilaClient;

import java.util.ArrayList;
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
    @FXML
    private TextField txtFrom;
    @FXML
    private TextField txtTo;
    private int from;
    private int to;
    private List<Client> clients;


    private ObservableList<FilaClient> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        verificarTextField(txtFrom);
        verificarTextField(txtTo);
        initColumns();
    }

    private void initColumns() {
        clientDate.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getClientNumberString())));
        stateClient.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getStateString())));
        inTime.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getInTimeString())));
        inServerTime.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getServeTimeString())));
        outTime.setCellValueFactory(e -> new SimpleStringProperty((e.getValue().getOutTimeString())));
//        tvClient.getItems().clear();
        from = -1;
        to = -1;
        clients = new ArrayList();
    }

    public void loadClient(List<Client> clients){
        tvClient.getItems().clear();
        tvClient.getItems().addAll(clients);
        this.clients.addAll(clients);
    }

    public List<Client> loadTable(List<Client> clients){
        return clients;
    }

    @FXML
    private void exitClient(ActionEvent event)  {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void searchClient(ActionEvent e){
        tvClient.getItems().clear();

        if (txtFrom.getText().isEmpty() || Integer.parseInt(txtFrom.getText()) == 0){
            from = 0;
        }else {
            from = Integer.parseInt(txtFrom.getText())-1;
        }
        if (txtTo.getText().isEmpty() || Integer.parseInt(txtTo.getText())> clients.size()){
            to = clients.size();
        }else {
            to = Integer.parseInt(txtTo.getText());
        }
        for (int i = 0; from < to ; i ++) {
            if (i == from && i <=to) {
                tvClient.getItems().add(clients.get(from));
                from ++;
            }
        }
    }
    private void verificarTextField(TextField textField){
        textField.textProperty().addListener(agregarListenerAlTextField(textField));
    }

    private ChangeListener<String> agregarListenerAlTextField(TextField aTextField) {
        return (observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                aTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        };
    }




}
