package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    ArrayList<String> names;
    ArrayList<String> dates;
    ArrayList<String> types;
    ArrayList<String> calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerView);

        // Get data for recycler view
        names = ((FitnessApplication) getApplication()).getNames();
        dates = ((FitnessApplication) getApplication()).getDates();
        types = ((FitnessApplication) getApplication()).getTypes();
        calories = ((FitnessApplication) getApplication()).getCalories();

        for (Object name : names.toArray()) {
            Log.i("nameobject", name.toString());
        }

        // Set adapter to recycler view
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