package com.example.healthyapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthyapp.R;
import com.example.healthyapp.fragments.AboutWorkoutFragment;
import com.example.healthyapp.fragments.ForgotPasswordFragment;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.fragments.LoginFragment;
import com.example.healthyapp.interfaces.LoginActivityFragmentCommunication;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class LoginActivity extends AppCompatActivity implements LoginActivityFragmentCommunication {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment_container,
                LoginFragment.newInstance(" ", " ")).commit();



    }


    @Override
    public void replaceWithForgotPasswordFragment() {

        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment_container, ForgotPasswordFragment.newInstance("", ""), "ForgotPasswordFragmentTag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

    /*@Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }*/
