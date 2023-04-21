package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView CurrentWeight;
    private TextView GoalWeight;
    private TextView GoalCalorieIntake;
    private TextView CaloriesConsumed;
    private TextView CaloriesBurned;
    private TextView CaloriesRemaining;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("UserSettings", MODE_PRIVATE);

        CurrentWeight = findViewById(R.id.txtCurrentWeight);
        GoalWeight = findViewById(R.id.txtGoalWeight);
        GoalCalorieIntake = findViewById(R.id.txtGoalCalorieIntake);
        CaloriesConsumed = findViewById(R.id.txtCaloriesConsumed);
        CaloriesBurned = findViewById(R.id.txtCaloriesBurned);
        CaloriesRemaining = findViewById(R.id.txtCaloriesRemaining);

        int caloriesConsumed = (int)((FitnessApplication)getApplication()).getCaloriesConsumed();
        int caloriesBurned = (int)((FitnessApplication)getApplication()).getCaloriesBurned();

        CurrentWeight.setText(sharedPreferences.getString("currentWeight", ""));
        GoalWeight.setText(sharedPreferences.getString("goalWeight", ""));
        GoalCalorieIntake.setText(String.valueOf(sharedPreferences.getInt("BMR", 0)));
        CaloriesConsumed.setText(String.valueOf(caloriesConsumed));
        CaloriesBurned.setText(String.valueOf(caloriesBurned));
        CaloriesRemaining.setText(String.valueOf((sharedPreferences.getInt("BMR", 0) - caloriesConsumed + caloriesBurned)));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fitness_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menuUserSettings) {
            Intent intent = new Intent(this, UserSettingsActivity.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.menuJournal) {
            Intent intent = new Intent(this, JournalActivity.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.menuHistory) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        }

        return true;

    }

    @Override
    protected void onPause() {
        startService(new Intent(getApplicationContext(), NotificationService.class));

        super.onPause();
    }
}