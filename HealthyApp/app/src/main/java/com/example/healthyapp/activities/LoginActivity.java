package com.example.healthyapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {
    Button createAccountBtn, loginBtn;
    EditText username, password;
    TextView forgotPassword;
    FirebaseAuth firebaseAuth;
    CheckBox rememberMeCheckBox;
    AlertDialog.Builder reset_alert;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String User = "user";
    public static final String Password = "password";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        reset_alert = new AlertDialog.Builder(getApplicationContext());
        createAccountBtn = findViewById(R.id.createAccount);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


        username = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginb);
//        forgotPassword = findViewById(R.id.forgot_password);
        rememberMeCheckBox = findViewById(R.id.remember_check_box);

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString(User, "") != null && sharedPreferences.getString(Password, "") != null) {
            username.setText(sharedPreferences.getString(User, ""));
            password.setText(sharedPreferences.getString(Password, ""));

        }
      /*  forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_alert.setTitle("Reset Forgot Password ?")
                        .setMessage("Enter Your Email to get Password Reset link.")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //validate the email link
                                //send the reset link
                            }
                        }).setNegativeButton("Cancel", null).create().show();

            }

        });*/

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (username.getText().toString().isEmpty()) {
                    username.setError("Email is Missing.");
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Password is Missing.");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                if (rememberMeCheckBox.isChecked()) {
                    saveUserInSharedPreferences(username.getText().toString(), password.getText().toString());
                }
            }
        });

    }

    private void saveUserInSharedPreferences(String email, String password) {

        editor.putString(User, email);
        editor.putString(Password, password);
        editor.commit();
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
