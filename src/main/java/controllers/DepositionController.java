package controllers;

import animation_util.AnimationUtility;
import dao.OperationsDAO;
import entity.ClientInformation;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import session_manager.SessionManager;
import util.BalanceMessage;

import java.util.List;

public class DepositionController extends BaseController {
    OperationsDAO opDAO = new OperationsDAO();

    ClientInformation client = SessionManager.getLoggedInClientInformation();
    int clientId = client.getClientId();

    @FXML
    public void handleDeposit() {
        try {
            double money = Double.parseDouble(amountField.getText());
            String selectedIban = clientIbans.getValue();
            opDAO.depositMoney(money, selectedIban);

            int ibanIndex = clientIbans.getSelectionModel().getSelectedIndex();
            BalanceMessage balanceMessage = new BalanceMessage(money, ibanIndex, clientId);
            List<String> messages = balanceMessage.updatedMessages();

            Stage currentStage = (Stage) amountField.getScene().getWindow();
            Scene currentScene = amountField.getScene();
            AnimationUtility.showSuccessMessage(currentStage, currentScene, messages.get(0), messages.get(1), this);
            amountField.clear();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        super.initialize();
        clientIbans.setOnAction(e -> {
            int ibanIndex = clientIbans.getSelectionModel().getSelectedIndex();
            List<Double> balances = opDAO.getBalance(clientId);
            Double balance = balances.get(ibanIndex);
            String balanceMessage = String.format("Your balance \n%.2f TL", balance);
            clientBalanceField.setText(balanceMessage);
        });
    }
}
