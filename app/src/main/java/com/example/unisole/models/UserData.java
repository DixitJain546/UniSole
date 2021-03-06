package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("userName")
    String userName;
    @SerializedName("email")
    String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
