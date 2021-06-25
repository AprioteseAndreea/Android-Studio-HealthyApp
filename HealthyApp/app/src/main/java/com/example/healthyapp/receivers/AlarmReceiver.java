package com.example.healthyapp.receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.healthyapp.R;
import com.example.healthyapp.fragments.AboutWorkoutFragment;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, AboutWorkoutFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);

          NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "foxandroid")
                  .setSmallIcon(R.mipmap.ic_launcher)
                  .setContentTitle("Healthy App Alarm")
                  .setContentText("You have a workout to do!")
                  .setAutoCancel(true)
                  .setDefaults(NotificationCompat.DEFAULT_ALL)
                  .setPriority(NotificationCompat.PRIORITY_HIGH)
                  .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());

    }
}
