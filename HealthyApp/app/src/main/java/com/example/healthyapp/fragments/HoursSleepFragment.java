package com.example.healthyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthyapp.R;
import com.example.healthyapp.adapters.HoursSleepAdapter;
import com.example.healthyapp.data.HoursSleepRepository;
import com.example.healthyapp.models.HoursSleepElement;
import com.example.healthyapp.models.dbEntities.HoursSleep;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HoursSleepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoursSleepFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private DatePicker dataPicker;
    private EditText hours;
    private Button addBtn;

    private HoursSleepRepository hoursSleepRepository = new HoursSleepRepository();
    private HoursSleepAdapter hoursSleepAdapter;

    private RecyclerView recyclerView;
    private ArrayList<HoursSleepElement> hoursSleepElements;

    public HoursSleepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HoursSleepFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HoursSleepFragment newInstance(String param1, String param2) {
        HoursSleepFragment fragment = new HoursSleepFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addBtn = view.findViewById(R.id.add_hour);
        dataPicker = view.findViewById(R.id.data_picker);
        hours = view.findViewById(R.id.hours);

        addBtn.setOnClickListener(v -> insertHoursSleep());
        getHoursSleep();



    }

    public void insertHoursSleep() {
        String currentHours = hours.getText().toString();
        if (hours == null) {
            hours.setError(getString(R.string.error_required));
        }
        String date = String.valueOf(dataPicker.getDayOfMonth()) + "/"+
                String.valueOf( dataPicker.getMonth())+"/"+ String.valueOf(dataPicker.getYear());
        HoursSleep hoursSleep = new HoursSleep(date, currentHours);
        hoursSleepRepository.insertHoursSleep(hoursSleep, () -> {
            hoursSleepElements.add(hoursSleep.convert());
            hoursSleepAdapter.notifyItemChanged(hoursSleepElements.size()-1);
            hoursSleepAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "Success.", Toast.LENGTH_SHORT).show();

        });
        hours.setText("");
    }

    public void getHoursSleep() {

        hoursSleepRepository.getAllHoursSleep(items -> {
            hoursSleepElements.clear();
            for(HoursSleep h : items){
                hoursSleepElements.add(h.convert());
            }
            hoursSleepAdapter.notifyDataSetChanged();
        });

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
        hoursSleepElements = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_hours_sleep, container, false);
        recyclerView = view.findViewById(R.id.hours_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        hoursSleepElements.clear();
        hoursSleepAdapter = new HoursSleepAdapter(hoursSleepElements);
        recyclerView.setAdapter(hoursSleepAdapter);

        return view;
    }
}