package com.example.healthyapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthyapp.R;
import com.example.healthyapp.activities.RegisterActivity;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.core.content.ContextCompat.getColor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutMeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutMeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private boolean isFemaleCheck = true;

    private TextView fullNameTextView;
    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText heightEditText;

    private CircleImageView femaleImage;
    private CircleImageView maleImage;

    private Button saveButton;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String FullName = "full_name";
    public static final String Email = "user";
    public static final String Age = "age";
    public static final String Weight = "weight";
    public static final String Height = "height";
    public static final String Gender = "gender";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public AboutMeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutMeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutMeFragment newInstance(String param1, String param2) {
        AboutMeFragment fragment = new AboutMeFragment();
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
        View view = inflater.inflate(R.layout.fragment_about_me, container, false);

        fullNameEditText = view.findViewById(R.id.full_name_edit_text);
        emailEditText = view.findViewById(R.id.mail_edit_text);
        ageEditText = view.findViewById(R.id.age_edit_text);
        weightEditText = view.findViewById(R.id.weight_edit_text);
        heightEditText = view.findViewById(R.id.height_edit_text);
        fullNameTextView = view.findViewById(R.id.name_label);
        femaleImage = view.findViewById(R.id.female_image);
        maleImage = view.findViewById(R.id.male_image);

        saveButton = view.findViewById(R.id.save_button);

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getString(FullName, "") != null) {
            fullNameEditText.setText(sharedPreferences.getString(FullName, ""));
            fullNameTextView.setText(sharedPreferences.getString(FullName, ""));

        }
        if (sharedPreferences.getString(Email, "") != null) {
            emailEditText.setText(sharedPreferences.getString(Email, ""));

        }
        if (sharedPreferences.getString(Age, "") != null) {
            ageEditText.setText(sharedPreferences.getString(Age, ""));

        }
        if (sharedPreferences.getString(Weight, "") != null) {
            weightEditText.setText(sharedPreferences.getString(Weight, ""));

        }
        if (sharedPreferences.getString(Height, "") != null) {
            heightEditText.setText(sharedPreferences.getString(Height, ""));

        }
        if (sharedPreferences.getString(Gender, "") != null) {
            if (sharedPreferences.getString(Gender, "").equals("female")) {
                femaleImage.setBorderColor(getColor(getContext(), R.color.baby_blue));
                maleImage.setBorderColor(getColor(getContext(), R.color.white));
                femaleImage.setBorderWidth(3);
                isFemaleCheck = true;
            } else {
                maleImage.setBorderColor(getColor(getContext(), R.color.baby_blue));
                femaleImage.setBorderColor(getColor(getContext(), R.color.white));

                maleImage.setBorderWidth(3);
                isFemaleCheck = false;
            }

        }


        femaleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFemaleCheck) {
                    femaleImage.setBorderColor(getColor(getContext(), R.color.baby_blue));
                    maleImage.setBorderColor(getColor(getContext(), R.color.white));

                    femaleImage.setBorderWidth(3);
                    isFemaleCheck = true;
                }
            }
        });
        maleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFemaleCheck) {
                    maleImage.setBorderColor(getColor(getContext(), R.color.baby_blue));
                    femaleImage.setBorderColor(getColor(getContext(), R.color.white));

                    maleImage.setBorderWidth(3);
                    isFemaleCheck = false;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(getContext(), "Saved successfully!", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    private void saveData() {
        editor.putString(FullName, fullNameEditText.getText().toString());
        editor.putString(Email, emailEditText.getText().toString());
        editor.putString(Age, ageEditText.getText().toString());
        editor.putString(Weight, weightEditText.getText().toString());
        editor.putString(Height, heightEditText.getText().toString());
        if (isFemaleCheck) {
            editor.putString(Gender, "female");

        } else {
            editor.putString(Gender, "male");

        }
        editor.commit();
        fullNameTextView.setText(sharedPreferences.getString(FullName, ""));

    }
}