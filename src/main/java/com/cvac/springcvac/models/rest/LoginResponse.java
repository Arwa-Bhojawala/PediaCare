package com.cvac.springcvac.models.rest;

import com.cvac.springcvac.models.enums.LoginStatus;
import org.springframework.lang.Nullable;

public class LoginResponse {
    private LoginStatus status;
    @Nullable
    private String msg;
    @Nullable
    private String cookie;

    public LoginStatus getStatus() {
        return status;
    }

    public void setStatus(LoginStatus status) {
        this.status = status;
    }

    @Nullable
    public String getMsg() {
        return msg;
    }

    public void setMsg(@Nullable String msg) {
        this.msg = msg;
    }

    @Nullable
    public String getCookie() {
        return cookie;
    }

    public void setCookie(@Nullable String cookie) {
        this.cookie = cookie;
    }
}
