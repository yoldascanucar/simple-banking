package util;

import controllers.BaseController;
import dao.OperationsDAO;
import entity.ClientInformation;
import session_manager.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class BalanceMessage extends BaseController {
    private final double money;
    private final int ibanIndex;
    private final int clientId;

    public BalanceMessage(double money, int ibanIndex, int clientId) {
        this.money = money;
        this.ibanIndex = ibanIndex;
        this.clientId = clientId;
    }

    public List<String> updatedMessages() {
        OperationsDAO opDAO = new OperationsDAO();
        List<Double> updatedBalances = opDAO.getBalance(clientId);
        double newBalance = updatedBalances.get(ibanIndex);

        String message = String.format("Successfully deposited %.2f TL", money);
        String balanceMessage = String.format("Your balance \n%.2f TL", newBalance);

        List<String> messages = new ArrayList<>();
        messages.add(message);
        messages.add(balanceMessage);

        return messages;
    }
}
