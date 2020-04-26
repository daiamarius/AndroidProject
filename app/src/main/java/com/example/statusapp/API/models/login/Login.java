package com.example.statusapp.API.models.login;

import android.util.Base64;

public class Login {
    private String user;
    private String password;

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String toHeader(){
        String base = this.user+":"+this.password;
        String header = "Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        return header;
    }
}
