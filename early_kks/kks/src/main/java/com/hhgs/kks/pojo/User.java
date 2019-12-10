package com.hhgs.kks.pojo;

import java.io.Serializable;

public class User implements Serializable {

    //用户名
    private String username;
    //密码
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User() {
        super();
    }
}
