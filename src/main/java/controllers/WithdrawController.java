package controllers;
import animation_util.AnimationUtility;
import dao.OperationsDAO;
import entity.ClientInformation;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import session_manager.SessionManager;
import util.BalanceMessage;
import validation.MoneyValidation;

import java.util.List;

public class WithdrawController extends BaseController {
   OperationsDAO opDAO = new OperationsDAO();


    @FXML
    private TextField amountField;

    ClientInformation client = SessionManager.getLoggedInClientInformation();
    int clientId = client.getClientId();

    @FXML
    public void handleWithdraw() {
        try {
            double money = Double.parseDouble(amountField.getText());
            String selectedIban = clientIbans.getValue();

            List<Double> balances = opDAO.getBalance(clientId);
            int ibanIndex = clientIbans.getSelectionModel().getSelectedIndex();
            double balance = balances.get(ibanIndex);

            if (money <= balance) {
                opDAO.withdrawMoney(money, selectedIban);

                BalanceMessage balanceMessage = new BalanceMessage(money, ibanIndex, clientId);
                List<String> messages = balanceMessage.updatedMessages();

                Stage currentStage = (Stage) amountField.getScene().getWindow();
                Scene currentScene = amountField.getScene();
                AnimationUtility.showSuccessMessage(currentStage, currentScene, messages.get(0), messages.get(1), this);
            } else {
                MoneyValidation.notEnoughMoney();
            }
            amountField.clear();
        } catch (Exception e) {
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
