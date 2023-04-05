package com.library.model;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Book {
    private String id;
    private String title;
    private String author;
    private String ph;
    private int yearPublic;
    private String genre;
    private int hasLeft;
    private String place;
    private double rate;
    private int borrowingPrice;
    private int routine;
    private String pathImage;

    public Book(String id, String bookname, String author, String ph, int yearPublic,
        String genre, int hasLeft, String place, int borrowingPrice, int routine, String pathImage){
        this.id = id;
        this.title = bookname;
        this.author = author;
        this.ph = ph;
        this.yearPublic = yearPublic;
        this.genre = genre;
        this.hasLeft = hasLeft;
        this.place = place;
        this.rate = 0;
        this.borrowingPrice = borrowingPrice;
        this.routine = routine;
        this.pathImage = pathImage;
    }

    //Getter methods
    public String getID(){ return this.id; }

    public String getTitle(){ return this.title; }

    public String getAuthor(){ return this.author; }

    public String getPublishingHouse() { return this.ph; }

    public int getYearPublic() { return this.yearPublic; }

    public String getGenre() { return this.genre; }

    public int getHasLeft() { return this.hasLeft; }

    public String getPlace() { return this.place; }

    public double getRating() { return this.rate; }

    public int getPrice() { return this.borrowingPrice; }

    public int getRoutine() { return this.routine; }

    public String getPathImage() { return this.pathImage; }
}
