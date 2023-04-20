package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class JournalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerActivityFood;
    EditText txtName;
    EditText txtCalories;
    Button btnSaveEntry;
    String text = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerActivityFood = findViewById(R.id.spinnerActivityFood);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activityFood, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityFood.setAdapter(adapter);
        spinnerActivityFood.setOnItemSelectedListener(this);

        txtName = findViewById(R.id.txtName);
        txtCalories = findViewById(R.id.txtCalories);
        btnSaveEntry = findViewById(R.id.btnSaveEntry);

        btnSaveEntry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = txtName.getText().toString();
                int calories = Integer.parseInt(txtCalories.getText().toString());

                ((FitnessApplication)getApplication()).addResult(name, calories, text);



            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}