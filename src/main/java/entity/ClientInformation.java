package entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "client_data")
public class ClientInformation {
    @Id
    @Column(name = "id")
    private int clientId;

    @Column(name = "client_firstname")
    private String clientFirstName;

    @Column(name = "client_Lastname")
    private String clientLastName;

    @Column(name = "client_password")
    private String clientPassword;

    @OneToMany(mappedBy = "clientInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClientAccount> accounts;

    @Column(name = "bank_card_number")
    private String bankCardNumber;

    public ClientInformation(int clientId, String clientFirstName, String clientLastName,
                             String clientPassword, List<ClientAccount> accounts, String bankCardNumber) {
        this.clientId = clientId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPassword = clientPassword;
        this.accounts = accounts;
        this.bankCardNumber = bankCardNumber;
    }

    public ClientInformation() {}

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }


    public List<ClientAccount> getAccounts() {
        return accounts;
    }


    public void setAccounts(List<ClientAccount> accounts) {
        this.accounts = accounts;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }
}
