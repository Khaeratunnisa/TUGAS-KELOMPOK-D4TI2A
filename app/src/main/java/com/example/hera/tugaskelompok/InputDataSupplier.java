package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputDataSupplier extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText textIdSupplier, textNamaSupplier, textAlamatSupplier, textTelpSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_supplier);

        dbHelper = new DBHelper(this);
        textIdSupplier = (EditText) findViewById(R.id.editText_idspl);
        textNamaSupplier = (EditText) findViewById(R.id.editText_nmspl);
        textAlamatSupplier = (EditText) findViewById(R.id.editText_almspl);
        textTelpSupplier = (EditText) findViewById(R.id.editText_tlpspl);
        buttonSimpan = (Button) findViewById(R.id.button_simpan2);
        buttonKembali = (Button) findViewById(R.id.button_kembali2);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into supplier(id_supplier, nama_supplier, alamat_supplier, telp_supplier) values('" +
                        textIdSupplier.getText().toString() + "','" +
                        textNamaSupplier.getText().toString() + "','" +
                        textAlamatSupplier.getText().toString() + "','" +
                        textTelpSupplier.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
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
