package com.library.statistic;
import com.library.book.Book;
import com.library.history.History;
import com.library.user.client.Client;

import java.util.Date;

class Statistics extends History {
    public static void showChartBorrowingMonth(){

    }

    public static void showBucksInMonth(){
        
    }

    static class Borrowing{
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

        public void create(){

        }

        public void update(){

        }

        public void cancel(){

        }
    }
}
