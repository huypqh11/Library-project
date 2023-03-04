package com.library.history;
import com.library.borrowing.Borrowing;
import com.library.user.client.Client;

import java.util.*;

public class History {
    private static ArrayList<Borrowing> listOfBorrowings;
    
    public static void seeHistory(Client client){
        
    }

    public static void saveHistory(Borrowing borrow){
        listOfBorrowings.add(borrow);
    }
}
