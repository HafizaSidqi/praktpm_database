package com.fti.penjualan.entity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Date;

@Entity(tableName= "penjualan_db") //memberi nama table
public class DataPenjualan {
    @NonNull
    @PrimaryKey(autoGenerate = true)

    private int id;


    @ColumnInfo(name = "tanggal")
    private String tanggal;
    @ColumnInfo(name = "input")
    private int input;
    @ColumnInfo(name = "output")
    private int output;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }




}
