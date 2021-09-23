package com.example.cookfrombook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class myUploadedrecipies extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView myrecyclerView;
    RecycleViewAdapter myAdapter;

    List<Recipes> recipes1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_uploadedrecipies);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(myUploadedrecipies.this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {

                    case R.id.home_menu:
                        Toast.makeText(myUploadedrecipies.this, "home is clicked", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(myUploadedrecipies.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.liked_recipe:
                        Toast.makeText(myUploadedrecipies.this, "liked recipies is clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.uploaded_recipies:
                        Intent j = new Intent(myUploadedrecipies.this, myUploadedrecipies.class);
                        startActivity(j);
                        finish();
                        return true;
                }

                return true;
            }
        });

        Intent j = getIntent();
        String r_name = j.getStringExtra("rname");
        String r_in = j.getStringExtra("ringridents");
        String Reci = j.getStringExtra("reci");



        recipes1 = new ArrayList<>();

        MainActivity mainActivity = new MainActivity();
        if(mainActivity.status1==true){
        recipes1.add(new Recipes(r_name, r_in, "Method", Reci, R.drawable.pasta1));}

        myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

        myAdapter = new RecycleViewAdapter(this, recipes1);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);

    }
}