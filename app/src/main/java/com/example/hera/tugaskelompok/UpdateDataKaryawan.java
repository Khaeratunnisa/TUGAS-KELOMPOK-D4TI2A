package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataKaryawan extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText textIdKaryawan, textNamaKaryawan, textAlamatKaryawan, textTelpKaryawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_karyawan);

        dbHelper = new DBHelper(this);
        textIdKaryawan = (EditText) findViewById(R.id.editText_idkry);
        textNamaKaryawan = (EditText) findViewById(R.id.editText_nmkry);
        textAlamatKaryawan = (EditText) findViewById(R.id.editText_almkry);
        textTelpKaryawan = (EditText) findViewById(R.id.editText_tlpkry);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM karyawan WHERE nama_karyawan = '" +
                getIntent().getStringExtra("nama_karyawan") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            textIdKaryawan.setText(cursor.getString(0).toString());
            textNamaKaryawan.setText(cursor.getString(1).toString());
            textAlamatKaryawan.setText(cursor.getString(2).toString());
            textTelpKaryawan.setText(cursor.getString(3).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.button_update33);
        buttonKembali = (Button) findViewById(R.id.button_kembali33);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update karyawan set nama_karyawan='"+
                        textNamaKaryawan.getText().toString() +"', alamat_karyawan='" +
                        textAlamatKaryawan.getText().toString() +"', telp_karyawan='" +
                        textTelpKaryawan.getText().toString() + "' where id_karyawan='" +
                        textIdKaryawan.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
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
