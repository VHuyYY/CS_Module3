package entity;

public class Account {
    private int id;
    private String account;
    private String password;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Account(String account, String password, int role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }

    public Account(int id, String account, String password, int role) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.role = role;
    }
}
