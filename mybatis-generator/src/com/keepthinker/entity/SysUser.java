package com.keepthinker.entity;

public class SysUser {
    private Long id;

    private String username;

    private String password;

    private Boolean status;

    private Boolean passwordNonExpired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getPasswordNonExpired() {
        return passwordNonExpired;
    }

    public void setPasswordNonExpired(Boolean passwordNonExpired) {
        this.passwordNonExpired = passwordNonExpired;
    }
}