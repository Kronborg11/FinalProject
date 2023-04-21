package com.example.finalproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class NotificationService extends Service {
    static final String CHANNEL_ID = "1";

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        createNotificationChannel();

        final ConnectivityManager[] connectivityManager = {(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)};
        final NetworkInfo networkInfo = connectivityManager[0].getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            final Timer timer = new Timer(true);


            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    final Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    final PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

                    String displayResult = "";
                    String quote = "";

                    try {
                        URL url = new URL("https://api.api-ninjas.com/v1/quotes?category=inspirational");
                        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                        connection.setRequestProperty("X-Api-Key", "p6F5dP6PizQWmEVxW70ykQ==nsHGkUmLt9Pe6sZN");


                        InputStream inputStream = connection.getInputStream();

                        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");

                        if (scanner.hasNext()) {
                            displayResult = scanner.next();
                        } else {
                            displayResult = "";
                        }


                        try {
                            quote = new JSONArray(displayResult)
                                    .getJSONObject(0)
                                    .getString("quote");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.fitness_tracker_1__1_)
                            .setContentTitle("Track Your Health!")
                            .setContentText(quote)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.notify(0, builder.build());

                    timer.cancel();
                    stopSelf();
                }
            }, 30000);
        }
    }

    void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"Fitness Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}