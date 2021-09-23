package com.example.cookfrombook;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class RecipeActivity extends AppCompatActivity {

    private TextView mRecipeName;
    private TextView mRecipeIngredients;
    private TextView mRecipeMethodTitle;
    private TextView mRecipe;
    private Button b1, b2;
    private TextToSpeech tp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mRecipeName = (TextView) findViewById(R.id.text_recipe);
        mRecipeIngredients = (TextView) findViewById(R.id.ingredients);
        mRecipeMethodTitle = (TextView) findViewById(R.id.method);
        mRecipe = (TextView) findViewById(R.id.recipe);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.Vidio);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Ingredients = intent.getExtras().getString("Ingredients");
        String MethodTitle = intent.getExtras().getString("MethodTitle");
        String Recipe = intent.getExtras().getString("Recipe");


        mRecipeName.setText(Title);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethodTitle.setText(MethodTitle);
        mRecipe.setText(Recipe);

        tp = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tp.setLanguage(Locale.UK);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String tospeak = Title + Ingredients + MethodTitle + Recipe;
                tp.setSpeechRate((float) 0.5);
                tp.speak(tospeak, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + Title + "+recipe"));
                startActivity(i);
            }
        });
    }

    public void onPause(){
        if(tp !=null){
            tp.stop();
            tp.shutdown();
        }
        super.onPause();
    }
}

