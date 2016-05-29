package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatDataSupplier extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView tvIdSupplier, tvNamaSupplier, tvAlamatSupplier, tvTelpSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_supplier);

        dbHelper = new DBHelper(this);
        tvIdSupplier = (TextView) findViewById(R.id.textView_idspl);
        tvNamaSupplier = (TextView) findViewById(R.id.textView_nmspl);
        tvAlamatSupplier = (TextView) findViewById(R.id.textView_almspl);
        tvTelpSupplier = (TextView) findViewById(R.id.textView_tlpspl);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM supplier WHERE nama_supplier = '" +
                getIntent().getStringExtra("nama_supplier") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tvIdSupplier.setText(cursor.getString(0).toString());
            tvNamaSupplier.setText(cursor.getString(1).toString());
            tvAlamatSupplier.setText(cursor.getString(2).toString());
            tvTelpSupplier.setText(cursor.getString(3).toString());
        }
        
        buttonKembali = (Button) findViewById(R.id.button_kembali22);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
