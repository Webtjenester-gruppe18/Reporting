package ws18.Model.Fastmoney;

public class FastmoneyAccount {

    private Integer balance;
    private User user;

    public FastmoneyAccount() {}

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FastmoneyAccount(Integer balance, User user) {
        this.balance = balance;
        this.user = user;
    }
}