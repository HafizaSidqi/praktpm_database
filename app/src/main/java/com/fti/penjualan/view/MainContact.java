package com.fti.penjualan.view;

import android.view.View;


import com.fti.penjualan.entity.AppDataBase;
import com.fti.penjualan.entity.DataPenjualan;

import java.util.List;

public interface MainContact {
    //untuk kodingan activity
    interface view extends View
            .OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataPenjualan> list);
        void editData(DataPenjualan item);
        void deleteData(DataPenjualan item);
    }
    //kodingan untuk database
    interface presenter{
        void insertData(String tanggal, int pemasukan_kotor, int pengeluaran, AppDataBase dataBase);
        void readData(AppDataBase dataBase);
        void editData(String tanggal, int pemasukan_kotor, int pengeluaran, AppDataBase dataBase);
        void deleteData(DataPenjualan dataPenjualan, AppDataBase database);
    }


}
