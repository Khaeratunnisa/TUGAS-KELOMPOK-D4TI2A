package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataBarang extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText textIdBrg, textNama, textJenis, textJumlah, textHarga, textIdSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_barang);

        dbHelper = new DBHelper(this);
        textIdBrg = (EditText) findViewById(R.id.editText_idbrg);
        textNama = (EditText) findViewById(R.id.editText_nmbrg);
        textJenis = (EditText) findViewById(R.id.editText_jnsbrg);
        textJumlah = (EditText) findViewById(R.id.editText_jmlbrg);
        textHarga = (EditText) findViewById(R.id.editText_hrgbrg);
        textIdSupplier = (EditText) findViewById(R.id.editText_idspl);
        buttonUpdate = (Button) findViewById(R.id.button_update11);
        buttonKembali = (Button) findViewById(R.id.button_kembali11);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE nama_barang = '" +
                getIntent().getStringExtra("nama_barang") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            textIdBrg.setText(cursor.getString(0).toString());
            textNama.setText(cursor.getString(1).toString());
            textJenis.setText(cursor.getString(2).toString());
            textJumlah.setText(cursor.getString(3).toString());
            textHarga.setText(cursor.getString(4).toString());
            textIdSupplier.setText(cursor.getString(5).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.button_update11);
        buttonKembali = (Button) findViewById(R.id.button_kembali11);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update barang set nama_barang='"+
                        textNama.getText().toString() +"', jenis_barang='" +
                        textJenis.getText().toString() +"', jumlah_barang='" +
                        textJumlah.getText().toString() +"', harga_barang='" +
                        textHarga.getText().toString() +"', id_supplier='" +
                        textIdSupplier.getText().toString() + "' where id_barang='" +
                        textIdBrg.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                DataBarang.dataBarang.RefreshList();
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

