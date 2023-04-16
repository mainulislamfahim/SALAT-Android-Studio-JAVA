package com.example.salat;

public class UserHelperClass {
    String name ,username, mail, phone, pass ,feedback;
    int level;

    public UserHelperClass() {

    }
    public UserHelperClass( String name,String username, String mail, String phone, String pass) {
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.phone = phone;
        this.pass = pass;
    }

    public UserHelperClass(String name, String feedback, int level) {
        this.name = name;
        this.feedback = feedback;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
