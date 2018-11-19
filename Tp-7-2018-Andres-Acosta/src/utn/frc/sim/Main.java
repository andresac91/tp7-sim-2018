package utn.frc.sim;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utn.frc.sim.Euler.Euler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sim1.fxml"));
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
        Euler euler = new Euler();
        euler.resultado();
        System.out.println(String.valueOf(euler.getTiempoM1()));

        launch(args);


    }


}
