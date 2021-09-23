package com.example.cookfrombook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.like.LikeButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;


    Button b1, b2;



    RecyclerView myrecyclerView;
    RecycleViewAdapter myAdapter;

    List<Recipes> recipes1;

    public static boolean status = false;
    public static boolean status1 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {

                    case R.id.home_menu:
                        Toast.makeText(MainActivity.this, "home is clicked", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.liked_recipe:
                        Intent p = new Intent(MainActivity.this, Like_activity.class);
                        startActivity(p);
                        finish();
                        return true;
                    case R.id.uploaded_recipies:
                        Intent j = new Intent(MainActivity.this, myUploadedrecipies.class);
                        startActivity(j);
                        finish();
                        return true;
                }

                return true;
            }
        });




        if(status==true){
            b1.setText("LogOut");
            b2.setText("Upload");
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.uploaded_recipies).setVisible(true);
            View headerView = navigationView.getHeaderView(0);
            TextView navUsername = (TextView) headerView.findViewById(R.id.name);
            navUsername.setText("Rutuja Kulkarni");


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b1.setText("Sign Up");
                    b2.setText("Login");
                    MainActivity.status=false;
                    finish();
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, UploadRecipePage.class);
                    startActivity(intent);
                }
            });
        }
        else{
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SignupPage.class);
                    startActivity(intent);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginPage.class);
                    startActivity(intent);
                }
            });
        }

        Intent j = getIntent();
        String r_name = j.getStringExtra("rname");
        String r_in = j.getStringExtra("ringridents");
        String Reci = j.getStringExtra("reci");


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

        recipes1.add(new Recipes("Dosa","3 cups rice.\n" +
                "1 cup urad daal (split, skinless black gram).\n" +
                "3/4 teaspoon fenugreek seeds.\n" +
                "Salt (to taste).\n" +
                "Vegetable / canola / sunflower cooking oil.","Method",
                "Wash the rice and urad daal well. Add the fenugreek seeds to the mix and fill enough water in the rice-daal bowl to cover them about 2-inch deep. Soak overnight.\n" +
                        "Put some cooking oil in a small bowl and keep ready. You will also need a bowl of ice cold water, a large, flat nonstick pan, 2 sheets of paper towel, a ladle, a spatula, and a basting brush.\n" +
                        "When the upper surface begins to look cooked (it will no longer look soft or runny), flip the dosa. By this time, ideally, the surface that was underneath should be light golden in color. Cook for 1 minute after flipping.\n" +
                        "The dosa is almost done. Fold it in half and allow to cook for 30 seconds more.",R.drawable.dosa1));

        recipes1.add(new Recipes("Pancake","1 1/4 cups milk.\n" +
                "1 egg." +
                "3 tablespoons butter melted.\n" +
                "1 1/2 cups all-purpose flour.\n" +
                "3 1/2 teaspoons baking powder.\n" +
                "1 teaspoon salt.\n" +
                "1 tablespoon white sugar.","Method",
                "In a large bowl, sift together the flour, baking powder, salt and sugar. Make a well in the center and pour in the milk, egg and melted butter; mix until smooth." +
                        "Heat a lightly oiled griddle or frying pan over medium high heat. Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.",R.drawable.pancakes));

        if(status1 == true)
            recipes1.add(new Recipes(r_name, r_in, "Method", Reci, R.drawable.pasta1));

        myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

        myAdapter = new RecycleViewAdapter(this, recipes1);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);
    }
}