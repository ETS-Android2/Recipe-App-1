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
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Like_activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView myrecyclerView;
    RecycleViewAdapter myAdapter;

    List<Recipes> recipes1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_activity);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Like_activity.this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {

                    case R.id.home_menu:
                        Toast.makeText(Like_activity.this, "home is clicked", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Like_activity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.liked_recipe:
                        Intent p = new Intent(Like_activity.this, Like_activity.class);
                        startActivity(p);
                        finish();
                        return true;
                    case R.id.uploaded_recipies:
                        Intent j = new Intent(Like_activity.this, myUploadedrecipies.class);
                        startActivity(j);
                        finish();
                        return true;
                }

                return true;
            }
        });



        recipes1 = new ArrayList<>();

        recipes1.add(new Recipes("Donut","1 cup whole milk.\n" +
                "1/4 cup plus 1 teaspoon granulated sugar, divided.\n" +
                "1 packet (or 2 1/4 teaspoon.) active dry yeast.\n " +
                "4 cup all-purpose flour, plus more if needed.\n" +
                "1/2 teaspoon kosher salt.\n" +
                "6 tbsp. melted butter.\n" +
                "2 large eggs.\n" +
                "1/2 tsp. pure vanilla extract.\n" +
                "Canola or vegetable oil, for frying.","Method","\n" +
                "Grease a large bowl with cooking spray and set aside. In a small, microwave-safe bowl or glass measuring cup, add milk. Microwave until lukewarm, 40 seconds. Add a teaspoon of sugar and stir to dissolve, then sprinkle over yeast and let sit until frothy, about 8 minutes.\n" +
                "Make glaze: In a large bowl, whisk together milk, powdered sugar, and vanilla until smooth. Set aside.\n" +
                "Line a large baking sheet with paper towels. In a large dutch oven over medium heat, heat 2'' oil to 350°. Cook doughnuts, in batches, until deeply golden on both sides, about 1 minute per side. Holes will cook even faster!\n" +
                "Transfer doughnuts to paper towel-lined baking sheet to drain and cool slightly. Dip into glaze, then place onto a cooling rack (or eat immediately!)",R.drawable.donut1));

        myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

        myAdapter = new RecycleViewAdapter(this, recipes1);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);
    }
}