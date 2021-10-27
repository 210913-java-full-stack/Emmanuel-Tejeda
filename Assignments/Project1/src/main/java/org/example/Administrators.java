package org.example;

import javax.persistence.*;

@Entity
public class Administrators {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminID;

    private String userName;
    private String password;


    public Administrators(){}

    public int getAdminID() {
        return adminID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


