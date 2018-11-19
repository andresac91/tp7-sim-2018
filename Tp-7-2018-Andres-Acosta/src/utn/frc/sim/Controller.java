package utn.frc.sim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utn.frc.sim.simulation.SimulationFinishedException;
import utn.frc.sim.simulation.SimulationWrapper;
import utn.frc.sim.util.Fila;

public class Controller {

    private SimulationWrapper simulation;
    private ObservableList<Fila> data = FXCollections.observableArrayList();

    @FXML
    public TableColumn event;
    @FXML
    public TableColumn clock;
    @FXML
    public TableColumn client;
    @FXML
    public TableColumn nextClient;
    @FXML
    public TableColumn stateMagicCarpet;
    @FXML
    public TableColumn clientMagicCarpet;
    @FXML
    public TableColumn endMagicCarpet;
    @FXML
    public TableColumn queueMagicCarpet;
    @FXML
    private TableView<Fila> tvSim;

    @FXML
    private Label maxCola;

    @FXML
    private Label maxEspera;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnRun;



    @FXML
    void resetClick(ActionEvent event) {
        enableRunButtons();
        resetStatistics();
        startNewSimulation();
        clearItemsInTableView();
    }

    private void clearItemsInTableView() {
        tvSim.getItems().clear();
    }
    private void resetSimulation() {
        enableSemiautomaticButton();
        clearItemsInTableView();
        initializeNewSimulation();
    }
    private void enableSemiautomaticButton() {
        btnRun.setDisable(false);
    }


    @FXML
    void runClick(ActionEvent event) {
        resetSimulation();
        runSimulationToEnd();
    }


    @FXML
    public void initialize(){
        startNewSimulation();
    }


    private void startNewSimulation() {

        tvSim.getItems().clear();
        simulation = SimulationWrapper.ofType();
    }
    private void runSimulationToEnd() {

        runSimulation(Boolean.TRUE);
    }


    private void runSimulation(boolean auto) {

        while (true) {
            try {
                runOneStepOfSimulationAndAddToTable();
            } catch (SimulationFinishedException e) {
                finishSimulation();

                break;
            }
        }

    }


    private void runOneStepOfSimulationAndAddToTable() throws SimulationFinishedException {
        simulation.step();
        loadTable();
    }

    private void initializeNewSimulation() {

        simulation = SimulationWrapper.ofType();
        data = FXCollections.observableArrayList();
    }

    private void finishSimulation() {

       setStatistics();
        disableRunButtons();
    }

    private void setStatistics() {
        maxCola.setText(simulation.getMaxAmountInQueue());
        maxEspera.setText(simulation.getMaxDurationInQueue());

    }

    private void disableRunButtons() {
        btnRun.setDisable(Boolean.FALSE);

    }

    private void resetStatistics() {
    maxCola.setText("");
    maxEspera.setText("");
    }





    private void enableRunButtons() {
        btnRun.setDisable(Boolean.FALSE);
      //  btnStep.setDisable(Boolean.FALSE);
    }

    private void loadTable(){
       String eventContent = simulation.getLastEvent();
       String clockContent = simulation.getClock();
       String clientContent = simulation.getClientOfEventNumber();
       String nextClientContent = simulation.getNextClientEvent();
       String stateMagicCarpetContent = simulation.getMagicCarpetState();
       String clientMagicCarpetContent = simulation.getMagicCarpetClient();
       String endMagicCarpetContent = simulation.getMagicCarpetNextEvent();
       String queueMagicCarpetContent = simulation.getMagicCarpetQueueSize();

       data.addAll(new Fila (eventContent, clockContent, clientContent, nextClientContent, stateMagicCarpetContent,clientMagicCarpetContent,endMagicCarpetContent, queueMagicCarpetContent));

        event.setCellValueFactory(new PropertyValueFactory<>("event"));
        clock.setCellValueFactory(new PropertyValueFactory<>("clock"));
        client.setCellValueFactory(new PropertyValueFactory<>("client"));
        nextClient.setCellValueFactory(new PropertyValueFactory<>("nextClient"));
        stateMagicCarpet.setCellValueFactory(new PropertyValueFactory<>("stateMagicCarpet"));
        clientMagicCarpet.setCellValueFactory(new PropertyValueFactory<>("clientMagicCarpet"));
        endMagicCarpet.setCellValueFactory(new PropertyValueFactory<>("endMagicCarpet"));
        queueMagicCarpet.setCellValueFactory(new PropertyValueFactory<>("queueMagicCarpet"));

        tvSim.setItems(data);

    }




}
