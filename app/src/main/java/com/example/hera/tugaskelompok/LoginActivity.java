package com.example.hera.tugaskelompok;

/**
 * Created by Hera on 5/29/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    DBHelper dbHelper;
    Button buttonLogin, buttonExit;
    EditText textUser, textPasswd;
    String Username = "hera", Password = "syarah";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        textUser = (EditText) findViewById(R.id.editText);
        textPasswd = (EditText) findViewById(R.id.editText2);
        buttonLogin = (Button) findViewById(R.id.button);
        buttonExit = (Button) findViewById(R.id.button2);

        Button buttonRegister = (Button) findViewById(R.id.button_rgs);

        buttonRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String User = textUser.getText().toString();
                String Pass = textPasswd.getText().toString();
                if(User.equals("") || Pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Username atau Password belum diisi", Toast.LENGTH_SHORT).show();
                }else{
                    if(!User.equals(Username) || !Pass.equals(Password)){
                        Toast.makeText(LoginActivity.this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Berhasil Login!", Toast.LENGTH_SHORT).show();
                        dbHelper.SimpanUser(User, Pass);
                        finish();
                        Intent im = new Intent(LoginActivity.this, DataBarang.class);
                        startActivity(im);

                    }
                }
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                moveTaskToBack(true);
            }
        });
    }
}
