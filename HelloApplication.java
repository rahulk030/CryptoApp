package org.example.cryptodata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file from resources
            URL fxmlLocation = getClass().getResource("/crypto_view.fxml");
            if (fxmlLocation == null) {
                throw new RuntimeException("FXML file not found: /crypto_view.fxml");
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            // Set the title and scene
            primaryStage.setTitle("Cryptocurrency Data");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
