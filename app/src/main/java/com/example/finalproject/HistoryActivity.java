package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    String names[];
    String dates[];
    String types[];
    String calories[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        names = ((FitnessApplication) getApplication()).getNames().toArray(new String[0]);
        dates = ((FitnessApplication) getApplication()).getDates().toArray(new String[0]);
        types = ((FitnessApplication) getApplication()).getTypes().toArray(new String[0]);
        calories = ((FitnessApplication) getApplication()).getCalories().toArray(new String[0]);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(names, dates, types, calories, this);

        recyclerView.setAdapter(historyRecyclerViewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            // Go back to main activity
            onBackPressed();
        }
        return true;
    }
}