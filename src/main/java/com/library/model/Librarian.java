package com.library.model;

import java.util.*;
import javax.swing.*;

public class Librarian extends User{
    private String department;

    public Librarian(String id, String fullname, String phoneNumber, String emailAddress,
            String gender, Date createdDate, String department){
        super(id, fullname, phoneNumber, emailAddress, gender, createdDate);
        this.department = department;
    }

    @Override
    public JFrame getDashBoard(){
        return null;
    }

    public void getClientInfo(){

    }

    public void addBook(Book book){

    }

    public void updateBook(Book book){
    }
}