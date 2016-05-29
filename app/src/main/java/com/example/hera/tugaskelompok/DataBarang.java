package com.example.hera.tugaskelompok;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DataBarang extends Activity {
    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    DBHelper dbHelper;
    public static DataBarang dataBarang;

    @Override
    //onCreate merupakan activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_barang);
        
        Button buttonSupplier = (Button) findViewById(R.id.button_spl1);
        Button buttonKaryawan = (Button) findViewById(R.id.button_kry1);
        Button buttonTambah = (Button) findViewById(R.id.button_tbbrg);

        buttonSupplier.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in = new Intent(DataBarang.this, DataSupplier.class);
                startActivity(in);
            }
        });

        buttonTambah.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(DataBarang.this, TambahBarang.class);
                startActivity(i);
            }
        });
        buttonKaryawan.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent ik = new Intent(DataBarang.this, DataKaryawan.class);
                startActivity(ik);
            }
        });

        dataBarang = this;
        dbHelper = new DBHelper(this);
        RefreshList();
    }

    //method Refreslish untuk menampilkan kembali data terbaru
    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Data Barang", "Update Data Barang", "Hapus Data Barang"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataBarang.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) { //dalam bentuk dialog
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), LihatDataBarang.class);
                                i.putExtra("nama_barang", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateDataBarang.class);
                                in.putExtra("nama_barang", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from barang where nama_barang = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}
