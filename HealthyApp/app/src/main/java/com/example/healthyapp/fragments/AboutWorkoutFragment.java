package com.example.healthyapp.fragments;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.models.Workout;
import com.example.healthyapp.receivers.AlarmReceiver;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutWorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutWorkoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView workoutTitle;
    private TextView workoutTime;
    private TextView workoutDay;
    private ImageView workoutImage;

    TextView selectedTime;
    Button selectTime;
    Button setAlarm;
    Button cancelAlarm;

    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public AboutWorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutWorkoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutWorkoutFragment newInstance(String param1, String param2) {
        AboutWorkoutFragment fragment = new AboutWorkoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_about_workout, container, false);

        workoutTitle = view.findViewById(R.id.title_workout);
        workoutTime = view.findViewById(R.id.workout_time);
        workoutDay = view.findViewById(R.id.workout_day);
        workoutImage = view.findViewById(R.id.about_workout_image);

        selectedTime = view.findViewById(R.id.selectedTime);
        selectTime = view.findViewById(R.id.selectTimeBtn);
        setAlarm = view.findViewById(R.id.setAlarmBtn);
        cancelAlarm = view.findViewById(R.id.cancelAlarmBtn);

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);

        int workoutId = Integer.parseInt(HomeFragment.workoutClicked);
        workoutTitle.setText(HomeFragment.workouts.get(workoutId).getName());
        workoutTime.setText(HomeFragment.workouts.get(workoutId).getTime());
        workoutDay.setText(HomeFragment.workouts.get(workoutId).getDay());
        getLifecycle().addObserver(youTubePlayerView);

        Workout currentWorkout = HomeFragment.workouts.get(workoutId);
        String myYouTubeVideoUrl = currentWorkout.getLink();

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = HomeFragment.workouts.get(workoutId).getLink();
                youTubePlayer.loadVideo(myYouTubeVideoUrl, 0);
                youTubePlayer.pause();
            }
        });

        String imageViewUrl = HomeFragment.workouts.get(workoutId).getBackground();
        ImageLoader imageLoader = VolleyConfigSingleton.getInstance(workoutImage.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader.get(imageViewUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                workoutImage.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        createNotificationChannel();
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();

            }


        });
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

        return view;
    }

    private void cancelAlarm() {
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);

        if (alarmManager == null) {
            alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(getContext(), "Alarm Cancelled", Toast.LENGTH_SHORT).show();

    }

    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getFragmentManager(), "foxandroid");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picker.getHour() > 12) {
                    selectedTime.setText(String.format(picker.getHour() - 12 + picker.getMinute() + " PM"));
                } else {
                    selectedTime.setText(
                            String.format(picker.getHour() + " : " + picker.getMinute() + " AM")
                    );
                }
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

            }
        });
    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(getContext(), "Alarm set Successfully", Toast.LENGTH_SHORT).show();


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "foxandroidReminderChannel";
            String description = "Channel For Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("foxandroid", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }


}