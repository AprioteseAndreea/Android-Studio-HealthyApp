package com.example.healthyapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthyapp.R;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.adapters.WorkoutAdapter;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.example.healthyapp.interfaces.OnMealItemClick;
import com.example.healthyapp.interfaces.OnWorkoutItemClick;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Workout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static String workoutId;
    private RecyclerView recyclerView;
    public static ArrayList<Workout> workouts = new ArrayList<>();
    private ActivityFragmentCommunication activityFragmentCommunication;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutFragment newInstance(String param1, String param2) {
        WorkoutFragment fragment = new WorkoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        recyclerView = view.findViewById(R.id.workout_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getWorkouts();
        return view;
    }

    private void getWorkouts() {
        WorkoutAdapter adapter = new WorkoutAdapter(HomeFragment.workouts, new OnWorkoutItemClick() {
            @Override
            public void onClick(Workout workout) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutWorkoutFragment();

                }
            }
        });

        recyclerView.setAdapter(adapter);
    }
}