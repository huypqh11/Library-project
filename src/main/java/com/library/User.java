package com.library;

import java.util.*;

class User {
    protected String id;
    protected String fullname;
    protected String phoneNumber;
    protected String emailAddress;
    protected Date timeCreate;

    User(String id, String fullname, String phoneNumber, String emailAddress, Date timeCreate){
        this.id = id;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.timeCreate = timeCreate;
    }
    
    void verify(){

    }

    void checkAccount(){

    }


}

class Librarian extends User{
    private String department;

    Librarian(String id, String fullname, String phoneNumber, String emailAddress,
            Date timeCreate, String department){
        super(id, fullname, phoneNumber, emailAddress, timeCreate);
        this.department = department;
    }

    public void getFormUI(){

    }

    public void getClientInfo(){

    }

    public void addBook(Book book){

    }

    public void updateBook(Book book){
    }
}

class Client extends User{
    private String address;
    private String bankName;
    private ArrayList<Book> listOfAttentedBooks;

    Client(String id, String fullname, String phoneNumber, String emailAddress,
        Date timeCreate, String address, String bankAccount, String bankName){
        super(id, fullname, phoneNumber, emailAddress, timeCreate);
        this.address = address;
        this.bankName = bankName;
    }

    public void getFormUI(){

    }

    public void rateBook(Book book){

    }

    public void commentBook(Book book){

    }

    public void attentBook(Book book){

    }
}