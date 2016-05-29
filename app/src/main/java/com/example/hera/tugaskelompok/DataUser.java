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

public class DataUser extends Activity {
    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    DBHelper dbHelper;
    public static DataUser dataUser;

    @Override
    //onCreate merupakan activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        Button buttonTambahUser = (Button) findViewById(R.id.button_tbusr);


        buttonTambahUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(DataUser.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        dataUser = this;
        dbHelper = new DBHelper(this);
        RefreshList();
    }

    //method Refreslish untuk menampilkan kembali data terbaru
    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        listView = (ListView)findViewById(R.id.listView4);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Data User", "Hapus Data User"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataUser.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) { //dalam bentuk dialog
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), LihatDataUser.class);
                                i.putExtra("username", selection);
                                startActivity(i);
                                break;
                            case 1:
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from user where username= '" + selection + "'");
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
