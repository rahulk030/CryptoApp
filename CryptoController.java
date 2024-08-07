package org.example.cryptodata;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressIndicator;

public class CryptoController {

    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;

    @FXML
    private Label keyLabel;
    @FXML
    private Label holderCountLabel;
    @FXML
    private Label dailyActiveLabel;
    @FXML
    private Label totalLabel;

    @FXML
    private Label errorLabel; // Label for displaying error messages

    @FXML
    private ProgressIndicator progressIndicator; // Loading indicator

    @FXML
    private void handleSearch() {
        String searchKey = searchField.getText();
        CryptoService cryptoService = new CryptoService(searchKey);

        progressIndicator.setVisible(true); // Show loading indicator

        cryptoService.setOnSucceeded(event -> {
            CryptoCurrency result = cryptoService.getValue();
            progressIndicator.setVisible(false); // Hide loading indicator
            if (result != null) {
                keyLabel.setText("Key: " + result.getKey());
                holderCountLabel.setText("Holder Count: " + result.getHolderCount());
                dailyActiveLabel.setText("Daily Active: " + result.getDailyActive());
                totalLabel.setText("Total: " + result.getTotal());
                errorLabel.setText(""); // Clear error label on success
            } else {
                keyLabel.setText("No data available.");
            }
        });

        cryptoService.setOnFailed(event -> {
            progressIndicator.setVisible(false); // Hide loading indicator
            Throwable exception = cryptoService.getException();
            if (exception != null) {
                errorLabel.setText("Failed to load data: " + exception.getMessage());
            } else {
                errorLabel.setText("Failed to load data.");
            }
        });

        cryptoService.start();
    }
}
