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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.models.Meal;
import com.jgabrielfreitas.core.BlurImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutMealFromHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutMealFromHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutMealFromHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutMealFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutMealFromHomeFragment newInstance(String param1, String param2) {
        AboutMealFromHomeFragment fragment = new AboutMealFromHomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_about_meal, container, false);

        TextView title = view.findViewById(R.id.about_meal_title);
        TextView prepTime = view.findViewById(R.id.prep_time);
        TextView kcals = view.findViewById(R.id.kcals);
        TextView ingredients = view.findViewById(R.id.ingredients);
        TextView howtoprepare = view.findViewById(R.id.howtoprepare);
        BlurImageView background_image_view = view.findViewById(R.id.background_image_view_recipe);

        TextView carbs_value = view.findViewById(R.id.carbs_value);
        TextView protein_value = view.findViewById(R.id.protein_value);
        TextView fibre_value = view.findViewById(R.id.fibre_value);
        TextView fat_value = view.findViewById(R.id.fat_value);
        ImageView imageView = view.findViewById(R.id.about_meal_image);



        background_image_view.setBlur(7);
        title.setText(HomeFragment.mealClicked.getName());
        prepTime.setText(HomeFragment.mealClicked.getPreptime());
        kcals.setText(HomeFragment.mealClicked.getCalories());
        ingredients.setText(HomeFragment.mealClicked.getIngredients());
        howtoprepare.setText(HomeFragment.mealClicked.getHowtoprepare());
        carbs_value.setText(HomeFragment.mealClicked.getCarbs());
        protein_value.setText(HomeFragment.mealClicked.getProtein());
        fibre_value.setText(HomeFragment.mealClicked.getFibre());
        fat_value.setText(HomeFragment.mealClicked.getFat());

        String imageViewUrl = HomeFragment.mealClicked.getImagePath();
        Glide.with(view).load(imageViewUrl).apply(new RequestOptions().override(300, 300)).into(imageView);
        Glide.with(view).load(imageViewUrl).apply(new RequestOptions().override(300, 300)).into(background_image_view);

        return view;
    }
}