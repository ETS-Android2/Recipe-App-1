package com.example.cookfrombook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UploadRecipePage extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button button;

    ImageView ri;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe_page);

        ed1 = (EditText)findViewById(R.id.text_recipe);
        ed2 = (EditText)findViewById(R.id.ingredients);
        ed3 = (EditText)findViewById(R.id.recipe);
        button = (Button)findViewById(R.id.upload_btn);
        ri = findViewById(R.id.recipe_img);
        fb = findViewById(R.id.floatingActionButton);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(UploadRecipePage.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String R_Name = ed1.getText().toString();
                String R_Ingri =  ed2.getText().toString();
                String Recipe1 = ed3.getText().toString();


                Intent i = new Intent(UploadRecipePage.this, MainActivity.class);
                i.putExtra("rname", R_Name);
                i.putExtra("ringridents", R_Ingri);
                i.putExtra("reci", Recipe1);

                startActivity(i);

                MainActivity mainActivity = new  MainActivity();
                mainActivity.status1 = true;
                finish();

                Intent j = new Intent(UploadRecipePage.this, myUploadedrecipies.class);
                j.putExtra("rname", R_Name);
                j.putExtra("ringridents", R_Ingri);
                j.putExtra("reci", Recipe1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        ri.setImageURI(uri);
    }
}