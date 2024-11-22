package controllers;

import dao.OperationsDAO;
import entity.ClientInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import session_manager.SessionManager;
import window_config.WindowConfigurations;

public class MainPageController {
    OperationsDAO opDAO = new OperationsDAO();

    @FXML
    private TextField passwordField;

    @FXML
    public void login() {
        String password = passwordField.getText();

        if (opDAO.checkPassword(password)) {
            ClientInformation clientInformation = opDAO.getClientInformation(password);

            if (clientInformation != null) {
                SessionManager.login(clientInformation);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/selectionPage.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) passwordField.getScene().getWindow();
                    Scene selectionPage = WindowConfigurations.createScene(root);
                    stage.setScene(selectionPage);
                    stage.show();
                } catch (Exception e) {
                    showError("Error", "Could not load selection page", e.getMessage());
                    e.printStackTrace();
                }
            } else {
                showError("Login Error", "Could not retrieve client information", "Please try again");
            }
        } else {
            showError("Invalid Password", "The password you entered is incorrect", "Please try again");
        }
    }
    private void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
