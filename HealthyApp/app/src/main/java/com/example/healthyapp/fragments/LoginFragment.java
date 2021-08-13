package com.example.healthyapp.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthyapp.R;
import com.example.healthyapp.activities.LoginActivity;
import com.example.healthyapp.activities.MainActivity;
import com.example.healthyapp.activities.RegisterActivity;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.example.healthyapp.interfaces.LoginActivityFragmentCommunication;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    Button loginBtn;
    TextView createAccountBtn;
    TextInputEditText username;
    TextInputEditText password;
    TextView forgotPassword;
    public static FirebaseAuth firebaseAuth;
    Switch rememberMeSwitch;
    AlertDialog.Builder reset_alert;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String User = "user";
    public static final String Password = "password";

    public static SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private LoginActivityFragmentCommunication loginActivityFragmentCommunication;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        reset_alert = new AlertDialog.Builder(getContext());
        createAccountBtn = view.findViewById(R.id.createAccount);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });

        username = view.findViewById(R.id.textInputLayoutEditTextEmail);
        password = view.findViewById(R.id.textInputLayoutEditTextPassword);
        loginBtn = view.findViewById(R.id.loginb);
        forgotPassword = view.findViewById(R.id.forgot_password_text);
        rememberMeSwitch = view.findViewById(R.id.remember_me_switch);



        sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString(User, "") != null && sharedPreferences.getString(Password, "") != null) {
            username.setText(sharedPreferences.getString(User, ""));
            password.setText(sharedPreferences.getString(Password, ""));

        }
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginActivityFragmentCommunication != null) {
                    loginActivityFragmentCommunication.replaceWithForgotPasswordFragment();

                }
            }
        });

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
                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),
                        password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                if (rememberMeSwitch.isChecked()) {
                    saveUserInSharedPreferences(username.getText().toString(), password.getText().toString());
                }
            }
        });
        return view;
    }
    private void saveUserInSharedPreferences(String email, String password) {

        editor.putString(User, email);
        editor.putString(Password, password);
        editor.commit();
    }
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        if (context instanceof LoginActivityFragmentCommunication) {
            loginActivityFragmentCommunication = (LoginActivityFragmentCommunication) context;
        }
    }
}