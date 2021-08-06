package com.example.healthyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.example.healthyapp.R;
import com.example.healthyapp.fragments.AboutMealFromHomeFragment;
import com.example.healthyapp.fragments.AboutMealFromMealsFragment;
import com.example.healthyapp.fragments.AboutWorkoutFragment;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.fragments.HoursSleepFragment;
import com.example.healthyapp.fragments.MealsFragment;
import com.example.healthyapp.fragments.WorkoutFragment;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements ActivityFragmentCommunication, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                HomeFragment.newInstance(" ", " ")).commit();


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new HomeFragment();
                break;
//            case R.id.nav_about_me:
//                //selectedFragment = new MealsFragment();
//                break;
//            case R.id.nav_favourites:
//                // selectedFragment = new WorkoutFragment();
//                break;
            case R.id.nav_meals:
                selectedFragment = new MealsFragment();
                toolbar.setTitle("Meals");
                break;

            case R.id.nav_workouts:
                selectedFragment = new WorkoutFragment();
                toolbar.setTitle("Workouts");
                break;
//            case R.id.nav_snacks:
//                // selectedFragment = new
//            case R.id.nav_progress:
//                //
//            case R.id.nav_settings:
//                //
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void replaceWithAboutMealFromHomeFragment() {
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, AboutMealFromHomeFragment.newInstance("", ""), "AboutMealFragmentTag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void replaceWithAboutMealFromMealsFragment() {

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, AboutMealFromMealsFragment.newInstance("", ""), "AboutMealFromMealsFragmentTag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(); ///  NU MERGE !!!
                //  toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);


            }
        });


    }

    @Override
    public void replaceWithAddHoursFragment() {
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, HoursSleepFragment.newInstance("", ""), "HoursSleepFragmentTag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void replaceWithAboutWorkoutFragment() {
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, AboutWorkoutFragment.newInstance("", ""), "AboutWorkoutFragmentTag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}