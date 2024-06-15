package com.example.myappavia.data.DB.DAO;


import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Passenger.class, Ticket.class}, version = 9, autoMigrations = {
        @AutoMigration(from = 8, to = 9)
})
public abstract class MyDatabase extends RoomDatabase {

    public abstract PassengerDAO passengerDAO();
    public abstract TicketDao ticketDao();
//

}