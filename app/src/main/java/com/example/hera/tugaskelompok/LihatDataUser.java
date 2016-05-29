package com.example.hera.tugaskelompok;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatDataUser extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView textUser,textPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_user);

        dbHelper = new DBHelper(this);
        textUser = (TextView) findViewById(R.id.textView_usr);
        textPass = (TextView) findViewById(R.id.textView_psw);
        buttonKembali = (Button) findViewById(R.id.button_back);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user WHERE username = '" +
                getIntent().getStringExtra("username") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            textUser.setText(cursor.getString(0).toString());
            textPass.setText(cursor.getString(1).toString());
        }

        buttonKembali = (Button) findViewById(R.id.button_back);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
