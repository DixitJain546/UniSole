package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class LoginHistory {
    @SerializedName("loginId")
    String loginId;
    @SerializedName("email")
    String email;
    @SerializedName("date")
    Date date;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
