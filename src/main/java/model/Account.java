package model;

public class Account{
    long id;
    String username;
    String Password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Account(long id, String username, String password) {
        this.id = id;
        this.username = username;
        Password = password;
    }
}
