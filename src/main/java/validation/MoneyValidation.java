package validation;

import javafx.scene.control.Alert;

public class MoneyValidation {
    public static void notEnoughMoney(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Balance Exceeded");
        alert.setHeaderText("You don't have enough money");
        alert.setContentText("Please enter a valid number");
        alert.showAndWait();
    }
}
