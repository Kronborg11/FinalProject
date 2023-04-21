package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;


public class JournalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerActivityFood;
    EditText txtName;
    EditText txtCalories;
    Button btnSaveEntry;
    String text = "Activity";
    Button btnReset;

    LinearLayout layoutJournalButtons;

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
        btnReset = findViewById(R.id.btnReset);
        layoutJournalButtons = findViewById(R.id.layoutJournalButtons);

        btnSaveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((txtName.getText().toString().isEmpty() || txtCalories.getText().toString().isEmpty())) {
                    Snackbar snackbar = Snackbar.make(v, "Name and/or Calories not given.", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    snackbar.show();
                } else {
                    String name = txtName.getText().toString();
                    int calories = Integer.parseInt(txtCalories.getText().toString());

                    ((FitnessApplication)getApplication()).addResult(name, calories, text);
                    Snackbar snackbar = Snackbar.make(v, "Activity/Food saved.", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    snackbar.show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((FitnessApplication)getApplication()).reset();

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