package com.example.cookfrombook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity {

    EditText e1, e2, e3;
    Button b1, b2;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        myDB = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.user_id);
        e2 = (EditText)findViewById(R.id.editText31);
        e3 = (EditText)findViewById(R.id.editText41);
        b1 = (Button)findViewById(R.id.next);
        b2 = (Button)findViewById(R.id.rst_btn);

        LinearLayout ly1 = (LinearLayout)findViewById(R.id.linearly1);
        LinearLayout ly2 = (LinearLayout)findViewById(R.id.linearly2);
        ly1.setVisibility(View.VISIBLE);
        ly2.setVisibility(View.INVISIBLE);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = e1.getText().toString();

                if(username == ""){
                    Toast.makeText(com.example.cookfrombook.PasswordActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkUser = myDB.isMailExist(username);
                    if(checkUser == true){
                        ly1.setVisibility(View.GONE);
                        ly2.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toast.makeText(PasswordActivity.this, "User does not exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = e1.getText().toString();
                String pass = e2.getText().toString();
                String confirmpass = e3.getText().toString();

                if(pass == ""){
                    Toast.makeText(PasswordActivity.this, "enter password", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(confirmpass)){
                        boolean resetpass = myDB.updatedata(username, pass);
                        if(resetpass == true){
                            finish();
                            Toast.makeText(PasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            e2.setText("");
                            e3.setText("");
                            Toast.makeText(PasswordActivity.this, "Password not changed , Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(PasswordActivity.this, "Check ConfirmPassword!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}