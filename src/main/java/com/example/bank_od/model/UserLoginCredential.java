package com.example.bank_od.model;

import javax.persistence.*;

@Entity
public class UserLoginCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long login_logout_id;
    Long user_id;
    String user_login_id;
    String user_login_password;

    public Long getLogin_logout_id() {
        return login_logout_id;
    }

    public void setLogin_logout_id(Long login_logout_id) {
        this.login_logout_id = login_logout_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getUser_login_id() {
        return user_login_id;
    }

    public void setUser_login_id(String user_login_id) {
        this.user_login_id = user_login_id;
    }

    public String getUser_login_password() {
        return user_login_password;
    }

    public void setUser_login_password(String user_login_password) {
        this.user_login_password = user_login_password;
    }
}
