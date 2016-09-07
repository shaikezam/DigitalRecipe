package com.example.shayzambrovski.digitalrecipe;

/**
 * Created by Shay Zambrovski on 07/09/2016.
 */
public class User {
    String userName;
    String password;
    String eMail;

    public User(String userName, String password, String eMail) {
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
