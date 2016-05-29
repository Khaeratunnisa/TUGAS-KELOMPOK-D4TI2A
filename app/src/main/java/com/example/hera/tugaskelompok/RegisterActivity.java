package com.example.hera.tugaskelompok;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText textUser, textPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);
        textUser = (EditText) findViewById(R.id.editText_usr);
        textPass = (EditText) findViewById(R.id.editText_psw);
        buttonSimpan = (Button) findViewById(R.id.button_signup);
        buttonKembali = (Button) findViewById(R.id.button_back);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String User = textUser.getText().toString();
                String Pass = textPass.getText().toString();
                if (textUser.getText().toString().trim().length() == 0
                        || textPass.getText().toString().trim().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    // TODO Auto-generated method stub
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into user(username, password) values('" +
                            textUser.getText().toString() + "','" +
                            textPass.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
                    dbHelper.SimpanUser(User, Pass);
                    DataUser.dataUser.RefreshList();
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

