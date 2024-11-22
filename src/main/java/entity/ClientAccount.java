package entity;


import jakarta.persistence.*;

@Entity
@Table(name = "client_account")
public class ClientAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "client_iban", unique = true, nullable = false)
    private String clientIban;

    @Column(name = "client_balance")
    private int clientBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientInformation clientInformation;

    public ClientAccount() {
    }

    public ClientAccount(String clientIban, int clientBalance, ClientInformation clientInformation) {
        this.clientIban = clientIban;
        this.clientBalance = clientBalance;
        this.clientInformation = clientInformation;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getClientIban() {
        return clientIban;
    }

    public void setClientIban(String clientIban) {
        this.clientIban = clientIban;
    }

    public int getClientBalance() {
        return clientBalance;
    }

    public void setClientBalance(int clientBalance) {
        this.clientBalance = clientBalance;
    }

    public ClientInformation getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(ClientInformation clientInformation) {
        this.clientInformation = clientInformation;
    }
}
