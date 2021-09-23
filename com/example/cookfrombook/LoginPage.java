package com.example.cookfrombook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText eu,ep;
    Button bl;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        eu = (EditText)findViewById(R.id.username_id);
        ep = (EditText)findViewById(R.id.password_id);
        bl = (Button)findViewById(R.id.login_btn_id);
        mydb = new DatabaseHelper(this);
        tv = (TextView) findViewById(R.id.fpass);


        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = eu.getText().toString();
                String Password = ep.getText().toString();

                if(UserName.equals("")||Password.equals(""))
                    Toast.makeText(LoginPage.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();

                else{
                    boolean checkUserPass = mydb.checkusernamepass(UserName, Password);
                    if(checkUserPass==true) {
                        Toast.makeText(LoginPage.this, "Great! Welcome", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginPage.this, MainActivity.class);
                        startActivity(i);
                        MainActivity mainActivity = new  MainActivity();
                        mainActivity.status = true;
                        finish();

                    }else{
                        Toast.makeText(LoginPage.this, "Invalid Credential!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PasswordActivity.class);
                startActivity(i);
            }
        });
    }
}