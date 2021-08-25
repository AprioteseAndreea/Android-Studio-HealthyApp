package com.example.healthyapp.fragments;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthyapp.R;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.example.healthyapp.interfaces.OnAddToPreferencesClick;
import com.example.healthyapp.interfaces.OnMealItemClick;
import com.example.healthyapp.models.Meal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.healthyapp.Constants.CALORIES;
import static com.example.healthyapp.Constants.DAY;
import static com.example.healthyapp.Constants.HOW_TO_PREPARE;
import static com.example.healthyapp.Constants.ID;
import static com.example.healthyapp.Constants.IMAGE_PATH;
import static com.example.healthyapp.Constants.INGREDIENTS;
import static com.example.healthyapp.Constants.MEALS_URL;
import static com.example.healthyapp.Constants.NAME;
import static com.example.healthyapp.Constants.PREP_TIME;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static String mealId;
    private RecyclerView recyclerView;
    public static ArrayList<Meal> meals = new ArrayList<>();
    public static ArrayList<Meal> favouritesMeals = new ArrayList<>();

    private ActivityFragmentCommunication activityFragmentCommunication;

    public MealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealsFragment newInstance(String param1, String param2) {
        MealsFragment fragment = new MealsFragment();
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

        View view = inflater.inflate(R.layout.fragment_meals, container, false);
        recyclerView = view.findViewById(R.id.meals_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        MealAdapter adapter = new MealAdapter(HomeFragment.meals, new OnMealItemClick() {
            @Override
            public void onClick(Meal Album) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutMealFromMealsFragment();

                }
            }
        }, new OnAddToPreferencesClick() {
            @Override
            public void onClick(Meal meal) {
                if(!favouritesMeals.contains(meal)){
                    favouritesMeals.add(meal);
                    Toast.makeText(getContext(), meal.getName()+" was added to Preferences ", Toast.LENGTH_LONG).show();
                    meal.setFavourite(true);

                }

            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}
