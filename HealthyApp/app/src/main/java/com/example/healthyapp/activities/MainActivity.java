package com.example.healthyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;

import com.example.healthyapp.R;
import com.example.healthyapp.activities.LoginActivity;
import com.example.healthyapp.fragments.AboutMealFragment;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.fragments.MealsFragment;
import com.example.healthyapp.fragments.WorkoutFragment;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements ActivityFragmentCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                HomeFragment.newInstance(" ", " ")).commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_meals:
                            selectedFragment = new MealsFragment();
                            break;
                        case R.id.nav_workout:
                            selectedFragment = new WorkoutFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void replaceWithAboutMealFragment() {
         FragmentTransaction fragmentTransaction;
         FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,  AboutMealFragment.newInstance("",""), "AboutMealFragmentTag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}