package com.example.healthyapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.models.Meal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutMealFromMealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutMealFromMealsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutMealFromMealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutMealFromMealsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutMealFromMealsFragment newInstance(String param1, String param2) {
        AboutMealFromMealsFragment fragment = new AboutMealFromMealsFragment();
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
        View view = inflater.inflate(R.layout.fragment_about_meal_from_meals, container, false);

        TextView title = view.findViewById(R.id.about_meal_title);
        TextView prepTime = view.findViewById(R.id.prep_time);
        TextView kcals = view.findViewById(R.id.kcals);
        TextView ingredients = view.findViewById(R.id.ingredients);
        TextView howtoprepare = view.findViewById(R.id.howtoprepare);

        int mealId = Integer.parseInt(HomeFragment.clickedId)-1 ;

        title.setText(MealsFragment.meals.get(mealId).getName());
        prepTime.setText(MealsFragment.meals.get(mealId).getPreptime());
        kcals.setText(MealsFragment.meals.get(mealId).getCalories());
        ingredients.setText(MealsFragment.meals.get(mealId).getIngredients());
        howtoprepare.setText(MealsFragment.meals.get(mealId).getHowtoprepare());

        ImageView imageView = view.findViewById(R.id.about_meal_image);
        String imageViewUrl = MealsFragment.meals.get(mealId).getImagePath();
        ImageLoader imageLoader = VolleyConfigSingleton.getInstance(imageView.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader.get(imageViewUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return view;
    }
    }
