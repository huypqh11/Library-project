package com.library.user.librarian;

import com.library.book.Book;
import com.library.user.User;

import java.util.Date;

public class Librarian extends User {
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