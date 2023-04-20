package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
    protected void onStop() {
        startService(new Intent(getApplicationContext(), NotificationService.class));

        super.onStop();
    }
}