package com.example.healthyapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthyapp.R;
import com.example.healthyapp.adapters.SnackAdapter;
import com.example.healthyapp.adapters.WorkoutAdapter;
import com.example.healthyapp.interfaces.ActivityFragmentCommunication;
import com.example.healthyapp.interfaces.OnSnackItemClick;
import com.example.healthyapp.interfaces.OnWorkoutItemClick;
import com.example.healthyapp.models.Snack;
import com.example.healthyapp.models.Workout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SnacksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SnacksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ActivityFragmentCommunication activityFragmentCommunication;

    public SnacksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SnacksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SnacksFragment newInstance(String param1, String param2) {
        SnacksFragment fragment = new SnacksFragment();
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
        View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        recyclerView = view.findViewById(R.id.snacks_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        getSnacks();

        return view;
    }

    private void getSnacks() {
        ArrayList<Snack> allSnacks = new ArrayList<>();
        for (Snack s: HomeFragment.snacks) {
            boolean exist = allSnacks.stream().filter(o -> o.getPath().equals(s.getPath())).findFirst().isPresent();
            if (!exist) {
                allSnacks.add(s);

            }

        }
        SnackAdapter adapter = new SnackAdapter(allSnacks, new OnSnackItemClick() {
            @Override
            public void onClick(Snack snack) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutSnackFragment();

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