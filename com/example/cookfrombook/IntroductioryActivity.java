package com.example.cookfrombook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class IntroductioryActivity extends AppCompatActivity {

    VideoView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductiory);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(15000);
                    Intent intent = new Intent(IntroductioryActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        

        lottieAnimationView = (VideoView)findViewById(R.id.animate);
        String vp = "android.resource://com.example.cookfrombook/"+R.raw.animation;
        Uri up = Uri.parse(vp);

        lottieAnimationView.setVideoURI(up);
        lottieAnimationView.start();

        thread.start();
    }
}