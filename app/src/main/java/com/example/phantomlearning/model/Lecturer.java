package com.example.phantomlearning.model;

public class Lecturer {

    private String userName,subject,userEmail;

    public Lecturer(String userName, String subject, String userEmail) {
        this.userName = userName;
        this.subject = subject;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
