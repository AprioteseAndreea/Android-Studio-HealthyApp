package com.example.healthyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthyapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText registerFullName, registerEmail, registerPassword, registerConfPass;
    Button registerUserBtn, gotoLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFullName = findViewById(R.id.textInputLayoutEditTextFullName);
        registerEmail = findViewById(R.id.textInputLayoutEditTextEmailAddress);
        registerPassword = findViewById(R.id.textInputLayoutEditTextPassword);
        registerConfPass = findViewById(R.id.textInputLayoutEditTextConfPassword);
        registerUserBtn = findViewById(R.id.registerBtn);
        gotoLogin = findViewById(R.id.gotoLogin);

        fAuth = FirebaseAuth.getInstance();

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extract the data from the form

                String fullName = registerFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confPass = registerConfPass.getText().toString();

                if (fullName.isEmpty()) {
                    registerFullName.setError("Full name is Required");
                    return;
                }
                if (email.isEmpty()) {
                    registerEmail.setError("Email is Required");
                    return;
                }
                if (password.isEmpty()) {
                    registerPassword.setError("Password is Required");
                    return;
                }
                if (confPass.isEmpty()) {
                    registerConfPass.setError("Confirmation password is Required");
                    return;
                }
                if (!password.equals(confPass)) {
                    registerConfPass.setError("Password Do not match!");
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Data validated!", Toast.LENGTH_SHORT).show();
                fAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}