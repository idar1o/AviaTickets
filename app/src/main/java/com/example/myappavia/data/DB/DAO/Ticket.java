package com.example.myappavia.data.DB.DAO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets",
        foreignKeys = @ForeignKey(entity = Passenger.class,
                                  parentColumns = "PassID",
                                    childColumns = "PassID",
        onDelete = ForeignKey.CASCADE))
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "origin")
    private String origin;
    @ColumnInfo (name = "destination")
    private String destination;
    @ColumnInfo (name = "departure_at")
    private String departure_at;
    @ColumnInfo (name = "finish_time")
    private String finish_time;
    @ColumnInfo (name = "price")
    private String price;
    @ColumnInfo (name = "passengers_count")
    private int passengers_count;
    @ColumnInfo (index = true)
    private int PassID;
    @ColumnInfo (name = "transfers")
    private String transfers;
    @ColumnInfo (name = "return_ticket")
    private Boolean return_ticket;

    public Ticket( String origin, String destination, String departure_at, String finish_time, String price, int passengers_count, int passID, String transfers, Boolean return_ticket) {
        this.origin = origin;
        this.destination = destination;
        this.departure_at = departure_at;
        this.finish_time = finish_time;
        this.price = price;
        this.passengers_count = passengers_count;
        this.PassID = passID;
        this.transfers = transfers;
        this.return_ticket = return_ticket;
    }
    public Ticket() {
        this.origin = "";
        this.destination = "";
        this.departure_at = "";
        this.finish_time = "";
        this.price = "";
        this.passengers_count = 0;
        this.PassID = 0;
        this.transfers = "";
        this.return_ticket = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture_at() {
        return departure_at;
    }

    public void setDeparture_at(String departure_at) {
        this.departure_at = departure_at;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPassengers_count() {
        return passengers_count;
    }

    public void setPassengers_count(int passengers_count) {
        this.passengers_count = passengers_count;
    }

    public int getPassID() {
        return PassID;
    }

    public void setPassID(int passID) {
        PassID = passID;
    }

    public String getTransfers() {
        return transfers;
    }

    public void setTransfers(String transfers) {
        this.transfers = transfers;
    }

    public Boolean getReturn_ticket() {
        return return_ticket;
    }

    public void setReturn_ticket(Boolean return_ticket) {
        this.return_ticket = return_ticket;
    }
}
