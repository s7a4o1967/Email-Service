package com.example.emailsender.Dto;

public class CredentialDto {

    private String empId;
    private String email;
    private String password;
    private String firstName;

    private String from="mamanuvvu@gmail.com";
    private String subject="Registration Success";
    private String text="Thanks for Registering";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmpId() {
        return empId;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
