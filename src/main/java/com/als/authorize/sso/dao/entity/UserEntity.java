package com.als.authorize.sso.dao.entity;

import com.als.authorize.sso.dao.entity.mapped.BasicMapped;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "pas_user")
public class UserEntity extends BasicMapped {
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String token;
    private String lastIP;
    private Date lastLogin;
    private Date lastLogout;
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(Date lastLogout) {
        this.lastLogout = lastLogout;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
