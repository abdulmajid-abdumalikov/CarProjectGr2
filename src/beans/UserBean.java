package beans;

public class UserBean extends BaseIDBean {
    private String username;
    private String password;
    private Integer balance;

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 1000;
    }

    public UserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "ID= " + getID() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
