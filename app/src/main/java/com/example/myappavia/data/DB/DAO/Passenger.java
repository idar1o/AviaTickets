package com.example.myappavia.data.DB.DAO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "passengers")
public class Passenger {

    @PrimaryKey(autoGenerate = true)
    private int PassID;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "Lastname")
    private String Lastname;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "sex")
    private String sex;
    @ColumnInfo(name = "docs")
    private String docs;
    @ColumnInfo(name = "number_docs")
    private String number_docs;

    public Passenger(String name, String lastname, String number, String email, String sex, String docs, String number_docs) {
        this.name = name;
        this.Lastname = lastname;
        this.number = number;
        this.email = email;
        this.sex = sex;
        this.docs = docs;
        this.number_docs = number_docs;
    }
    public Passenger(){
        this.name = "";
        this.Lastname = "";
        this.number = "";
        this.email = "";
        this.sex = "";
        this.docs = "";
        this.number_docs = "";

    }
    public int getPassID() {
        return PassID;
    }

    public void setPassID(int id) {
        this.PassID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassengerName() {
        return name;
    }

    public void setPassengerName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public String getNumber_docs() {
        return number_docs;
    }

    public void setNumber_docs(String number_docs) {
        this.number_docs = number_docs;
    }
}
