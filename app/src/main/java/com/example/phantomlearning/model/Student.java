package com.example.phantomlearning.model;

public class Student {

   private String userName,registrationNumber,role;

    public Student(String userName, String registrationNumber, String role) {
        this.userName = userName;
        this.registrationNumber = registrationNumber;
        this.role = role;
    }
    public Student(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
