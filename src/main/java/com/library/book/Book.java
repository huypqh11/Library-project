package com.library.book;

import java.util.*;
 public class Book {
    private String id;
    private String bookname;
    private String author;
    private String ph;
    private String yearPublic;
    private String type;
    private int hasLeft;
    private ArrayList<String> listPlace;
    private String rate;
    private ArrayList<String> comment;
    private int borrowingPrice;

     public Book(String id, String bookname, String author, String ph, String yearPublic,
                 String type, int hasLeft, ArrayList<String> listPlace, String rate,
                 ArrayList<String> comment, int borrowingPrice) {
         this.id = id;
         this.bookname = bookname;
         this.author = author;
         this.ph = ph;
         this.yearPublic = yearPublic;
         this.type = type;
         this.hasLeft = hasLeft;
         this.listPlace = listPlace;
         this.rate = rate;
         this.comment = comment;
         this.borrowingPrice = borrowingPrice;
     }
     public int getNumOfBook(){
        return this.hasLeft;
    }
}
