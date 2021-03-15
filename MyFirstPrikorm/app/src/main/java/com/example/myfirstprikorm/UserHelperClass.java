package com.example.myfirstprikorm;

public class UserHelperClass {
    String name, childname, email, phoneno, password;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String childname, String email, String phoneno, String password) {
        this.name = name;
        this.childname = childname;
        this.email = email;
        this.phoneno = phoneno;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getChildname() {
        return childname;
    }

    public String getEmail() {return email;}

    public String getphoneno() {return phoneno;}

    public String getPassword() {
        return password;
    }
}
