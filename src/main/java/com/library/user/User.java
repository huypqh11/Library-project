package com.library.user;

import com.library.book.Book;

import java.util.*;

public class User {
    protected String id;
    protected String fullname;
    protected String phoneNumber;
    protected String emailAddress;
    protected Date timeCreate;
    protected String gender;

    protected User(String id, String fullname, String phoneNumber, String emailAddress, Date timeCreate){
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



