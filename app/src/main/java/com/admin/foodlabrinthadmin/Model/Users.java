package com.admin.foodlabrinthadmin.Model;

public class Users {
    private String email;
    private String fullname;
    private String password;

    public Users(){

    }

    public Users(String email, String fullname, String password) {
        this.password = password;
        this.email = email;
        this.fullname = fullname;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
