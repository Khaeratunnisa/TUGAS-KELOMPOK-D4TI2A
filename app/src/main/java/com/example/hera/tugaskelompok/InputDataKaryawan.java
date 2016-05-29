package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputDataKaryawan extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText textIdKaryawan, textNamaKaryawan, textAlamatKaryawan, textTelpKaryawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_karyawan);

        dbHelper = new DBHelper(this);
        textIdKaryawan = (EditText) findViewById(R.id.editText_idkry);
        textNamaKaryawan = (EditText) findViewById(R.id.editText_nmkry);
        textAlamatKaryawan = (EditText) findViewById(R.id.editText_almkry);
        textTelpKaryawan = (EditText) findViewById(R.id.editText_tlpkry);
        buttonSimpan = (Button) findViewById(R.id.button_simpan3);
        buttonKembali = (Button) findViewById(R.id.button_kembali3);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into karyawan(id_karyawan, nama_karyawan, alamat_karyawan, telp_karyawan) values('" +
                        textIdKaryawan.getText().toString() + "','" +
                        textNamaKaryawan.getText().toString() + "','" +
                        textAlamatKaryawan.getText().toString() + "','" +
                        textTelpKaryawan.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
                DataKaryawan.dataKaryawan.RefreshList();
                finish();
            }
        });

        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
