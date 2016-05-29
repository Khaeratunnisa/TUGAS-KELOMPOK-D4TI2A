package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahBarang extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText textIdBrg, textNama, textJenis, textJumlah, textHarga, textIdSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);

        dbHelper = new DBHelper(this);
        textIdBrg = (EditText) findViewById(R.id.editText_idbrg);
        textNama = (EditText) findViewById(R.id.editText_nmbrg);
        textJenis = (EditText) findViewById(R.id.editText_jnsbrg);
        textJumlah = (EditText) findViewById(R.id.editText_jmlbrg);
        textHarga = (EditText) findViewById(R.id.editText_hrgbrg);
        textIdSupplier = (EditText) findViewById(R.id.editText_idspl);
        buttonSimpan = (Button) findViewById(R.id.button_simpan11);
        buttonKembali = (Button) findViewById(R.id.button_kembali11);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (textIdBrg.getText().toString().trim().length() == 0
                        || textNama.getText().toString().trim().length() == 0
                        || textJenis.getText().toString().trim().length() == 0
                        || textJumlah.getText().toString().trim().length() == 0
                        || textHarga.getText().toString().trim().length() == 0
                        || textIdSupplier.getText().toString().trim().length() == 0) {
                    Toast.makeText(TambahBarang.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else{
                    // TODO Auto-generated method stub
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into barang(id_barang, nama_barang, jenis_barang, jumlah_barang, harga_barang, id_supplier) values('" +
                            textIdBrg.getText().toString() + "','" +
                            textNama.getText().toString() + "','" +
                            textJenis.getText().toString() + "','" +
                            textJumlah.getText().toString() + "','" +
                            textHarga.getText().toString() + "','" +
                            textIdSupplier.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
                    DataBarang.dataBarang.RefreshList();
                    finish();
                }
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

