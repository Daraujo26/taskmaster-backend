package com.taskmaster.models.response;

import com.taskmaster.models.user.AppUser;

public class AuthenticationResponse {

    private String jwt;
    private AppUser user;

    public AuthenticationResponse(String jwt, AppUser user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
