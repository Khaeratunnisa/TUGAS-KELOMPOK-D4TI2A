package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatDataBarang extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView textIdBrg, textNama, textJenis, textJumlah, textHarga, textIdSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_barang);

        dbHelper = new DBHelper(this);
        textIdBrg = (TextView) findViewById(R.id.textView_idbrg);
        textNama = (TextView) findViewById(R.id.textView_nmbrg);
        textJenis = (TextView) findViewById(R.id.textView_jnsbrg);
        textJumlah = (TextView) findViewById(R.id.textView_jmlbrg);
        textHarga = (TextView) findViewById(R.id.textView_hrgbrg);
        textIdSupplier = (TextView) findViewById(R.id.textView_idspl);
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

        buttonKembali = (Button) findViewById(R.id.button_kembali11);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
