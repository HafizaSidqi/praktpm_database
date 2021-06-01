package com.fti.penjualan.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataPenjualan.class}, version =1, exportSchema = false) //ambil entitas dari kelas DataPenjualan
public abstract class AppDataBase extends RoomDatabase { //di-extends RoomDatabase
    public abstract DataPenjualanDAO dao();
    private static AppDataBase appDataBase;

    public static AppDataBase inidb(Context context) {
        if (appDataBase == null)
            appDataBase = Room.databaseBuilder(context, AppDataBase.class, "penjualan")
                    .allowMainThreadQueries().build(); //nama database : penjualan
        return appDataBase;
    }
}
