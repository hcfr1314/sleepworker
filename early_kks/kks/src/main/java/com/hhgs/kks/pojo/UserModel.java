package com.hhgs.kks.pojo;

import java.util.List;

public class UserModel {


    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserModel(List<User> users) {
        super();
        this.users = users;
    }

    public UserModel() {
        super();
    }
}
