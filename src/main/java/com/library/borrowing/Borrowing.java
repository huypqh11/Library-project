package com.library.borrowing;

import com.library.user.client.Client;
import com.library.book.Book;

import java.util.*;

public class Borrowing{
    private String id;
    private Book book;
    private Client borrower;
    private Date dateBorrowed;
    private Date dateGiveBack;
    private int price;

    Borrowing(String id, Book book, Client borrower,
            Date dateBorrowed, Date dateGiveBack, int price){
        this.id = id;
        this.book = book;
        this.borrower = borrower;
        this.dateBorrowed = dateBorrowed;
        this.dateGiveBack = dateGiveBack;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getBorrower() {
        return borrower;
    }

    public void setBorrower(Client borrower) {
        this.borrower = borrower;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Date getDateGiveBack() {
        return dateGiveBack;
    }

    public void setDateGiveBack(Date dateGiveBack) {
        this.dateGiveBack = dateGiveBack;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void create(){

    }

    public void update(){

    }

    public void cancel(){

    }
}