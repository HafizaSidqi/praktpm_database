package com.fti.penjualan.view;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.fti.penjualan.entity.AppDataBase;
import com.fti.penjualan.entity.DataPenjualan;

//import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainPresenter implements MainContact.presenter{

    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Long, Long, Long> {
        private AppDataBase appDataBase;
        private DataPenjualan dataPenjualan;

        public InsertData(AppDataBase appDataBase, DataPenjualan dataPenjualan) {
            this.appDataBase = appDataBase;
            this.dataPenjualan = dataPenjualan;
        }

        @Override
        protected Long doInBackground(Long... longs) {
            return appDataBase.dao().insertData(dataPenjualan);
        }
        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }
    @Override
    public void insertData(String tanggal, int input, int output, AppDataBase dataBase) {
        final DataPenjualan dataPenjualan = new DataPenjualan();
        dataPenjualan.setTanggal(tanggal);
        dataPenjualan.setInput(input);
        dataPenjualan.setOutput(output);
        new InsertData(dataBase, dataPenjualan).execute(); //prosess input data
    }

    @Override
    public void readData(AppDataBase dataBase) {
        List<DataPenjualan> list;
        list = dataBase.dao().getData(); //mengakses AppDataBase lalu ke dao() yang menunjukkan DataPenjualanDAO lalu mengakses getData() yang ada pada kelas DataPenjualanDAO
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Long, Integer> {
        private AppDataBase appDataBase;
        private DataPenjualan dataPenjualan;

        public EditData(AppDataBase appDataBase, DataPenjualan dataPenjualan) {
            this.appDataBase = appDataBase;
            this.dataPenjualan = dataPenjualan;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDataBase.dao().updateData(dataPenjualan); //masuk ke query update data
        }
        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute : " +integer);
            view.successAdd();
        }
    }
    @Override
    public void editData(String tanggal, int input, int output, AppDataBase dataBase) {
        final DataPenjualan dataPenjualan = new DataPenjualan();
        dataPenjualan.setTanggal(tanggal);
        dataPenjualan.setInput(input);
        dataPenjualan.setOutput(output);
        new EditData(dataBase, dataPenjualan).execute(); //prosess update data
    }

    class DeleteData extends AsyncTask<Void, Long, Integer>{
        private AppDataBase appDataBase;
        private DataPenjualan dataPenjualan;

        public DeleteData(AppDataBase appDataBase, DataPenjualan dataPenjualan){
            this.appDataBase = appDataBase;
            this.dataPenjualan = dataPenjualan;
        }


        @Override
        protected Integer doInBackground(Void... voids) {
            appDataBase.dao().deleteData(dataPenjualan); //masuk ke query delete
            return null;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        protected void onPostExecute(Long along) {
            super.onPostExecute(Math.toIntExact(along));
            view.successDelete(); //sukses menghapus
        }
    }

    @Override
    public void deleteData(DataPenjualan dataPenjualan, AppDataBase database) {
        new DeleteData(database, dataPenjualan).execute(); //prosess delete data
    }




}
