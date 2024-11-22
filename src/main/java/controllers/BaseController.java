package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import navigation.Navigation;
import window_config.WindowConfigurations;

import java.util.List;

public abstract class BaseController implements Navigation {
    @FXML
    protected Button backButton;

    @FXML
    protected Text clientBalanceField;

    @FXML
    protected ComboBox<String> clientIbans;

    @FXML
    protected TextField amountField;

    @Override
    public void navigateBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/selectionPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = WindowConfigurations.createScene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        if (backButton != null) {
            backButton.setOnAction(e -> navigateBack());
        }
    }

    public Text getClientBalanceField(){
        return clientBalanceField;
    }

    public ComboBox<String> getDropdownIbans() {
        return clientIbans;
    }
}
