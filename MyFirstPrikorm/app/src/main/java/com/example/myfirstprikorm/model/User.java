package com.example.myfirstprikorm.model;

public class User {
    private Integer id;
    private String username;
    private String childName;
    private String password;
    private String email;
    private String phoneno;

    public User(String username, String childName, String password, String email, String phoneno) {
        this.username = username;
        this.childName = childName;
        this.password = password;
        this.email = email;
        this.phoneno = phoneno;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getChildName() {
        return childName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneno() {
        return phoneno;
    }
}
