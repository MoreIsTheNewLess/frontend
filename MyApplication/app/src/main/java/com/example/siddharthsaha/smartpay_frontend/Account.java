package com.example.siddharthsaha.smartpay_frontend;

/**
 * Created by Siddharth Saha on 4/1/2017.
 */

public class Account {
    String name,emailid,uname,pas;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String emailid) {
        this.emailid = emailid;
    }

    public String getEmail() {
        return this.emailid;
    }

    public void setUsername(String uname) {
        this.uname = uname;
    }

    public String getUsername() {
        return this.uname;
    }

    public void setPassword(String pas) {
        this.pas = pas;
    }

    public String getPassword() {
        return this.pas;
    }
}
