package com.example.healthyapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthyapp.R;
import com.example.healthyapp.activities.LoginActivity;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.adapters.SnackAdapter;
import com.example.healthyapp.adapters.TodayMealsAdapter;
import com.example.healthyapp.adapters.WorkoutAdapter;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.example.healthyapp.interfaces.OnMealItemClick;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Snack;
import com.example.healthyapp.models.Workout;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static com.example.healthyapp.Constants.CALORIES;
import static com.example.healthyapp.Constants.DAY;
import static com.example.healthyapp.Constants.HOW_TO_PREPARE;
import static com.example.healthyapp.Constants.ID;
import static com.example.healthyapp.Constants.IMAGE_PATH;
import static com.example.healthyapp.Constants.INGREDIENTS;
import static com.example.healthyapp.Constants.LINK;
import static com.example.healthyapp.Constants.MEALS_URL;
import static com.example.healthyapp.Constants.NAME;
import static com.example.healthyapp.Constants.PATH;
import static com.example.healthyapp.Constants.PREP_TIME;
import static com.example.healthyapp.Constants.SNACKS_URL;
import static com.example.healthyapp.Constants.TIME;
import static com.example.healthyapp.Constants.WORKOUT_URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView snacksRecyclerView;
    private RecyclerView workoutRecyclerView;

    public static ArrayList<Meal> meals = new ArrayList<>();
    public static ArrayList<Snack> snacks = new ArrayList<>();
    public static ArrayList<Workout> workouts = new ArrayList<>();

    private ActivityFragmentCommunication activityFragmentCommunication;
    private TextView dateTimeDisplay;

    private final String clickedId = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.today_meals_recyclerView);
        snacksRecyclerView = view.findViewById(R.id.today_snacks_recyclerView);
        workoutRecyclerView = view.findViewById(R.id.today_workout_recyclerView);

        Button logout = view.findViewById(R.id.logout_btn);
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));

        });

        Button addHours = view.findViewById(R.id.addHours_btn);
        addHours.setOnClickListener(v -> {
            if (activityFragmentCommunication != null) {
                activityFragmentCommunication.replaceWithAddHoursFragment();
            }
        });

        Button drunk = view.findViewById(R.id.drink);
        TextView water_glasses = view.findViewById(R.id.water_glasses);
        drunk.setOnClickListener(v -> {
            int waterGlasses = Integer.parseInt((String) water_glasses.getText());
            if (waterGlasses > 0) {
                water_glasses.setText(String.valueOf(waterGlasses - 1));
            }

        });
        dateTimeDisplay = view.findViewById(R.id.date_time);
        getDate();

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        snacksRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        getMeals();
        getSnacks();
        getWorkout();
        return view;
    }

    private void getDate() {

        Calendar calendar;
        SimpleDateFormat dateFormat;
        String date;

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        date = dateFormat.format(calendar.getTime());

        dateTimeDisplay.setText(date);
    }

    private void getWorkout() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, WORKOUT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            handleWorkoutResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "ERROR: get WORKOUT failed with error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

    private void handleWorkoutResponse(String responseJson) throws JSONException {
        workouts.clear();
        JSONArray usersJSONArray = new JSONArray(responseJson);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < usersJSONArray.length(); i++) {
            JSONObject obj = usersJSONArray.getJSONObject(i);
            String day = obj.getString(DAY);
            String name = obj.getString(NAME);
            String time = obj.getString(TIME);
            String link = obj.getString(LINK);

            Workout workout = new Workout(day, name, time, link);
            int currentDay = cal.get(Calendar.DAY_OF_WEEK);
            switch (currentDay) {
                case 1: {
                    if (workout.getDay().equals("1")) {
                        workouts.add(workout);
                    }
                    break;
                }
                case 2: {
                    if (workout.getDay().equals("2")) {
                        workouts.add(workout);
                    }
                    break;
                }
                case 3: {
                    if (workout.getDay().equals("3")) {
                        workouts.add(workout);
                    }
                    break;
                }
                case 4: {
                    if (workout.getDay().equals("4")) {
                        workouts.add(workout);
                    }
                    break;
                }
                case 5: {
                    if (workout.getDay().equals("5")) {
                        workouts.add(workout);
                    }
                    break;
                }
                case 6: {
                    if (workout.getDay().equals("6")) {
                        workouts.add(workout);
                    }
                    break;
                }
                case 7: {
                    if (workout.getDay().equals("7")) {
                        workouts.add(workout);
                    }
                    break;
                }
            }
        }
        workoutRecyclerView.setAdapter(new WorkoutAdapter(workouts));
    }

    private void getSnacks() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SNACKS_URL,
                response -> {

                    try {
                        handleSnackResponse(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "ERROR: get MEALS failed with error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

    private void getMeals() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = MEALS_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {

                    try {
                        handleMealResponse(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "ERROR: get MEALS failed with error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

    private void handleSnackResponse(String responseJson) throws JSONException {
        snacks.clear();
        JSONArray usersJSONArray = new JSONArray(responseJson);
        for (int i = 0; i < usersJSONArray.length(); i++) {
            JSONObject obj = usersJSONArray.getJSONObject(i);
            String path = obj.getString(PATH);
            Snack snack = new Snack(path);
            snacks.add(snack);
        }
        getCurrentSnacks();
    }

    public void getCurrentSnacks() {
        ArrayList<Snack> randomSnacks = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        int currentDay = calendar.get(Calendar.DATE);
        if (currentDay <= 4) {
            randomSnacks.add(snacks.get(currentDay + 0));
            randomSnacks.add(snacks.get(currentDay + 1));
            randomSnacks.add(snacks.get(currentDay + 2));
            randomSnacks.add(snacks.get(currentDay + 3));
        } else {
            randomSnacks.add(snacks.get(currentDay - 0));
            randomSnacks.add(snacks.get(currentDay - 1));
            randomSnacks.add(snacks.get(currentDay - 2));
            randomSnacks.add(snacks.get(currentDay - 3));
        }


        SnackAdapter adapter = new SnackAdapter(randomSnacks);
        snacksRecyclerView.setAdapter(adapter);
    }

    private void handleMealResponse(String responseJson) throws JSONException {
        meals.clear();
        JSONArray usersJSONArray = new JSONArray(responseJson);
        for (int i = 0; i < usersJSONArray.length(); i++) {
            JSONObject obj = usersJSONArray.getJSONObject(i);

            String id = obj.getString(ID);
            String day = obj.getString(DAY);
            String name = obj.getString(NAME);
            String imagePath = obj.getString(IMAGE_PATH);
            String ingredients = obj.getString(INGREDIENTS);
            String howtoprepare = obj.getString(HOW_TO_PREPARE);
            String preptime = obj.getString(PREP_TIME);
            String calories = obj.getString(CALORIES);

            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            int currentDay = calendar.get(Calendar.DATE);

            if (day.equals(String.valueOf(currentDay))) {
                Meal meal = new Meal(id, day, name, preptime, calories, imagePath, ingredients, howtoprepare);
                meals.add(meal);
            }

        }


        TodayMealsAdapter adapter = new TodayMealsAdapter(meals, new OnMealItemClick() {
            @Override
            public void onClick(Meal meal) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutMealFragment();

                }
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}