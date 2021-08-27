package com.example.healthyapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.activities.LoginActivity;
import com.example.healthyapp.adapters.SnackAdapter;
import com.example.healthyapp.adapters.TodayMealsAdapter;
import com.example.healthyapp.adapters.TodayWorkoutAdapter;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.example.healthyapp.interfaces.OnMealItemClick;
import com.example.healthyapp.interfaces.OnSnackItemClick;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Snack;
import com.example.healthyapp.models.Workout;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static com.example.healthyapp.Constants.BACKGROUND;
import static com.example.healthyapp.Constants.CALORIES;
import static com.example.healthyapp.Constants.CARBS;
import static com.example.healthyapp.Constants.DAY;
import static com.example.healthyapp.Constants.FAT;
import static com.example.healthyapp.Constants.FIBRE;
import static com.example.healthyapp.Constants.HOW_TO_PREPARE;
import static com.example.healthyapp.Constants.ID;
import static com.example.healthyapp.Constants.IMAGE_PATH;
import static com.example.healthyapp.Constants.INGREDIENTS;
import static com.example.healthyapp.Constants.LINK;
import static com.example.healthyapp.Constants.MEALS_URL;
import static com.example.healthyapp.Constants.NAME;
import static com.example.healthyapp.Constants.PATH;
import static com.example.healthyapp.Constants.PREP_TIME;
import static com.example.healthyapp.Constants.PROTEIN;
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

    private RecyclerView mealsrecyclerView;
    private RecyclerView snacksRecyclerView;
    private RecyclerView workoutRecyclerView;

    public static ArrayList<Meal> meals = new ArrayList<>();
    public static ArrayList<Meal> todayMeals = new ArrayList<>();
    public static ArrayList<Snack> snacks = new ArrayList<>();
    public static ArrayList<Workout> workouts = new ArrayList<>();


    private ImageView snack_image_one;
    private ImageView snack_image_two;
    private ImageView snack_image_three;
    private ImageView snack_image_four;

    private TextView greeting_text_view;
    private ImageView weather_icon;

    private ImageView avatar_image;
    private ActivityFragmentCommunication activityFragmentCommunication;
    private TextView dateTimeDisplay;

    private TextView totalProteins;
    private TextView totalKcals;

    public static Meal mealClicked = null;
    public static String workoutClicked = null;

    private static boolean mealsWasReading = false;


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

        snack_image_one = view.findViewById(R.id.snack_image_one);
        snack_image_two = view.findViewById(R.id.snack_image_two);
        snack_image_three = view.findViewById(R.id.snack_image_third);
        snack_image_four = view.findViewById(R.id.snack_image_fourth);

        mealsrecyclerView = view.findViewById(R.id.today_meals_recyclerView);
        greeting_text_view = view.findViewById(R.id.greeting_message);
        weather_icon = view.findViewById(R.id.today_icon);
        avatar_image = view.findViewById(R.id.home_avatar_image);

        totalKcals = view.findViewById(R.id.total_kcals);
        totalProteins = view.findViewById(R.id.total_protains);

        avatar_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutMeFragment();

                }
            }
        });
        setGreetings();



        //snacksRecyclerView = view.findViewById(R.id.today_snacks_recyclerView);
        //workoutRecyclerView = view.findViewById(R.id.today_workout_recyclerView);
//
//        Button logout = view.findViewById(R.id.logout_btn);
//        logout.setOnClickListener(v -> {
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(getContext(), LoginActivity.class));
//
//        });

//        Button addHours = view.findViewById(R.id.addHours_btn);
//        addHours.setOnClickListener(v -> {
//            if (activityFragmentCommunication != null) {
//                activityFragmentCommunication.replaceWithAddHoursFragment();
//            }
//        });


//        Button drunk = view.findViewById(R.id.drink);
//        TextView water_glasses = view.findViewById(R.id.water_glasses);
//        drunk.setOnClickListener(v -> {
//            int waterGlasses = Integer.parseInt((String) water_glasses.getText());
//            if (waterGlasses > 0) {
//                water_glasses.setText(String.valueOf(waterGlasses - 1));
//            }
//
//        });
        dateTimeDisplay = view.findViewById(R.id.date_time);
        getDate();

        mealsrecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        getMeals();
        getSnacks();
        //getWorkout();
        return view;
    }

    private void getTotalProteins() {
        float sum = 0;
        for (Meal m : todayMeals) {
            float value = Float.parseFloat(m.getProtein().substring(0, m.getProtein().length() - 1));
            sum += value;

        }
        totalProteins.setText(String.valueOf( sum));
    }

    private void getTotalKcals() {
        int sum = 0;
        for (Meal m : todayMeals) {
            int value = Integer.parseInt(m.getCalories().substring(0, m.getCalories().length() - 6));
            sum += value;

        }
        totalKcals.setText(String.valueOf(sum));
    }

    private void setGreetings() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        if (currentHour >= 5 && currentHour <= 12) {
            greeting_text_view.setText("Good morning!");
            // Glide.with(getView()).load(R.drawable.green_sun_icon).apply(new RequestOptions().override(25, 25)).into(weather_icon);

        } else if (currentHour > 12 && currentHour <= 18) {
//            Glide.with(getView()).load(R.drawable.green_sun_icon).apply(new RequestOptions().override(25, 25)).into(weather_icon);
            // greeting_text_view.setText("Good afternoon!");


        } else if (currentHour > 18 && currentHour < 22) {
            greeting_text_view.setText("Good evening!");
            // Glide.with(getView()).load(R.drawable.green_moon_icon).apply(new RequestOptions().override(25, 25)).into(weather_icon);

        } else {
            greeting_text_view.setText("Good night!");
            // Glide.with(getView()).load(R.drawable.green_moon_icon).apply(new RequestOptions().override(25, 25)).into(weather_icon);

        }
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
                Toast.makeText(getContext(), "ERROR: get WORKOUT failed with error: " +
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

    private void handleWorkoutResponse(String responseJson) throws JSONException {
        workouts.clear();
        JSONArray usersJSONArray = new JSONArray(responseJson);
        for (int i = 0; i < usersJSONArray.length(); i++) {
            JSONObject obj = usersJSONArray.getJSONObject(i);
            String id = obj.getString(ID);
            String day = obj.getString(DAY);
            String name = obj.getString(NAME);
            String time = obj.getString(TIME);
            String link = obj.getString(LINK);
            String background = obj.getString(BACKGROUND);
            Workout workout = new Workout(id, day, name, time, link, background);
            workouts.add(workout);
        }
        getTodayWorkout();
    }

    private void getTodayWorkout() {
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_WEEK);
        ArrayList<Workout> todayWorkout = new ArrayList<>();
        for (Workout w : workouts) {

            switch (currentDay) {
                case 1: {
                    if (w.getDay().equals("1")) {
                        todayWorkout.add(w);
                    }
                    break;
                }
                case 2: {
                    if (w.getDay().equals("2")) {
                        todayWorkout.add(w);
                    }
                    break;
                }
                case 3: {
                    if (w.getDay().equals("3")) {
                        todayWorkout.add(w);
                    }
                    break;
                }
                case 4: {
                    if (w.getDay().equals("4")) {
                        todayWorkout.add(w);
                    }
                    break;
                }
                case 5: {
                    if (w.getDay().equals("5")) {
                        todayWorkout.add(w);
                    }
                    break;
                }
                case 6: {
                    if (w.getDay().equals("6")) {
                        todayWorkout.add(w);
                    }
                    break;
                }
                case 7: {
                    if (w.getDay().equals("7")) {
                        todayWorkout.add(w);
                    }
                    break;
                }
            }
        }
        TodayWorkoutAdapter workoutAdapter = new TodayWorkoutAdapter(todayWorkout, workout -> {
            if (activityFragmentCommunication != null) {
                activityFragmentCommunication.replaceWithAboutWorkoutFragment();

            }
        });
        workoutRecyclerView.setAdapter(workoutAdapter);
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

                }, error -> Toast.makeText(getContext(), "ERROR: get MEALS failed with error: " +
                error.getMessage(), Toast.LENGTH_LONG).show());

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
                Toast.makeText(getContext(), "ERROR: get MEALS failed with error: " +
                        error.getMessage(), Toast.LENGTH_LONG).show();
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


        SnackAdapter adapter = new SnackAdapter(randomSnacks, new OnSnackItemClick() {
            @Override
            public void onClick(Snack snack) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutSnackFragment();

                }
            }
        });
//        snacksRecyclerView.setAdapter(adapter);

        String imageViewUrl1 = randomSnacks.get(0).getPath();
        String imageViewUrl2 = randomSnacks.get(1).getPath();
        String imageViewUrl3 = randomSnacks.get(2).getPath();
        String imageViewUrl4 = randomSnacks.get(3).getPath();

        ImageLoader imageLoader1 = VolleyConfigSingleton.getInstance(snack_image_one.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader1.get(imageViewUrl1, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                snack_image_one.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ImageLoader imageLoader2 = VolleyConfigSingleton.getInstance(snack_image_two.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader2.get(imageViewUrl2, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                snack_image_two.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ImageLoader imageLoader3 = VolleyConfigSingleton.getInstance(snack_image_three.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader3.get(imageViewUrl3, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                snack_image_three.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ImageLoader imageLoader4 = VolleyConfigSingleton.getInstance(snack_image_four.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader4.get(imageViewUrl4, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                snack_image_four.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void handleMealResponse(String responseJson) throws JSONException {
        if (!mealsWasReading) {


            meals.clear();
            todayMeals.clear();
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
                String carbs = obj.getString(CARBS);
                String protein = obj.getString(PROTEIN);
                String fibre = obj.getString(FIBRE);
                String fat = obj.getString(FAT);

                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                int currentDay = calendar.get(Calendar.DATE);

                Meal meal = new Meal(id, day, name, preptime, calories, imagePath, ingredients, howtoprepare, carbs, protein, fibre, fat, false);
                boolean exist = meals.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
                if (!exist) {
                    meals.add(meal);

                }

                if (day.equals(String.valueOf(currentDay))) {
                    todayMeals.add(meal);
                }

            }
            todayMeals.add(meals.get(meals.size() - 1));
            mealsWasReading = true;

        }
        TodayMealsAdapter adapter = new TodayMealsAdapter(todayMeals, new OnMealItemClick() {
            @Override
            public void onClick(Meal meal) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutMealFromHomeFragment();

                }
            }
        });
        mealsrecyclerView.setAdapter(adapter);
        getTotalProteins();
        getTotalKcals();

    }


    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}