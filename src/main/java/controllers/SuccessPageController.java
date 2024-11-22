package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SuccessPageController {
    @FXML
    private Label transactionText;

    public void setTransactionMessage(String message) {
        transactionText.setText(message);
    }
}
