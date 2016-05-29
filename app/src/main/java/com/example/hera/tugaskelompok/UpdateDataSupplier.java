package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataSupplier extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText textIdSupplier, textNamaSupplier, textAlamatSupplier, textTelpSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_supplier);

        dbHelper = new DBHelper(this);
        textIdSupplier = (EditText) findViewById(R.id.editText_idspl);
        textNamaSupplier = (EditText) findViewById(R.id.editText_nmspl);
        textAlamatSupplier = (EditText) findViewById(R.id.editText_almspl);
        textTelpSupplier = (EditText) findViewById(R.id.editText_tlpspl);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM supplier WHERE nama_supplier = '" +
                getIntent().getStringExtra("nama_supplier") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            textIdSupplier.setText(cursor.getString(0).toString());
            textNamaSupplier.setText(cursor.getString(1).toString());
            textAlamatSupplier.setText(cursor.getString(2).toString());
            textTelpSupplier.setText(cursor.getString(3).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.button_update22);
        buttonKembali = (Button) findViewById(R.id.button_kembali22);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update supplier set nama_supplier='"+
                        textNamaSupplier.getText().toString() +"', alamat_supplier='" +
                        textAlamatSupplier.getText().toString() +"', telp_supplier='" +
                        textTelpSupplier.getText().toString() + "' where id_supplier='" +
                        textIdSupplier.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                DataSupplier.dataSupplier.RefreshList();
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
