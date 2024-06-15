package com.example.myappavia.data.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.ArrayList;


@Dao
public interface PassengerDAO {
    @Insert
    void insertPassenger(Passenger passenger);
//-
//    @Query("SELECT * FROM passengers ORDER BY id DESC")
//    public ArrayList<Passenger> getAllPassengers ();


    @Delete
    void deletePassenger(Passenger passenger);
}
