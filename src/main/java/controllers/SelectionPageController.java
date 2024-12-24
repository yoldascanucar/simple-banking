package controllers;

import dao.OperationsDAO;
import entity.ClientInformation;
import entity.ClientAccount;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import session_manager.SessionManager;
import util.StageUtil;
import window_config.WindowConfigurations;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SelectionPageController extends BaseController {
    OperationsDAO opDAO = new OperationsDAO();
    StageUtil stageUtil = new StageUtil();

    @FXML
    private Button depositPageBtn;

    @FXML
    private Button withdrawPageBtn;

    @FXML
    private Button eftPageBtn;

    @FXML
    private Button logoutBtn;
     // Get the ClientInformation object
    ClientInformation loggedInClient = SessionManager.getLoggedInClientInformation();
    // Get all Iban numbers for the client using the accounts relationship
    List<String> loggedInClientIbanNumbers = loggedInClient.getAccounts()
            .stream()
            .map(ClientAccount::getClientIban)
            .collect(Collectors.toList());


    @FXML
    public void accessDepositPage() throws IOException {
       stageUtil.loadAndDisplayStage(depositPageBtn, loggedInClientIbanNumbers, getClass().getResource("/fxml/depositionPage.fxml"));
    }

     @FXML
     public void accessWithdrawPage() throws IOException {
     stageUtil.loadAndDisplayStage(withdrawPageBtn, loggedInClientIbanNumbers, getClass().getResource("/fxml/withdrawalPage.fxml"));
     }

     @FXML
     public void accessEFTPage() throws IOException {
     stageUtil.loadAndDisplayStage(eftPageBtn, loggedInClientIbanNumbers, getClass().getResource("/fxml/eftPage.fxml"));
     }


    public void logout(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            Scene scene = WindowConfigurations.createScene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
