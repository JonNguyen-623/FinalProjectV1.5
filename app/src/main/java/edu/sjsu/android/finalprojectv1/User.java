package edu.sjsu.android.finalprojectv1;

public class User {
    private String fullname;
    private String email;
    private String mobile;
    private String password;

    public User(String fullname, String email, String mobile, String password) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}


