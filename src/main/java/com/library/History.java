package com.library;
import com.library.*;
import java.util.*;

public class History {
    private static ArrayList<Borrowing> listOfBorrowings;
    
    public static void seeHistory(Client client){
        
    }

    public static void saveHistory(Borrowing borrow){
        listOfBorrowings.add(borrow);
    }
}
