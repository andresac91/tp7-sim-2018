package main.java.sim;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.sim.Euler.Euler;
import main.java.sim.controllers.ClientController;
import main.java.sim.controllers.EulerController;
import main.java.sim.controllers.StatisticsController;
import main.java.sim.rows.Fila;
import main.java.sim.simulation.SimulationFinishedException;
import main.java.sim.simulation.SimulationWrapper;

import java.io.IOException;
import java.util.Optional;

public class Controller<pivate> {

    private SimulationWrapper simulation;
    private ObservableList<Fila> data = FXCollections.observableArrayList();
    private Euler euler = new Euler();

    private Optional<ClientController> controllerClient;
    private Optional<StatisticsController> controllerStatistic;
    private Optional<EulerController> controllerEuler;
    private Stage dialog;

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
    private Hyperlink hypClient;
    @FXML
    private Hyperlink hypStatistics;
    @FXML
    private Hyperlink hypEuler;

    @FXML
    private Button btnRun;

    @FXML
    private TextField fromHour;
    @FXML
    private TextField toHour;
    private int from , to;

    @FXML
    public void initialize(){
        initializeController();
        startNewSimulation();
        verificarTextField(toHour);
        verificarTextField(fromHour);
        initValues();
    }

    private void initValues() {
        from = 9;
        to = 24;
    }

    @FXML
    void resetClick(ActionEvent event) {
        enableRunButtons();
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



    private void startNewSimulation() {
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
        searchClient();
    }

    private void initializeNewSimulation() {
        simulation = SimulationWrapper.ofType();
        data = FXCollections.observableArrayList();
    }

    private void finishSimulation() {
        disableRunButtons();
    }

    private void disableRunButtons() {
        btnRun.setDisable(Boolean.FALSE);
    }

    private void enableRunButtons() {
        btnRun.setDisable(Boolean.FALSE);
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

    private Optional<ClientController> showTableClient(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/client.fxml"));
            openScene(loader.load(),"Clientes");
            return Optional.ofNullable(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
            return  Optional.empty();
        }
    }

    private Optional<StatisticsController> showStatistic(){
        try{
            FXMLLoader loader = new FXMLLoader((getClass().getResource("view/statistics.fxml")));
            openScene((loader.load()),"Resultados");
            return  Optional.ofNullable(loader.getController());
        }catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<EulerController> showTableEuler(){
        try{
            FXMLLoader loader = new FXMLLoader((getClass().getResource("view/euler.fxml")));

            openScene((loader.load()),"Calculo de Euler");
            return  Optional.ofNullable(loader.getController());
        }catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private void openScene(Parent parent, String title){
        dialog = new Stage();
        Scene scene = new Scene(parent);
        dialog.setScene(scene);
        dialog.setTitle(title);
        dialog.show();
    }

    @FXML
    private void openClient(ActionEvent event){
        showClient();
        hypClient.setVisited(Boolean.FALSE);
    }

    private void showClient() {
        if (!controllerClient.isPresent()) {
            controllerClient = showTableClient();
            controllerClient.ifPresent(c ->c.loadClient(simulation.getClient()));
        }
        initializeController();
    }

    private void showStatistics() {
        if (!controllerStatistic.isPresent()) {
            controllerStatistic = showStatistic();
            controllerStatistic.ifPresent(c -> c.setStatistics(simulation.getMaxDurationInQueue(),simulation.getMaxAmountInQueue()));
        }
        initializeController();
    }

    private void initializeController() {
        controllerClient = Optional.empty();
        controllerStatistic = Optional.empty();
        controllerEuler = Optional.empty();
    }

    @FXML
    private void openStatistic(ActionEvent e){
        showStatistics();
        hypStatistics.setVisited(Boolean.FALSE);
    }
    
    @FXML
    private void openEuler(ActionEvent e){
        showEuler();
        hypEuler.setVisited(Boolean.FALSE);
    }

    private void showEuler() {
        if (!controllerEuler.isPresent()) {
            controllerEuler = showTableEuler();
            controllerEuler.ifPresent(c -> c.loadTable(euler.resultado()));
        }
        initializeController();
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


    private void searchClient(){
        if(!fromHour.getText().isEmpty()){
            from = Integer.parseInt(fromHour.getText());
        }
        if(!toHour.getText().isEmpty()){
            to = Integer.parseInt(toHour.getText());
        }
        if (simulation.getClockTime().getHour()>= from && simulation.getClockTime().getHour()< to) {
            loadTable();
        }
    }

}
