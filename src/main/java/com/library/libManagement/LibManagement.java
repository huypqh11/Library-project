package com.library.libManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class LibManagement{
    public static final String database_name = "LIBMANAGEMENT";
    private String username;
    private String password;
    private int userType;

    public void login(){

    }

    public void logout(){

    }

    public void register(){
        
    }

    public void addLibrarian(){

    }

    public static Connection connect()
    {
        try {
            String user = "root";
            String pw = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Loaded driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=" + user + "&password= " + pw);
            //System.out.println("Connected to MySQL");
            return con;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createDB(){
        try{
            Connection connection = connect();
            ResultSet resultSet = connection.getMetaData().getCatalogs();

            while(resultSet.next()){
                String dbname = resultSet.getString(1);
                
                if (dbname == database_name){
                    resultSet.close();
                    return;
                }
                
            }

            Statement stmt = connection.createStatement();

            //Create database
            stmt.executeUpdate("CREATE DATABASE " + database_name);
            //Use database
            stmt.executeUpdate("USE " + database_name);

            //Set commands
            String strCreateTableUser = "CREATE TABLE USER(" +
                                    "Username VARCHAR(255) NOT NULL, " + 
                                    "Password VARCHAR(255) NOT NULL, " +
                                    "TypeUser int NOT NULL DEFAULT 1, " +
                                    "PRIMARY KEY (Username));";
            stmt.executeUpdate(strCreateTableUser);

            String strCreateTableLibrarian = "CREATE TABLE LIBRARIAN(" + 
                                    "LID VARCHAR(255) NOT NULL, " + 
                                    "Fullname NVARCHAR(50), " +
                                    "PhoneNum VARCHAR(10), " + 
                                    "Email VARCHAR(255), " +
                                    "Gender NVARCHAR(10) CHECK (Gender = 'Male' OR Gender = 'Female' OR Gender = 'Other'), " +
                                    "Department VARCHAR(255), " +
                                    "CreatedDate DATE DEFAULT GETDATE(), " +
                                    "Username VARCHAR(255), " +
                                    "PRIMARY KEY (LID));";
            stmt.executeUpdate(strCreateTableLibrarian);

            String strCreateTableClient = "CREATE TABLE CLIENT(" + 
                                    "CID VARCHAR(255) NOT NULL, " +
                                    "Fullname NVARCHAR(50), " +
                                    "PhoneNum VARCHAR(10), " +
                                    "Email VARCHAR(255), " + 
                                    "Address NVARCHAR(100), " +
                                    "BankAccount VARCHAR(100), " +
                                    "BankName VARCHAR(100)" +
                                    "CreatedDate DATE DEFAULT GETDATE(), " +
                                    "Username VARCHAR(255), " +
                                    "PRIMARY KEY (CID));";
            stmt.executeUpdate(strCreateTableClient);

            String strCreateTableBook = "CREATE TABLE BOOK(" +
                                    "BID VARCHAR(255) NOT NULL, " + 
                                    "Tittle NVARCHAR(50), " +
                                    "Author NVARCHAR(50), " +
                                    "PublishingHouse NVARCHAR(50), " +
                                    "Year DATE, " +
                                    "Genre VARCHAR(50), " +
                                    "HasLeft int DEFAULT 0, " +
                                    "Place VARCHAR(50), " +
                                    "PRIMARY KEY (BID));";
            stmt.executeUpdate(strCreateTableBook);
            
            String strCreateTableReview = "CREATE TABLE REVIEW(" +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "CID VARCHAR(255) NOT NULL, " +
                                    "Rate int DEFAULT 0, " +
                                    "Comment NVARCHAR(255), " +
                                    "ReviewedDate DATETIME DEFAULT GETDATE(), " +
                                    "PRIMARY KEY (BID, CID));";
            stmt.executeUpdate(strCreateTableReview);

            String strCreateTableInteresting = "CREATE TABLE INTERESTING(" +
                                    "CID VARCHAR(255) NOT NULL, " +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "PRIMARY KEY (CID, BID));";
            stmt.executeUpdate(strCreateTableInteresting);
                                    
            String strCreateTableBorrowing = "CREATE TABLE BORROWING(" +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "Price int NOT NULL DEFAULT 25000, " +
                                    "Routine int NOT NULL DEFAULT 7, " +
                                    "PRIMARY KEY (BID));";
            stmt.executeUpdate(strCreateTableBorrowing);
                                
            String strCreateTableTransaction = "CREATE TABLE TRANSACTION(" +
                                    "TID VARCHAR(255) NOT NULL, " +
                                    "CID VARCHAR(255) NOT NULL, " +
                                    "BorrowedDate DATE DEFAULT GETDATE(), " +
                                    "ReturnDate DATE, " + 
                                    "TotalPrice int, " +
                                    "Quantity int, " +
                                    "PRIMARY KEY (TID));";
            stmt.executeUpdate(strCreateTableTransaction);

            String strCreateTableDetailTrans = "CREATE TABLE DETAILTRANS(" +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "Price int NOT NULL, " +
                                    "Routine int NOT NULL, " +
                                    "TID VARCHAR(255) NOT NULL, " +
                                    "PRIMARY KEY (BID));";
            stmt.executeUpdate(strCreateTableDetailTrans);

            String strFK_Librarian_User = "ALTER TABLE LIBRARIAN " + 
                                    "ADD CONSTRAINT FK_LIBRARIAN_USER " +
                                    "FOREIGN KEY (Username) REFERENCES USER(Username)";
            stmt.executeUpdate(strFK_Librarian_User);

            String strFK_Client_User = "ALTER TABLE CLIENT " +
                                    "ADD CONSTRAINT FK_CLIENT_USER " +
                                    "FOREIGN KEY (Username) REFERENCES USER(Username)";
            stmt.executeUpdate(strFK_Client_User);

            String strFK_Review_Book = "ALTER TABLE REVIEW " +
                                    "ADD CONSTRAINT FK_REVIEW_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BOOK(BID)";
            stmt.executeUpdate(strFK_Review_Book);

            String strFK_Review_Client = "ALTER TABLE REVIEW " +
                                    "ADD CONSTRAINT FK_REVIEW_CLIENT " +
                                    "FOREIGN KEY (CID) REFERENCES CLIENT(CID)";
            stmt.executeUpdate(strFK_Review_Client);

            String strFK_Interesting_Client = "ALTER TABLE INTERESTING " +
                                    "ADD CONSTRAINT FK_INTERESTING_CLIENT " +
                                    "FOREIGN KEY (CID) REFERENCES CLIENT(CID)";
            stmt.executeUpdate(strFK_Interesting_Client);

            String strFK_Interesting_Book = "ALTER TABLE INTERESTING " +
                                    "ADD CONSTRAINT FK_INTERESTING_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BOOK(BID)";
            stmt.executeUpdate(strFK_Interesting_Book);

            String strFK_Borrowing_Book = "ALTER TABLE BORROWING " +
                                    "ADD CONSTRAINT FK_BORROWING_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BOOK(BID)";
            stmt.executeUpdate(strFK_Borrowing_Book);

            String strFK_DetailTrans_Book = "ALTER TABLE DETAILTRANS " +
                                    "ADD CONSTRAINT FK_DETAILTRANS_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BOOK(BID)";
            stmt.executeUpdate(strFK_DetailTrans_Book);

            String strFK_DetailTrans_Transaction = "ALTER TABLE DETAILTRANS " +
                                    "ADD CONSTRAINT FK_DETAILTRANS_TRANSACTION " +
                                    "FOREIGN KEY (TID) REFERENCES TRANSACTION(TID)";
            stmt.executeUpdate(strFK_DetailTrans_Transaction);

            resultSet.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}