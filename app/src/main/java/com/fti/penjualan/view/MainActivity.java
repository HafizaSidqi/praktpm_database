package com.fti.penjualan.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fti.penjualan.BuildConfig;
import com.fti.penjualan.R;
import com.fti.penjualan.entity.AppDataBase;
import com.fti.penjualan.entity.DataPenjualan;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{

    private AppDataBase appDataBase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnsubmit;
    private RecyclerView recyclerView;
    private EditText etTanggal, etInput, etOutput, etInputBersih;

    private int id = 0; //id untuk autogenerate, dihitung dari 0
    boolean edit = false; // untuk on/off fitur edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnsubmit = findViewById(R.id.btn_submit);
        etTanggal = findViewById(R.id.et_tgl);
        etInput = findViewById(R.id.et_input);
        etOutput = findViewById(R.id.et_output);
        recyclerView = findViewById(R.id.rcv_jual);

        //koneksi ke database
        appDataBase = AppDataBase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDataBase);

    }

    @Override
    public void successAdd() {

    }

    @Override
    public void successDelete() {  //implementasi jika sukses menghapus
        Toast.makeText(this, "Berhasil  Menghapus Data", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDataBase);
    }

    @Override
    public void resetForm() {  //implementasi method resetForm
        etTanggal.setText("");
        etInput.setText("");
        etOutput.setText("");
        btnsubmit.setText("Submit");

    }

    @Override
    public void getData(List<DataPenjualan> list) { //implementasi method getData
        mainAdapter = new MainAdapter(this, list, this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataPenjualan item) { //implementasi method editData
        etTanggal.setText(item.getTanggal());
        etInput.setText(item.getInput());
        etOutput.setText(item.getOutput());
        id = item.getId();
        edit = true;        //fitur edit dinyalakan
        btnsubmit.setText("EDIT DATA");     //teks yang ada di btnsubmit akan menjadi "edit data"
    }

    @Override
    public void deleteData(DataPenjualan item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda Yakin Ingin Menghapus Data Ini?")
                .setPositiveButton(android.R.string.yes, new
                        DialogInterface.OnClickListener() { //kalai diklik yes
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resetForm();
                                mainPresenter.deleteData(item, appDataBase);        //proses hapus data
                            }
                        })
                .setNegativeButton(android.R.string.no, new
                        DialogInterface.OnClickListener() {     //kalau di klik no
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view == btnsubmit) {
            if (etTanggal.getText().toString().equals("")||etInput.getText().toString().equals("")
            ||etOutput.getText().toString().equals("")){
                Toast.makeText(this, "Harap Isi Semua Data!", Toast.LENGTH_SHORT).show();
            } else {
                if (!edit) {        //kalau fitur edit off, dia masuk ke proses insertData
                    mainPresenter.insertData(etTanggal.getText().toString(), Integer.parseInt(etInput.getText().toString()),
                            Integer.parseInt(etOutput.getText().toString()), appDataBase);
                }else{              //kalau fitur edit on, dia masuk ke proses editData
                    mainPresenter.editData(etTanggal.getText().toString(), Integer.parseInt(etInput.getText().toString()),
                            Integer.parseInt(etOutput.getText().toString()), appDataBase);
                    edit = false;   //kalau proses edit udah selesai, fitur edit dimatikan
                }
                resetForm();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}