package javafxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("auction_gaps.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setStage(primaryStage);

        Scene scene = new Scene(root, 300, 275);

        primaryStage.setTitle("Auction Gaps");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
