package com.library.model;

import java.util.*;
import javax.swing.*;

import javax.swing.JFrame;

public class User {
    protected String id;
    protected String fullname;
    protected String phoneNumber;
    protected String emailAddress;
    protected java.util.Date createdDate;
    protected String gender;

    private static User instance;

    public static User getInstance() { return instance; }

    public static void setInstance(User user) { instance = user; }

    User(String id, String fullname, String phoneNumber, String emailAddress, String gender, java.util.Date createdDate){
        this.id = id;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.createdDate = createdDate;
    }
    
    //Gettter
    public String getID() { return this.id; }

    public String getFullname() { return this.fullname; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public String getEmailAddress() { return this.emailAddress; }

    public Date getCreatedDate() { return this.createdDate; }

    public String getGender() { return this.gender; }

    public JFrame getDashBoard(){
        return null;
    }

}

