package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatDataKaryawan extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView tvIdKaryawan, tvNamaKaryawan, tvAlamatKaryawan, tvTelpKaryawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_karyawan);

        dbHelper = new DBHelper(this);
        tvIdKaryawan = (TextView) findViewById(R.id.textView_idkry);
        tvNamaKaryawan = (TextView) findViewById(R.id.textView_nmkry);
        tvAlamatKaryawan = (TextView) findViewById(R.id.textView_almkry);
        tvTelpKaryawan = (TextView) findViewById(R.id.textView_tlpkry);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM karyawan WHERE nama_karyawan = '" +
                getIntent().getStringExtra("nama_karyawan") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tvIdKaryawan.setText(cursor.getString(0).toString());
            tvNamaKaryawan.setText(cursor.getString(1).toString());
            tvAlamatKaryawan.setText(cursor.getString(2).toString());
            tvTelpKaryawan.setText(cursor.getString(3).toString());
        }

        buttonKembali = (Button) findViewById(R.id.button_kembali33);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
