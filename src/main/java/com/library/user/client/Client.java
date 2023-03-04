package com.library.user.client;

import com.library.book.Book;
import com.library.user.User;

import java.util.ArrayList;
import java.util.Date;

public class Client extends User {
    private String address;
    private String bankName;
    private ArrayList<Book> listOfAttentedBooks;

    Client(String id, String fullname, String phoneNumber, String emailAddress,
           Date timeCreate, String address, String bankAccount, String bankName) {
        super(id, fullname, phoneNumber, emailAddress, timeCreate);
        this.address = address;
        this.bankName = bankName;
    }

    public void getFormUI() {

    }

    public void rateBook(Book book) {

    }

    public void commentBook(Book book) {

    }

    public void attentBook(Book book) {

    }
}
