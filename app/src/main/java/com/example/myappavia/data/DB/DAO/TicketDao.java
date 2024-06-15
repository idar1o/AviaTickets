package com.example.myappavia.data.DB.DAO;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Dao;

import java.util.ArrayList;

@Dao
public interface TicketDao {
    @Insert
    void insertTicket(Ticket ticket);
//    @Query("SELECT * FROM tickets ORDER BY id DESC")
//    ArrayList<Ticket> getAllTicketPasses ();
}
