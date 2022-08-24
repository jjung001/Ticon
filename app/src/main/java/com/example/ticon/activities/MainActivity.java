package com.example.ticon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticon.R;
import com.example.ticon.data.DataProvider;
import com.example.ticon.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder{
        ImageButton  wishlistBtn;
        Button changeToSearchFunny;
        Button changeToSearchCharacter;
        Button changeToSearchAnimals;
        SearchView searchView;
        public ViewHolder() {
            wishlistBtn = findViewById(R.id.favorite);
            changeToSearchFunny = findViewById(R.id.button1);
            changeToSearchCharacter = findViewById(R.id.button2);
            changeToSearchAnimals = findViewById(R.id.button3);
            searchView = findViewById(R.id.action_search);
        }
    }
    ViewHolder vh;
    DataProvider dp;

    String category = "";
    String listType = "home";

    RecyclerView popularRV;
    RecyclerView newRV;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.ticon.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vh = new ViewHolder();

        popularRV = findViewById(R.id.popular_recycler_view);
        DataProvider.getAllData(emoticons -> {
            // we are initializing our adapter class and passing our arraylist to it.
            EmoticonAdapter emoticonAdapter = new EmoticonAdapter(this, emoticons, listType);
            getData(emoticonAdapter, popularRV);
        });


        newRV = findViewById(R.id.new_recycler_view);
        DataProvider.getAllData(emoticons -> {
            // we are initializing our adapter class and passing our arraylist to it.
            EmoticonAdapter emoticonAdapter = new EmoticonAdapter(this, emoticons, listType);
            getData(emoticonAdapter, newRV);
        });

//        Button changeToSearchFunny = findViewById(R.id.button1);
        vh.changeToSearchFunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "funny";
                listType = "three";
                Intent funnyIntent = new Intent(getBaseContext(), ListActivity.class);
                funnyIntent.putExtra("category", category);
                funnyIntent.putExtra("listType", listType);
                startActivity(funnyIntent);
            }
        });

//        Button changeToSearchCharacter = findViewById(R.id.button2);
        vh.changeToSearchCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "character";
                listType = "four";
                Intent characterIntent = new Intent(getBaseContext(), ListActivity.class);
                characterIntent.putExtra("category", category);
                characterIntent.putExtra("listType", listType);
                startActivity(characterIntent);
            }
        });

//        Button changeToSearchAnimals = findViewById(R.id.button3);
        vh.changeToSearchAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "animals";
                listType = "three";
                Intent animalsIntent = new Intent(getBaseContext(), ListActivity.class);
                animalsIntent.putExtra("category", category);
                animalsIntent.putExtra("listType", listType);
                startActivity(animalsIntent);
            }
        });


//        ImageButton changeToDetails = findViewById(R.id.detailsButton);
//        changeToDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                changeActivityDetails();
//            }
//        });

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }


//    private void changeActivityDetails() {
//        Intent intent = new Intent(this, DetailsActivity.class);
//        startActivity(intent);
//    }

    protected void getData(EmoticonAdapter emoticonAdapter, RecyclerView rv) {
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as horizontal
        Collections.sort(emoticonAdapter.getAllEmoticons(), new SortByDate());
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rv.setLayoutManager(layout);
        rv.setAdapter(emoticonAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}