package com.example.cookfrombook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupPage extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText e1, e2, e3, e4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        mydb = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);
        e4 = (EditText)findViewById(R.id.editText4);
        b1 = (Button)findViewById(R.id.signup);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = e1.getText().toString();
                String EMail = e2.getText().toString();
                String Password = e3.getText().toString();
                String ConfrimPasssword = e4.getText().toString();

                if(FullName.equals("")||EMail.equals("")||Password.equals("")||ConfrimPasssword.equals(""))
                    Toast.makeText(SignupPage.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    if(Password.equals(ConfrimPasssword)){
                        boolean checkEmail = mydb.isMailExist(EMail);
                        if(checkEmail==false){
                            boolean insert = mydb.insertdata(FullName, EMail, Password);
                            if(insert == true){
                                Toast.makeText(SignupPage.this, "Great! Registered Successfully", Toast.LENGTH_SHORT).show();
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                                e3.setText("");
                                e4.setText("");
                            }else{
                                Toast.makeText(SignupPage.this, "Opps! Registration Failed", Toast.LENGTH_SHORT).show();
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                                e3.setText("");
                                e4.setText("");
                            }
                        }else{
                            Toast.makeText(SignupPage.this, "Account Exist already!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupPage.this, "Confirm Your Password Again!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}