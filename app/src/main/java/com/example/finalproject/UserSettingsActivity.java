package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class UserSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editCurrentWeight;
    private EditText editGoalWeight;
    private Spinner spinnerSex;
    private EditText editAge;
    private EditText editHeight;
    private Button btnSaveSettings;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editCurrentWeight = findViewById(R.id.editCurrentWeight);
        editGoalWeight = findViewById(R.id.editGoalWeight);
        spinnerSex = findViewById(R.id.spinnerSex);
        editAge = findViewById(R.id.editAge);
        editHeight = findViewById(R.id.editHeight);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);

        sharedPreferences = getSharedPreferences("UserSettings", MODE_PRIVATE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapter);
        spinnerSex.setOnItemSelectedListener(this);

        // Fill with data
        editCurrentWeight.setText(sharedPreferences.getString("currentWeight", ""));
        editGoalWeight.setText(sharedPreferences.getString("goalWeight", ""));
        spinnerSex.setSelection(sharedPreferences.getInt("sex", 0));
        editAge.setText(sharedPreferences.getString("age", ""));
        editHeight.setText(sharedPreferences.getString("height", ""));

        // Save button OnClick listener
        View.OnClickListener saveSettings = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("currentWeight", editCurrentWeight.getText().toString());
                editor.putString("goalWeight", editGoalWeight.getText().toString());
                editor.putInt("sex", spinnerSex.getSelectedItemPosition());
                editor.putString("age", editAge.getText().toString());
                editor.putString("height", editHeight.getText().toString());

                int BMR = (int) (66.47 + (6.24 * Integer.parseInt(editCurrentWeight.getText().toString())) +
                        ((12.7 * (Integer.parseInt(editHeight.getText().toString()) / 30.48)*12)) -
                        (6.75 * Integer.parseInt(editAge.getText().toString())));

                editor.putInt("BMR", BMR);

                editor.commit();


            }
        };
        btnSaveSettings.setOnClickListener(saveSettings);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}