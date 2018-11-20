package main.java.sim;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sim1.fxml"));
        primaryStage.setTitle("TP7 - 2018 - ACOSTA ANDRES");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(e -> forceClose());
        primaryStage.show();
    }
    private static void forceClose() {
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);


    }


}
